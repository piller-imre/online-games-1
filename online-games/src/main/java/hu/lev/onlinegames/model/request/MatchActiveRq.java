package hu.lev.onlinegames.model.request;

import hu.lev.onlinegames.model.User;
import hu.lev.onlinegames.model.fiveinarow.FiveInARowAction;
import hu.lev.onlinegames.model.fiveinarow.FiveInARowField;

public class MatchActiveRq {

	private int matchId;
	private User player1;
	private User player2;
	private int activePlayer;
	private int turn;
	private String boardstate;
	private int[] options;
	private int win;
	private FiveInARowAction action;
	private FiveInARowField[][] fields;
	
	// constructors
	public MatchActiveRq() {
		super();
	}

	public MatchActiveRq(int matchId, User player1, User player2, int activePlayer, int turn,
			String boardstate, int[] options, int win, FiveInARowAction action, FiveInARowField[][] fields) {
		super();
		this.matchId = matchId;
		this.player1 = player1;
		this.player2 = player2;
		this.activePlayer = activePlayer;
		this.turn = turn;
		this.boardstate = boardstate;
		this.options = options;
		this.win = win;
		this.action = action;
		this.fields = fields;
	}

	// toString
	@Override
	public String toString() {
		return "MatchActive [matchId=" + matchId
				+ ", player1=" + player1 
				+ ", player2=" + player2 
				+ ", activePlayer=" + activePlayer 
				+ ", turn=" + turn
				+ ", boardstate=" + boardstate 
				+ ", options=" + options.toString() 
				+ ", win= " + win 
				+ ", action= " + action.toString()
				+ "]";
	}
	
	// getters and setters
	public void setCheckedWin(boolean win) {
		if(win) {
			this.win = activePlayer;
		} else {
			this.win = 0;
		}
	}
	public int getMatchId() {
		return matchId;
	}
	public void setMatchId(int id) {
		this.matchId = id;
	}
	public User getPlayer1() {
		return player1;
	}
	public void setPlayer1(User player1) {
		this.player1 = player1;
	}
	public User getPlayer2() {
		return player2;
	}
	public void setPlayer2(User player2) {
		this.player2 = player2;
	}
	public int getActivePlayer() {
		return activePlayer;
	}
	public void setActivePlayer(int activePlayer) {
		this.activePlayer = activePlayer;
	}
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	public String getBoardstate() {
		return boardstate;
	}
	public void setBoardstate(String boardstate) {
		this.boardstate = boardstate;
	}
	public int[] getOptions() {
		return options;
	}
	public void setOptions(int[] options) {
		this.options = options;
	}
	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}
	public FiveInARowAction getAction() {
		return action;
	}
	public void setAction(FiveInARowAction action) {
		this.action = action;
	}
	public FiveInARowField[][] getFields() {
		return fields;
	}
	public void setFields(FiveInARowField[][] fields) {
		this.fields = fields;
	}
	
	
	
}
