package hu.lev.onlinegames.model.response;

public class MatchWaitingResp {
	private int id;
	private int userid;
	private String username;
	private int gameTypeId;
	private String options;
	
	
	public MatchWaitingResp() {
		super();
	}
	public MatchWaitingResp(int id, int userid, String username, int gameType, String options) {
		super();
		this.id = id;
		this.userid = userid;
		this.username = username;
		this.gameTypeId = gameType;
		this.options = options;
	}

	
	@Override
	public String toString() {
		return "MatchWaitingResp [id=" + id + ", userid=" + userid + ", username=" + username + ", gameType=" + gameTypeId
				+ ", options=" + options + "]";
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public void setGameTypeId(int gameType) {
		this.gameTypeId = gameType;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	
	
}
