package hu.lev.onlinegames.model.stats;

public class GlobalStatsByGameType {
	
	private String gameType;
	private int matches;
	
	
	public GlobalStatsByGameType() {
		super();
	}
	public GlobalStatsByGameType(String gameType, int matches) {
		super();
		this.gameType = gameType;
		this.matches = matches;
	}
	
	public String getGameType() {
		return gameType;
	}
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	public int getMatches() {
		return matches;
	}
	public void setMatches(int matches) {
		this.matches = matches;
	}
}
