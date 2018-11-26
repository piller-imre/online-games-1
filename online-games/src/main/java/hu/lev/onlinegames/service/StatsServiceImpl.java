package hu.lev.onlinegames.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.lev.onlinegames.model.stats.ByTypeStats;
import hu.lev.onlinegames.model.stats.GlobalStats;
import hu.lev.onlinegames.model.stats.GlobalStatsByGameType;
import hu.lev.onlinegames.model.stats.PersonalStats;
import hu.lev.onlinegames.model.stats.ByTypeStatsUsers;
import hu.lev.onlinegames.persist.StatsDao;

@Service
public class StatsServiceImpl implements StatsService {

	@Autowired
	StatsDao statsDao;

	
	// global stats

	@Override
	public GlobalStats getGlobalStats() {
		int allMatches = statsDao.getNumberOfAllMatches();
		GlobalStatsByGameType[] allMatchesByGameType = statsDao.getNumberOfAllMatchesByGameType();
		ByTypeStatsUsers[] globalRanks = statsDao.getGlobalRanks();
		GlobalStats globalStats = new GlobalStats(allMatches, allMatchesByGameType, globalRanks);
		return globalStats;
	}
	
	
	// byType stats

	@Override
	public ByTypeStats[] getByTypeStats() {
		ByTypeStats[] stats = statsDao.getByTypeStats();
		return stats;
	}
	
	
	// personal stats

	@Override
	public PersonalStats getPersonalStats(int id) {
		PersonalStats temp = new PersonalStats();
		PersonalStats stats = statsDao.setPersonalWinAndTotal(temp, id);
		stats.setStatsByGameType(statsDao.getPersonalStatsByGameType(id));
		return stats;
	}
	
}





