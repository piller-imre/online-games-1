package hu.lev.onlinegames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hu.lev.onlinegames.model.stats.ByTypeStats;
import hu.lev.onlinegames.model.stats.GlobalStats;
import hu.lev.onlinegames.model.stats.PersonalStats;
import hu.lev.onlinegames.service.StatsService;

@RestController
public class StatsController {

	// init stats service
	@Autowired
	StatsService statsService;
	
	// GET STATS
    @RequestMapping(value = "/stats/{id}", method = RequestMethod.GET)
	@ResponseBody
	public void stats(@PathVariable int id) {
    	// get global stats
    	GlobalStats globalStats = statsService.getGlobalStats();
    	
    	// get byType stats
    	ByTypeStats[] byTypeStats = statsService.getByTypeStats();
    	
    	// get personal stats
    	PersonalStats personalStats = statsService.getPersonalStats(id);
    	
	}
    

}
