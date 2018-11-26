package hu.lev.onlinegames.model.stats;

public class PersonalStatsByType {
	private String gameType;
	private int allMatches;
	private int winMatches;
	// private int rank;
	
	
	public PersonalStatsByType() {
		super();
	}
	public PersonalStatsByType(String gameType, int winMatches, int allMatches) {
		super();
		this.gameType = gameType;
		this.allMatches = allMatches;
		this.winMatches = winMatches;
	}
	
	public String getGameType() {
		return gameType;
	}
	public void setGameType(String gameType) {
		this.gameType = gameType;
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
