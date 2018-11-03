package hu.lev.onlinegames.model.stats;

public class PersonalStats {
	private int allMatches;
	private int winMatches;
	private PersonalRanks[] statsByGametype;
	
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
	public PersonalRanks[] getStatsByGametype() {
		return statsByGametype;
	}
	public void setStatsByGametype(PersonalRanks[] statsByGametype) {
		this.statsByGametype = statsByGametype;
	}
}
