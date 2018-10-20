package hu.lev.onlinegames;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import hu.lev.onlinegames.model.User;

public class App {

	public static void main(String[] args) {

    	User u = new User("Erzsi", "titok", "erzsi@vmi.huzat");
    	
    	Configuration con = new Configuration()
				.configure()
				.addAnnotatedClass(User.class);
		ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		session.save(u);
		
		tx.commit();
		session.close();
	}

}
