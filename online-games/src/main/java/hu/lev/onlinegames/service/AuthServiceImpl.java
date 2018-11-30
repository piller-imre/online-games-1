package hu.lev.onlinegames.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.lev.onlinegames.model.User;
import hu.lev.onlinegames.persist.UserDao;

@Service
public class AuthServiceImpl implements AuthService {
	
//	@Autowired
//	private UserDao userDao;
//
//	public int authenticate(User user) {		
//		return userDao.getUserIdByPassword(user);
//	}
//
//	public String getHash(String text) {
//		return DigestUtils.sha256Hex(text);
//	}
	
}
