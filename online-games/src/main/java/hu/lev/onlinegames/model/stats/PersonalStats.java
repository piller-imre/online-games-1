package hu.lev.onlinegames.model.stats;

public class PersonalStats {
	private int allMatches;
	private int winMatches;
	private PersonalStatsByType[] statsByGameType;
	
	
	
	public PersonalStats() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PersonalStats(int winMatches, int allMatches, PersonalStatsByType[] statsByGameType) {
		super();
		this.allMatches = allMatches;
		this.winMatches = winMatches;
		this.statsByGameType = statsByGameType;
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
	public PersonalStatsByType[] getStatsByGameType() {
		return statsByGameType;
	}
	public void setStatsByGameType(PersonalStatsByType[] statsByGametype) {
		this.statsByGameType = statsByGametype;
	}
}
