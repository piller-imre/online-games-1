package hu.lev.onlinegames;

public class App {

	public static void main(String[] args) {
		int v = 10;
		System.out.println(v + "");
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
