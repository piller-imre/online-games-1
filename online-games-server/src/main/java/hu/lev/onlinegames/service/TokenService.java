package hu.lev.onlinegames.service;

public interface TokenService {
	public String createJWT(String id, long ttlMillis);
	public void parseJWT(String jwt);
	public boolean saveToken(int userId, String token);
}
