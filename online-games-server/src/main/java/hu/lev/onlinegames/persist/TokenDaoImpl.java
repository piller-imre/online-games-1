package hu.lev.onlinegames.persist;

public class TokenDaoImpl implements TokenDao {

//	@Override
	public boolean saveToken(int userId, String token) {
		// TODO save token, return success status
		String snippet = "UPDATE user SET token = " + token + "WHERE id = " + userId + ";";
		return false;
	}

}