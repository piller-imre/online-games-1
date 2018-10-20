package hu.lev.onlinegames.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import hu.lev.onlinegames.model.User;
import hu.lev.onlinegames.persist.UserDao;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

//	@Override
	public boolean validateEmail(String emailStr) {
	        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
	        return matcher.find();
	}
	
//	@Override
	public boolean registerUser(User user) {
		return userDao.insertUser(user);
	}

}
