package hu.lev.onlinegames.model.stats;

public class ByTypeStats {
	
	private String gameType;
	private ByTypeStatsUsers[] results;
	
	
	
	public ByTypeStats() {
		super();
	}
	public ByTypeStats(String gameType, ByTypeStatsUsers[] results) {
		super();
		this.gameType = gameType;
		this.results = results;
	}
	
	public String getGameType() {
		return gameType;
	}
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	public ByTypeStatsUsers[] getResults() {
		return results;
	}
	public void setResults(ByTypeStatsUsers[] results) {
		this.results = results;
	}
	
}
