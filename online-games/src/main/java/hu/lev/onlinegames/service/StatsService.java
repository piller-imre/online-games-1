package hu.lev.onlinegames.service;

import hu.lev.onlinegames.model.stats.ByTypeStats;
import hu.lev.onlinegames.model.stats.GlobalStats;
import hu.lev.onlinegames.model.stats.PersonalStats;

public interface StatsService {

	// global stats
	GlobalStats getGlobalStats();
	
	// byType stats
	ByTypeStats[] getByTypeStats();
	
	// personal stats
	PersonalStats getPersonalStats(int id);
}
