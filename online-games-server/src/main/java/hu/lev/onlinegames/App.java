package hu.lev.onlinegames;

import hu.lev.onlinegames.model.GameType;
import hu.lev.onlinegames.model.GameTypeOption;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class App {
	public static void main(String[] args) {
		
		GameType[] gameTypes = null;
		try {
			Configuration con = new Configuration()
					.configure()
					.addAnnotatedClass(GameType.class)
					.addAnnotatedClass(GameTypeOption.class);
			ServiceRegistry reg = new StandardServiceRegistryBuilder()
					.applySettings(con.getProperties())
					.build();

			System.out.println("alma1");
			
			SessionFactory sf = con.buildSessionFactory(reg);
			
			System.out.println("alma2");

			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
						
				System.out.println("alma3");
				Query q = session.createQuery("from GameType");
				System.out.println("eper");
				
				List<Object> objects = q.list();			
				gameTypes = new GameType[objects.size()];
				objects.toArray(gameTypes);
			
			tx.commit();
			session.close();
			
			System.out.println("dinnye");
			for(GameType g : gameTypes) {
				System.out.println(g.toString());
			}
			System.out.println(gameTypes[0].getOptions().size());
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

// user insert test
//User u = new User("lék", "titok", "áél@vmi.huzat");
//
//Configuration con = new Configuration()
//		.configure()
//		.addAnnotatedClass(User.class);
//ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
//SessionFactory sf = con.buildSessionFactory(reg);
//Session session = sf.openSession();
//Transaction tx = session.beginTransaction();
//
//int id = (int) session.save(u);
//System.out.println("id: " + id);
//
//tx.commit();
//session.close();



//Trying to save user to DB, not worked in app mode
//AuthService authService = new AuthServiceImpl();
//UserService userService = new UserServiceImpl();
//
//RegisterRq req = new RegisterRq();
//req.setEmail("x@y");
//req.setPassword("qwe");
//req.setPasswordConfirmed("qwe");
//req.setUsername("qwe");
//
//boolean success = false;
//
//if(req.getUsername() != null
//		&& req.getPassword() != null
//		&& req.getPasswordConfirmed() != null
//		&& req.getPassword().equals(req.getPasswordConfirmed())
//		&& userService.validateEmail(req.getEmail())){
//	
//	String password = authService.getHash(req.getPassword()); // get hashcode of password
//	User user = new User(
//			req.getUsername(),
//			password,
//			req.getEmail());
//	int id = userService.registerUser(user);
//	
//	if(id > 0) {
//		success = true;
//	}
//}


//get 
