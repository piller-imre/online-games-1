package hu.lev.onlinegames.persist;

import hu.lev.onlinegames.model.User;

public interface UserDao {
	public int getUserIdByPassword(User user);	// returns user id
	public boolean insertUser(User user);
}
