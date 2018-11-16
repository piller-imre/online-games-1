package hu.lev.onlinegames.persist;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.persistence.PersistenceException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import hu.lev.onlinegames.manager.SessionManager;
import hu.lev.onlinegames.model.GameType;
import hu.lev.onlinegames.model.MatchActive;
import hu.lev.onlinegames.model.MatchWaiting;
import hu.lev.onlinegames.model.Players;
import hu.lev.onlinegames.model.User;
import hu.lev.onlinegames.model.request.MatchWaitingRq;

@Repository
public class MatchDaoImpl implements MatchDao {

	SessionManager sm;
	
	public MatchDaoImpl() {
		super();
		this.sm = new SessionManager();
	}

	@Override
	public GameType[] getGameTypes() {

		GameType[] gameTypes = null;
		
		try {
			Session session = sm.getSession();
			Transaction tx = session.beginTransaction();
						
				Query q = session.createQuery("from GameType");
				
				List<Object> objects = q.list();			
				gameTypes = new GameType[objects.size()];
				objects.toArray(gameTypes);
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gameTypes;
	}

	@Override
	public int insertNewMatch(MatchWaitingRq req) {
		int id = 0;

		try {
			Session session = sm.getSession();
			Transaction tx = session.beginTransaction();	
				
				GameType gameType = session.load(GameType.class, req.getGameTypeId());
				User user = session.load(User.class, req.getUserid());
				String ops = Arrays.toString(req.getOptions());
				
				MatchWaiting match = new MatchWaiting(user, gameType, ops);
				
				id = (int) session.save(match);
			tx.commit();
			session.close();
		} catch (PersistenceException e) {
			id = -1;
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return id;
	}

	@Override
	public MatchWaiting[] getMatchesWaiting() {
		MatchWaiting[] matchesWaiting = null;
		
		try {
			Session session = sm.getSession();
			Transaction tx = session.beginTransaction();
						
				Query q = session.createQuery("from MatchWaiting");
				
				List<Object> objects = q.list();	
				matchesWaiting = new MatchWaiting[objects.size()];
				objects.toArray(matchesWaiting);
			
			tx.commit();
			session.close();
									
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matchesWaiting;
	}

	@Override
	public boolean deleteMatchWaiting(int id) {
		boolean success = false;
		
		try {
			Session session = sm.getSession();
			Transaction tx = session.beginTransaction();
				
			MatchWaiting match = new MatchWaiting();
			match.setId(id);
			session.remove(match);
			
			tx.commit();
			session.close();
			
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	
	@Override
	public MatchWaiting getMatchWaiting(int matchId) {
		
		MatchWaiting match = null;
		
		try {
			Session session = sm.getSession();
			Transaction tx = session.beginTransaction();
			
			match = session.get(MatchWaiting.class, matchId);
			
			tx.commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
		return match;
	}
	

	@Override
	public int createMatchActive(int acceptingUserId, MatchWaiting matchWaiting) {

		int matchId = 0;
		Random rand = new Random();
		int mixer = rand.nextInt(2) + 1;
		SessionManager sm = new SessionManager();

		try {
			Session session = sm.getSession();
			Transaction tx = session.beginTransaction();

			MatchActive match = new MatchActive();
			match.setGameType(matchWaiting.getGameTypeId());
			match.setOptions(matchWaiting.getOptions());
			match.setTurn(1);
			
			matchId = (int) session.save(match);
			
			Players players = new Players();

			User acceptingUser = session.load(User.class, acceptingUserId);
			User challengingUser = session.load(User.class, matchWaiting.getUser().getId());
			
			if(mixer == 1) {
				players.setPlayer1(acceptingUser);
				players.setPlayer2(challengingUser);
			} else {
				players.setPlayer1(challengingUser);
				players.setPlayer2(acceptingUser);
			}
			players.setActivePlayer(1);
			players.setMatchId(session.get(MatchActive.class, matchId));
			
			session.save(players);
			
			tx.commit();
			session.close();
			
		} catch (Exception e) {
			matchId = 0;
			e.printStackTrace();
		}
		
		return matchId;
	}

	
	@Override
	public MatchActive checkStart(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
