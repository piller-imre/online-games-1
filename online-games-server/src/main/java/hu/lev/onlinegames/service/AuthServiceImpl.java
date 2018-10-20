package hu.lev.onlinegames.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.codec.digest.DigestUtils;

import hu.lev.onlinegames.model.User;
import hu.lev.onlinegames.persist.UserDao;

public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private UserDao dao;

	public int authenticate(User user) {		
		return dao.getUserIdByPassword(user);
	}

	public String getHash(String text) {
		return DigestUtils.sha256Hex(text);
	}
	
}
