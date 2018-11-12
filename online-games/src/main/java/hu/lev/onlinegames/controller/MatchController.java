package hu.lev.onlinegames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hu.lev.onlinegames.model.GameType;
import hu.lev.onlinegames.model.request.MatchWaitingRq;
import hu.lev.onlinegames.model.response.MatchWaitingResp;
import hu.lev.onlinegames.service.MatchService;

@RestController
public class MatchController {
	
	@Autowired
	private MatchService matchService;

    // GET MATCH LIST
    @RequestMapping(
    		value = "/match", 
    		method = RequestMethod.GET,
			produces = {"application/json"})
	@ResponseBody
	public MatchWaitingResp[] getList() {
    	MatchWaitingResp[] matches = matchService.getMatchesWaiting();
    	return matches;
	}

    // CREATE MATCH
    @RequestMapping(
    		value = "/match", 
    		method = RequestMethod.POST,
			produces = {"application/json"})
	@ResponseBody
	public int createNewMatch(@RequestBody MatchWaitingRq req) {    
    	int success = 0;
    	
    	// validate
    	if(req.getUserid() > 0
    			&& req.getUsername() != null
    			&& req.getUsername() != ""
    			&& req.getGameTypeId() > 0) {
    		if(req.getOptions() == null) {
    			req.setOptions(new int[] {});
    		}
    		
    		// insert
    		int id = matchService.createNewMatch(req);
    		
        	// check success
    		if(id > 0) {
    			success = 1;
    		}
    	}
    	
    	return success;
	}

    // DELETE MATCH
    @RequestMapping(value = "/match", method = RequestMethod.DELETE)
	@ResponseBody
	public boolean delete(@RequestBody int id) {
    	boolean success = false;
    	
    	if(id > 0) {
        	matchService.deleteMatchWaiting(id);
    	}
    	
    	return success;
	}

    // ACCEPT AND START NEW MATCH
    @RequestMapping(value = "/match/start", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> start() {
    	return null;
	}

    // CHECK ACTION HAPPENING
	@RequestMapping(value = "/match/start", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> checkStart() {
		return null;
	}

	// GET GAMETYPE LIST
	@RequestMapping(
			value = "/gametypes", 
			method = RequestMethod.GET,
			produces = {"application/json"})
	@ResponseBody
	public GameType[] getGameTypes() {
		GameType[] gameTypes = matchService.getGameTypes();
		return gameTypes;
	}
}
