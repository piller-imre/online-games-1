package hu.lev.onlinegames.model.stats;

public class GlobalStats {
	private int allMatches;
	private GlobalStatsByGameType[] allMatchesByGameType;
	private UserStats[] globalRanks;
	
	public int getAllMatches() {
		return allMatches;
	}
	public void setAllMatches(int allMatches) {
		this.allMatches = allMatches;
	}
	public GlobalStatsByGameType[] getAllMatchesByGameType() {
		return allMatchesByGameType;
	}
	public void setAllMatchesByGameType(GlobalStatsByGameType[] allMatchesByGameType) {
		this.allMatchesByGameType = allMatchesByGameType;
	}
	public UserStats[] getGlobalRanks() {
		return globalRanks;
	}
	public void setGlobalRanks(UserStats[] globalRanks) {
		this.globalRanks = globalRanks;
	}
}

