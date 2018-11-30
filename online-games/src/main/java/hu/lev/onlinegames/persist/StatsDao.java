package hu.lev.onlinegames.persist;

import hu.lev.onlinegames.model.stats.ByTypeStats;
import hu.lev.onlinegames.model.stats.GlobalStatsByGameType;
import hu.lev.onlinegames.model.stats.PersonalStats;
import hu.lev.onlinegames.model.stats.PersonalStatsByType;
import hu.lev.onlinegames.model.stats.ByTypeStatsUsers;

public interface StatsDao {

	// global stats
	int getNumberOfAllMatches();
	GlobalStatsByGameType[] getNumberOfAllMatchesByGameType();
	ByTypeStatsUsers[] getGlobalRanks();
	
	// byType stats
	ByTypeStats[] getByTypeStats();
	
	// personal stats
	PersonalStats setPersonalWinAndTotal(PersonalStats temp, int id);
	PersonalStatsByType[] getPersonalStatsByGameType(int id);
	
}
