package hu.lev.onlinegames.model.stats;

public class ByTypeStatsUsers {
	private int userid;
	private String username;
	private int allMatches;
	private int winMatches;
	
	
	public ByTypeStatsUsers() {
		super();
	}
	public ByTypeStatsUsers(int userid, String username, int winMatches, int allMatches) {
		super();
		this.userid = userid;
		this.username = username;
		this.allMatches = allMatches;
		this.winMatches = winMatches;
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
	public int getAllMatches() {
		return allMatches;
	}
	public void setAllMatches(int allMatches) {
		this.allMatches = allMatches;
	}
	public int getWinMatches() {
		return winMatches;
	}
	public void setWinMatches(int winMatches) {
		this.winMatches = winMatches;
	}
}
