package hu.lev.onlinegames.service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.lev.onlinegames.model.User;
import hu.lev.onlinegames.persist.UserDao;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
//	@Override
	public boolean validateEmail(String emailStr) {
		boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(emailStr);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
	}
	
//	@Override
	public int registerUser(User user) {
		return userDao.insertUser(user);
	}

}
