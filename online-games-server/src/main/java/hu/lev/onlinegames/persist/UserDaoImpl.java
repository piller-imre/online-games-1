package hu.lev.onlinegames.persist;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import hu.lev.onlinegames.model.User;

public class UserDaoImpl implements UserDao {

//	@Override
	public int getUserIdByPassword(User user) {
		// TODO getUserIdByPassword
		String snippet = "SELECT * FROM user WHERE username = " + user.getUsername() + " AND password = " + user.getPassword() + ";";
		return 0;
	}

//	@Override
	public boolean insertUser(User user) {
		// TODO insertUser
//		 String snippet = "INSERT INTO user (username, password, email) values (" + 
//				 		user.getUsername() + ", " +
//				 		user.getPassword() + ", " +
//				 		user.getEmail() + ");";
		
		Configuration con = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(User.class);
		ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		session.save(user);
		
		tx.commit();
		session.close();
		
		return false;
	}

}
