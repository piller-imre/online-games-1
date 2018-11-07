package hu.lev.onlinegames.persist;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Repository;

import hu.lev.onlinegames.model.User;
import hu.lev.onlinegames.model.GameType;
import hu.lev.onlinegames.model.GameTypeOption;

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
					.addAnnotatedClass(GameTypeOption.class);
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

}
