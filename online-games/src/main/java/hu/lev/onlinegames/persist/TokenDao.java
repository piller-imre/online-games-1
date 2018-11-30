package hu.lev.onlinegames.persist;

public interface TokenDao {
	public boolean saveToken(int userId, String token);
}
