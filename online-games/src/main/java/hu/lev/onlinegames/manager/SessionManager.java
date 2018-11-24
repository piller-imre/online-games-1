package hu.lev.onlinegames.manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Service;

import hu.lev.onlinegames.model.GameType;
import hu.lev.onlinegames.model.GameTypeOption;
import hu.lev.onlinegames.model.MatchActive;
import hu.lev.onlinegames.model.MatchWaiting;
import hu.lev.onlinegames.model.Players;
import hu.lev.onlinegames.model.User;

@Service
public class SessionManager {

	private Configuration config;
	ServiceRegistry reg;
	SessionFactory sf;
	private Session session;

	// constructror
	public SessionManager() {
		try {
			System.out.println("");
			System.out.println("NEW SESSION MANAGER");
			System.out.println("");
			this.config = new Configuration()
					.configure()
					.addAnnotatedClass(GameType.class)
					.addAnnotatedClass(GameTypeOption.class)
					.addAnnotatedClass(User.class)
					.addAnnotatedClass(MatchWaiting.class)
					.addAnnotatedClass(MatchActive.class)
					.addAnnotatedClass(Players.class);

			this.reg = new StandardServiceRegistryBuilder()
					.applySettings(this.config.getProperties())
					.build();			
			this.sf = this.config.buildSessionFactory(this.reg);
		
			this.session = sf.openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Configuration getConfig() {
		return config;
	}

	public void setConfig(Configuration config) {
		this.config = config;
	}

	// functions	
	public Session getSession() {
//		try {
//			if(this.session == null) {
//				this.session = sf.openSession();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return this.session;
	}

	public Session getNewSession() {
		Session session = null;
		try {
			session = sf.openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return session;
	}
	
}
