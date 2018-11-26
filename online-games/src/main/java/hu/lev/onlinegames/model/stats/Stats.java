package hu.lev.onlinegames.model.stats;

public class Stats {

	private GlobalStats globalstats;
	private ByTypeStats[] statsByGameType;
	private PersonalStats personalStats;
	
	public GlobalStats getGlobalstats() {
		return globalstats;
	}
	public void setGlobalstats(GlobalStats globalstats) {
		this.globalstats = globalstats;
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
