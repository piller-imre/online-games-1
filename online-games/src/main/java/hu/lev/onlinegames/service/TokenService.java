package hu.lev.onlinegames.service;

import io.jsonwebtoken.Claims;

public interface TokenService {
	public String createJWT(String id, long ttlMillis);
	public Claims parseJWT(String jwt);
//	public boolean saveToken(int userId, String token);
}
