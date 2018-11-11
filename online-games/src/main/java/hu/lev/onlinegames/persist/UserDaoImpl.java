package hu.lev.onlinegames.persist;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Expression;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Repository;

import hu.lev.onlinegames.model.GameType;
import hu.lev.onlinegames.model.GameTypeOption;
import hu.lev.onlinegames.model.MatchWaiting;
import hu.lev.onlinegames.model.User;

@Repository
public class UserDaoImpl implements UserDao {
//	
//	SessionFactory sessionFactory;
//	
//	public UserDaoImpl() {
//		Configuration con = null;
//		ServiceRegistry reg = null;
//		
//		try {
//			con = new Configuration()
//					.configure()
//					.addAnnotatedClass(User.class);
//			reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
//			this.sessionFactory = con.buildSessionFactory(reg);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

//	public RoomMembers logIn(Members members) {
//	    Criteria criteria = sessionFactory
//	            .getCurrentSession().createCriteria(Members.class);
//	    criteria.add(Expression.eq("memberUserName", members.getMemberUserName()));
//	    criteria.add(Expression.eq("password", members.getPassword()));
//
//	    Members Member=(Members) criteria.uniqueResult();
//	    return Member;
//	}
	
//	@Override
	public int getUserIdByPassword(User user) {		
		int id = 0;
		
		try {
			Configuration con = new Configuration()
					.configure()
					.addAnnotatedClass(GameType.class)
					.addAnnotatedClass(GameTypeOption.class)
					.addAnnotatedClass(User.class)
					.addAnnotatedClass(MatchWaiting.class);
			ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
			SessionFactory sf = con.buildSessionFactory(reg);
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			Query q = session.createQuery("select id from User where username = :a and password = :b");
			q.setParameter("a", user.getUsername());
			q.setParameter("b", user.getPassword());
			
			id = (int) q.uniqueResult();
			
			tx.commit();
			session.close();
			
		} catch (NullPointerException e) {
			id = 0;
//			e.printStackTrace();
		} catch (Exception e) {
			id = -2;
//			e.printStackTrace();
		}
    	
		return id;
	}

//	@Override
	public int insertUser(User user) {			
		int id = 0;
		
		try {
			Configuration con = new Configuration()
					.configure()
					.addAnnotatedClass(GameType.class)
					.addAnnotatedClass(GameTypeOption.class)
					.addAnnotatedClass(User.class)
					.addAnnotatedClass(MatchWaiting.class);
			ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
			SessionFactory sf = con.buildSessionFactory(reg);
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			id = (int) session.save(user);
			
			tx.commit();
			session.close();
		} catch (PersistenceException e) {
			id = 0;
//			e.printStackTrace();
		} catch (Exception e) {
			id = -2;
//			e.printStackTrace();
		}
    	
		return id;
	}

}
