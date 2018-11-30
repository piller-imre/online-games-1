package hu.lev.onlinegames.persist;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hu.lev.onlinegames.manager.SessionManager;
import hu.lev.onlinegames.model.GameType;
import hu.lev.onlinegames.model.stats.ByTypeStats;
import hu.lev.onlinegames.model.stats.ByTypeStatsUsers;
import hu.lev.onlinegames.model.stats.GlobalStatsByGameType;
import hu.lev.onlinegames.model.stats.PersonalStats;
import hu.lev.onlinegames.model.stats.PersonalStatsByType;

@Repository
public class StatsDaoImpl implements StatsDao {

	@Autowired
	SessionManager sm;

	public StatsDaoImpl() {
		super();
	}

	
	// global stats

	@Override
	public int getNumberOfAllMatches() {
		int allMatches = 0;

		try {
			Session session = sm.getNewSession();
			Transaction tx = session.beginTransaction();

			Query q = session.createSQLQuery("SELECT SUM(match_count_total)/2 FROM match_done");

			BigDecimal result = (BigDecimal) q.uniqueResult();
			try {
				allMatches = result.intValueExact();
			} catch (Exception e) {
				e.printStackTrace();
			}

			tx.commit();
			session.close();

		} catch (Exception e) {
			allMatches = 0;
			e.printStackTrace();
		}

		return allMatches;
	}

	@Override
	public GlobalStatsByGameType[] getNumberOfAllMatchesByGameType() {
		GlobalStatsByGameType[] stats = null;

		try {
			Session session = sm.getNewSession();
			Transaction tx = session.beginTransaction();

			Query q = session.createSQLQuery(
					"SELECT g.name, SUM(m.match_count_total)/2 FROM match_done m, game_type g where g.id = m.game_type_fk group by m.game_type_fk");

			List<Object[]> objects = q.list();

			if (objects != null) {			
				stats = new GlobalStatsByGameType[objects.size()];
				
				Object[] o;
				for (int i = 0; i < objects.size(); i++) {
					o = objects.get(i);
					GlobalStatsByGameType stat = 
							new GlobalStatsByGameType(
									(String) o[0], 
									((BigDecimal)o[1]).intValueExact());
					stats[i] = stat;
				}
			}

			tx.commit();
			 session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stats;
	}

	@Override
	public ByTypeStatsUsers[] getGlobalRanks() {
		ByTypeStatsUsers[] stats = null;

		try {
			Session session = sm.getNewSession();
			Transaction tx = session.beginTransaction();

			Query q = session.createSQLQuery(
					"SELECT u.id, u.username, SUM(m.match_won_total), SUM(m.match_count_total) " 
					+ "FROM match_done m, user u " 
					+ "where u.id = m.player_fk "
					+ "group by m.player_fk " 
					+ "order by sum(m.match_won_total) desc");

			List<Object[]> objects = q.list();

			if (objects != null) {			
				stats = new ByTypeStatsUsers[objects.size()];
				
				Object[] o;
				for (int i = 0; i < objects.size(); i++) {
					o = objects.get(i);
					ByTypeStatsUsers stat = 
							new ByTypeStatsUsers(
									(int)o[0], 
									(String) o[1], 
									((BigDecimal)o[2]).intValueExact(), 
									((BigDecimal)o[3]).intValueExact());
					stats[i] = stat;
				}
			}

			tx.commit();
			 session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stats;
	}

	
	// byType stats

	@Override
	public ByTypeStats[] getByTypeStats() {
		ByTypeStats[] stats = null;

		try {
			Session session = sm.getNewSession();
			Transaction tx = session.beginTransaction();
			
			Query q = session.createQuery("from GameType");
			List<GameType> gameTypeList = (List<GameType>) q.list();
			GameType[] gameTypes = new GameType[gameTypeList.size()];
			gameTypeList.toArray(gameTypes);

			stats = new ByTypeStats[gameTypes.length];
			
			for (int i = 0; i < gameTypes.length; i++) {
				
				stats[i] = new ByTypeStats();
				stats[i].setGameType(gameTypes[i].getGameTypeName());
				
				q = session.createSQLQuery(
						"SELECT u.id, u.username, SUM(m.match_won_total), SUM(m.match_count_total) "
						+ "FROM match_done m, user u " 
						+ "where u.id = m.player_fk and game_type_fk = :a " 
						+ "group by m.player_fk "
						+ "order by sum(m.match_won_total) desc limit 5");
				q.setParameter("a", gameTypes[i].getGameTypeId());
	
				List<Object[]> objects = q.list();
	
				if (objects != null) {	
					ByTypeStatsUsers[] result = new ByTypeStatsUsers[objects.size()];
					
					for(int j = 0; j < objects.size(); j++) {
						Object[] o = objects.get(j);
						ByTypeStatsUsers stat = new ByTypeStatsUsers(
								(int)o[0], 
								(String) o[1], 
								((BigDecimal)o[2]).intValueExact(), 
								((BigDecimal)o[3]).intValueExact());
						result[j] = stat;
					}
					
					stats[i].setResults(result);
				}
			}

			tx.commit();
			 session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stats;
	}

	
	// personal stats

	@Override
	public PersonalStats setPersonalWinAndTotal(PersonalStats temp, int id) {

		try {
			Session session = sm.getNewSession();
			Transaction tx = session.beginTransaction();

			Query q = session.createSQLQuery(
					"SELECT SUM(match_won_total), SUM(match_count_total) FROM match_done where player_fk = :a");
			q.setParameter("a", id);

			Object[] result = (Object[]) q.uniqueResult();

			if (result != null) {
				BigDecimal result0 = null;
				BigDecimal result1 = null;
				try {
					result0 = (BigDecimal)result[0];
					result1 = (BigDecimal)result[1];
					temp.setWinMatches(result0.intValueExact());
					temp.setAllMatches(result1.intValueExact());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			tx.commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return temp;
	}

	@Override
	public PersonalStatsByType[] getPersonalStatsByGameType(int id) {
		PersonalStatsByType[] stats = null;

		try {
			Session session = sm.getNewSession();
			Transaction tx = session.beginTransaction();

			Query q = session.createSQLQuery(
					"SELECT g.name, SUM(m.match_won_total), SUM(m.match_count_total) FROM match_done m, game_type g where g.id = m.game_type_fk and m.player_fk = :a group by m.game_type_fk order by sum(m.match_won_total) desc");
			q.setParameter("a", id);

			List<Object[]> objects = q.list();

			if (objects != null) {			
				stats = new PersonalStatsByType[objects.size()];
				
				Object[] o;
				for (int i = 0; i < objects.size(); i++) {
					o = objects.get(i);
					PersonalStatsByType stat = 
							new PersonalStatsByType(
									(String) o[0], 
									((BigDecimal)o[1]).intValueExact(), 
									((BigDecimal)o[2]).intValueExact());
					stats[i] = stat;
				}
			}

			tx.commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stats;
	}
}
