package hu.lev.onlinegames.model;

public class Token {
	private String token;
	private int userid;
	private String username;
	
	public Token() {
		this.token = null;
		this.userid = -1;
		this.username = null;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
