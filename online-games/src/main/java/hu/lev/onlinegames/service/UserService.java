package hu.lev.onlinegames.service;

import hu.lev.onlinegames.model.Token;
import hu.lev.onlinegames.model.User;

public interface UserService {
	public boolean validateEmail(String emailStr);
	public int registerUser(User user);
	public Token authenticate(User user);
	public String getHash(String text);
}
