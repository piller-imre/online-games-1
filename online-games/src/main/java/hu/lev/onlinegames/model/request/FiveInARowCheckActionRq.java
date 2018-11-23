package hu.lev.onlinegames.model.request;

public class FiveInARowCheckActionRq {

	int matchId;
	int turn;
	
	public FiveInARowCheckActionRq() {
		super();
	}
	public FiveInARowCheckActionRq(int matchId, int turn) {
		super();
		this.matchId = matchId;
		this.turn = turn;
	}
	
	@Override
	public String toString() {
		return "FiveInARowCheckActionRq [matchId=" + matchId + ", turn=" + turn + "]";
	}
	
	public int getMatchId() {
		return matchId;
	}
	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	
}
