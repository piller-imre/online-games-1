package hu.lev.onlinegames.model.request;

import java.util.Arrays;

public class MatchWaitingRq {
    
    private int userid;
    private String username;
    private int gameTypeId;
    private int[] options;
    
    
	public MatchWaitingRq() {
		super();
	}
	public MatchWaitingRq(int userid, String username, int gameTypeId, int[] options) {
		super();
		this.userid = userid;
		this.username = username;
		this.gameTypeId = gameTypeId;
		this.options = options;
	}
	
	@Override
	public String toString() {
		return "NewMatchRq [userid=" + userid + ", username=" + username + ", gameTypeId=" + gameTypeId + ", options="
				+ Arrays.toString(options) + "]";
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
	public int getGameTypeId() {
		return gameTypeId;
	}
	public void setGameTypeId(int gameTypeId) {
		this.gameTypeId = gameTypeId;
	}
	public int[] getOptions() {
		return options;
	}
	public void setOptions(int[] options) {
		this.options = options;
	}

}
