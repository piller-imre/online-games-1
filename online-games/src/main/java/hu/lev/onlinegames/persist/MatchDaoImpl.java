package hu.lev.onlinegames.persist;

import java.util.Arrays;
import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Repository;

import hu.lev.onlinegames.model.GameType;
import hu.lev.onlinegames.model.GameTypeOption;
import hu.lev.onlinegames.model.MatchWaiting;
import hu.lev.onlinegames.model.User;
import hu.lev.onlinegames.model.request.MatchWaitingRq;

@Repository
public class MatchDaoImpl implements MatchDao {

	public MatchDaoImpl() {
		super();
	}

	@Override
	public GameType[] getGameTypes() {

		GameType[] gameTypes = null;
		
		try {
			Configuration con = new Configuration()
					.configure()
					.addAnnotatedClass(GameType.class)
					.addAnnotatedClass(GameTypeOption.class)
					.addAnnotatedClass(User.class)
					.addAnnotatedClass(MatchWaiting.class);
			ServiceRegistry reg = new StandardServiceRegistryBuilder()
					.applySettings(con.getProperties())
					.build();			
			SessionFactory sf = con.buildSessionFactory(reg);

			Session session = sf.openSession();
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
			Configuration con = new Configuration()
					.configure()
					.addAnnotatedClass(GameType.class)
					.addAnnotatedClass(GameTypeOption.class)
					.addAnnotatedClass(User.class)
					.addAnnotatedClass(MatchWaiting.class);
			ServiceRegistry reg = new StandardServiceRegistryBuilder()
					.applySettings(con.getProperties())
					.build();
			SessionFactory sf = con.buildSessionFactory(reg);	
			Session session = sf.openSession();
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
			Configuration con = new Configuration()
					.configure()
					.addAnnotatedClass(GameType.class)
					.addAnnotatedClass(GameTypeOption.class)
					.addAnnotatedClass(User.class)
					.addAnnotatedClass(MatchWaiting.class);
			ServiceRegistry reg = new StandardServiceRegistryBuilder()
					.applySettings(con.getProperties())
					.build();			
			SessionFactory sf = con.buildSessionFactory(reg);

			Session session = sf.openSession();
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

}
