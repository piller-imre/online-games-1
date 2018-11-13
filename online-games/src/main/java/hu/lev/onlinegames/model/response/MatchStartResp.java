package hu.lev.onlinegames.model.response;

import hu.lev.onlinegames.model.Players;

public class MatchStartResp {
	
	private int id;
	private Players players;
	private int turn;
	private int winner;
	private int gameTypeId;
	private String gameTypeName;
	private int[] options;
	
	// constructors
	public MatchStartResp() {
		super();
	}

	// getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Players getPlayers() {
		return players;
	}

	public void setPlayers(Players players) {
		this.players = players;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

	public int getGameTypeId() {
		return gameTypeId;
	}

	public void setGameTypeId(int gameTypeId) {
		this.gameTypeId = gameTypeId;
	}

	public String getGameTypeName() {
		return gameTypeName;
	}

	public void setGameTypeName(String gameTypeName) {
		this.gameTypeName = gameTypeName;
	}

	public int[] getOptions() {
		return options;
	}

	public void setOptions(int[] options) {
		this.options = options;
	}
	
	
}