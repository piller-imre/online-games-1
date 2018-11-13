package hu.lev.onlinegames.model.request;

public class MatchStartRq {
	
	private int matchId;
	private int userid;
	
	// constructors
	public MatchStartRq() {
		super();
	}
	public MatchStartRq(int matchId, int userid) {
		super();
		this.matchId = matchId;
		this.userid = userid;
	}

	// toString
	@Override
	public String toString() {
		return "MatchStartRq [matchId=" + matchId + ", userid=" + userid + "]";
	}
	
	// getters and setters
	public int getMatchId() {
		return matchId;
	}
	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
}
