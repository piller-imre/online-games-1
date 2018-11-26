package hu.lev.onlinegames.model.stats;

public class Stats {

	private GlobalStats globalStats;
	private ByTypeStats[] statsByGameType;
	private PersonalStats personalStats;
	
	public GlobalStats getGlobalStats() {
		return globalStats;
	}
	public void setGlobalStats(GlobalStats globalStats) {
		this.globalStats = globalStats;
	}
	public ByTypeStats[] getStatsByGameType() {
		return statsByGameType;
	}
	public void setStatsByGameType(ByTypeStats[] statsByGameType) {
		this.statsByGameType = statsByGameType;
	}
	public PersonalStats getPersonalStats() {
		return personalStats;
	}
	public void setPersonalStats(PersonalStats personalStats) {
		this.personalStats = personalStats;
	}
}
