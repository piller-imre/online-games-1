package hu.lev.onlinegames.persist;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Repository;

import hu.lev.onlinegames.model.User;

@Repository
public class UserDaoImpl implements UserDao {

//	@Override
	public int getUserIdByPassword(User user) {
		// TODO getUserIdByPassword
		String snippet = "SELECT * FROM user WHERE username = " + user.getUsername() + " AND password = " + user.getPassword() + ";";
		
		return 1;
	}

//	@Override
	public int insertUser(User user) {	
		Configuration con = new Configuration()
				.configure()
				.addAnnotatedClass(User.class);
		ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		int id = 0;
		
		try {
			id = (int) session.save(user);
			
			tx.commit();
			session.close();
		} catch (PersistenceException e) {
			id = 0;
//			e.printStackTrace();
		}
    	
		return id;
	}

}
