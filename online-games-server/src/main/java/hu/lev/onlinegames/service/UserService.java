package hu.lev.onlinegames.service;

import hu.lev.onlinegames.model.User;

public interface UserService {
	public boolean validateEmail(String emailStr);
	public boolean registerUser(User user);
}
