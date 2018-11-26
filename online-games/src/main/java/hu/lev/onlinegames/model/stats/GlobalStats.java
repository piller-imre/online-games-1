package hu.lev.onlinegames.model.stats;

public class GlobalStats {
	private int allMatches;
	private GlobalStatsByGameType[] allMatchesByGameType;
	private ByTypeStatsUsers[] globalRanks;
	
	public int getAllMatches() {
		return allMatches;
	}
	public GlobalStats(int allMatches, GlobalStatsByGameType[] allMatchesByGameType, ByTypeStatsUsers[] globalRanks) {
		super();
		this.allMatches = allMatches;
		this.allMatchesByGameType = allMatchesByGameType;
		this.globalRanks = globalRanks;
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
	public ByTypeStatsUsers[] getGlobalRanks() {
		return globalRanks;
	}
	public void setGlobalRanks(ByTypeStatsUsers[] globalRanks) {
		this.globalRanks = globalRanks;
	}
}

