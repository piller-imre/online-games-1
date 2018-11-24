package hu.lev.onlinegames;

import hu.lev.onlinegames.model.fiveinarow.FiveInARowAction;
import hu.lev.onlinegames.model.fiveinarow.FiveInARowField;

public class App {
	public static void main(String[] args) {

//		MatchStartRq req = new MatchStartRq(6, 2);
//		MatchServiceImpl serv = new MatchServiceImpl();
//		MatchDao d = new MatchDaoImpl();
//		MatchWaiting ma = d.getMatchWaiting(req.getMatchId());

//		MatchActive ma = serv.startMatch(req);

//		System.out.println(ma.toString());

		boolean win = false;
		int player = 2; 
		FiveInARowAction action = new FiveInARowAction(4,4,2);
		FiveInARowField[][] fields = new FiveInARowField[25][25];
		
		for (int i = 0; i < fields.length; i++) {
			fields[i] = new FiveInARowField[25];
			for (int j = 0; j < fields[i].length; j++) {
				fields[i][j] = new FiveInARowField();
			}
		}
		
		fields[4][2].setValue(2);
		fields[4][3].setValue(2);
		fields[4][4].setValue(2);
		fields[4][5].setValue(2);
		fields[4][6].setValue(2);
		

		int startX = action.getX() - 4;			// init min and max indexes, so we check fields in board
		int startY = action.getY() - 4;
		int endX = action.getX() + 4;
		int endY = action.getY() + 4;
		
		System.out.println("startX: " + startX);
		System.out.println("startY: " + startY);
		System.out.println("endX: " + endX);
		System.out.println("endY: " + endY);
		
		// diagonals first, there is a bigger chance to win this way, save some energy
		// diagonal from top-left
		int lengthSoFar = 0;
		for(int i = startX, j = startY; i<=endX && j<=endY; i++){
			if (i >= 0 && i < fields.length && j >= 0 && j < fields[0].length){
				if (fields[i][j].getValue() == player){
					lengthSoFar++;
				} else if (lengthSoFar < 5){
					lengthSoFar = 0;
				}
			}
			j++;
		}
		if (lengthSoFar >= 5){ win = true; }

		// diagolal from bottom-left
		lengthSoFar = 0;
		for(int i = startX, j = endY; i<=endX && j>=startY; i++){
			if (i >= 0 && i < fields.length && j >= 0 && j < fields[0].length){
				if (fields[i][j].getValue() == player){
					lengthSoFar++;
				} else if (lengthSoFar < 5){
					lengthSoFar = 0;
				}
			}
			j--;
		}
		if (lengthSoFar >= 5){ win = true; }

		// vertical
		lengthSoFar = 0;
		for(int j=startY; j<=endY ; j++){
			if (j >= 0 && j < fields[0].length){
				System.out.println(fields[action.getX()][j].getValue());
				if (fields[action.getX()][j].getValue() == player){
					lengthSoFar++;
				} else if (lengthSoFar < 5){
					lengthSoFar = 0;
				}
			}
		}
		if (lengthSoFar >= 5){ win = true; }

		// horizontal
		lengthSoFar = 0;
		for(int i=startX; i<=endX ; i++){
			if (i >= 0 && i < fields.length){
				if (fields[i][action.getY()].getValue() == player){
					lengthSoFar++;
				} else if (lengthSoFar < 5){
					lengthSoFar = 0;
				}
			}
		}
		if (lengthSoFar >= 5){ win = true; }

		System.out.println(win);
		// no winning found
		win = false;

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

//save new match
//GameType[] gameTypes = null;
//try {
//	Configuration con = new Configuration()
//			.configure()
//			.addAnnotatedClass(GameType.class)
//			.addAnnotatedClass(GameTypeOption.class);
//	ServiceRegistry reg = new StandardServiceRegistryBuilder()
//			.applySettings(con.getProperties())
//			.build();
//
//	System.out.println("alma1");
//	
//	SessionFactory sf = con.buildSessionFactory(reg);
//	
//	System.out.println("alma2");
//
//	Session session = sf.openSession();
//	Transaction tx = session.beginTransaction();
//				
//		System.out.println("alma3");
//		Query q = session.createQuery("from GameType");
//		System.out.println("eper");
//		
//		List<Object> objects = q.list();			
//		gameTypes = new GameType[objects.size()];
//		objects.toArray(gameTypes);
//	
//	tx.commit();
//	session.close();
//	
//	System.out.println("dinnye");
//	for(GameType g : gameTypes) {
//		System.out.println(g.toString());
//	}
//	System.out.println(gameTypes[0].getOptions().size());
//				
//} catch (Exception e) {
//	e.printStackTrace();
//}

//insert new match waiting
//int[] o = {1,2,3};
//NewMatchRq req = new NewMatchRq(1, "test", 1, o);
//
//
//
//try {
//	Configuration con = new Configuration()
//			.configure()
//			.addAnnotatedClass(GameType.class)
//			.addAnnotatedClass(GameTypeOption.class)
//			.addAnnotatedClass(User.class)
//			.addAnnotatedClass(NewMatch.class);
//	ServiceRegistry reg = new StandardServiceRegistryBuilder()
//			.applySettings(con.getProperties())
//			.build();
//	System.out.println("alma1");			
//	SessionFactory sf = con.buildSessionFactory(reg);	
//	System.out.println("alma2");
//	Session session = sf.openSession();
//	Transaction tx = session.beginTransaction();						
//		System.out.println("alma3");
//		
//		GameType gameType = session.load(GameType.class, req.getGameTypeId());
//		User user = session.load(User.class, req.getUserid());
//		String ops = Arrays.toString(req.getOptions());
//		
//		NewMatch match = new NewMatch(user, gameType, ops);
//		
//		System.out.println("instert_start");
//		int id = (int) session.save(match);
//		System.out.println("insert_done");
//		System.out.println("new_match_id: " + id);
//		
//		System.out.println("eper");
//	tx.commit();
//	session.close();			
//	System.out.println("dinnye");
//
//				
//} catch (Exception e) {
//	e.printStackTrace();
//}

//get waiting matches
// DONT FORGET TO SET TYPES AND NAMES as newMatch became matchWaiting
//NewMatch[] newMatches = null;
//
//try {
//	Configuration con = new Configuration()
//			.configure()
//			.addAnnotatedClass(GameType.class)
//			.addAnnotatedClass(GameTypeOption.class)
//			.addAnnotatedClass(User.class)
//			.addAnnotatedClass(NewMatch.class);
//	ServiceRegistry reg = new StandardServiceRegistryBuilder()
//			.applySettings(con.getProperties())
//			.build();			
//	SessionFactory sf = con.buildSessionFactory(reg);
//
//	Session session = sf.openSession();
//	Transaction tx = session.beginTransaction();
//				
//		Query q = session.createQuery("from NewMatch");
//		
//		List<Object> objects = q.list();	
//		System.out.println("result size: " + objects.size());
//		newMatches = new NewMatch[objects.size()];
//		objects.toArray(newMatches);
//	
//	tx.commit();
//	session.close();
//	
//	for (NewMatch newMatch : newMatches) {
//		System.out.println(newMatch.toString());
//	}
//				
//} catch (Exception e) {
//	e.printStackTrace();
//}

//public int createMatchActive(int acceptingUserId, MatchWaiting match);
//MatchDao dao = new MatchDaoImpl();
//
//int matchId = 0;
//int acceptingUserId = 1;
//MatchWaiting matchWaiting = dao.getMatchWaiting(1);
//
//Random rand = new Random();
//int mixer = rand.nextInt(2) + 1;
//SessionManager sm = new SessionManager();
//
//try {
//	Session session = sm.getSession();
//	Transaction tx = session.beginTransaction();
//	System.out.println("alma");
//
//	MatchActive match = new MatchActive();
//	match.setGameType(matchWaiting.getGameTypeId());
//	match.setOptions(matchWaiting.getOptions());
//	match.setTurn(1);
//	
//	matchId = (int) session.save(match);
//
//	System.out.println("körte");
//	
//	Players players = new Players();
//
//	User acceptingUser = session.load(User.class, acceptingUserId);
//	User challengingUser = session.load(User.class, matchWaiting.getUser().getId());
//	
//	if(mixer == 1) {
//		players.setPlayer1(acceptingUser);
//		players.setPlayer2(challengingUser);
//	} else {
//		players.setPlayer1(challengingUser);
//		players.setPlayer2(acceptingUser);
//	}
//	players.setActivePlayer(1);
//	players.setMatchId(session.get(MatchActive.class, matchId));
//	
//	session.save(players);
//	tx.commit();
//	System.out.println("dinnye");
//	session.close();
//	
//} catch (Exception e) {
//	matchId = 0;
//	e.printStackTrace();
//}
