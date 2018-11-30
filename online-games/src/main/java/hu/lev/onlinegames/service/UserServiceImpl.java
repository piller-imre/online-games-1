package hu.lev.onlinegames.service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.lev.onlinegames.model.Token;
import hu.lev.onlinegames.model.User;
import hu.lev.onlinegames.persist.UserDao;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;	

	@Autowired
	private TokenService tokenService;
	
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

	public Token authenticate(User user) {
		Token token = null;
		int userid = userDao.getUserIdByPassword(user);
		if(userid > 0) {
			token = new Token();
			token.setToken(tokenService.createJWT(userid + "", 600000));
			token.setUserid(userid);
			token.setUsername(user.getUsername());
		}
		return token;
	}

	public String getHash(String text) {
		return DigestUtils.sha256Hex(text);
	}
}
