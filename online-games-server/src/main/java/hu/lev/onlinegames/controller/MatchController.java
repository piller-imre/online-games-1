package hu.lev.onlinegames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hu.lev.onlinegames.model.GameType;
import hu.lev.onlinegames.service.MatchService;

@RestController
public class MatchController {
	
	@Autowired
	private MatchService matchService;

    // GET MATCH LIST
    @RequestMapping(value = "/match", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> getList() {
    	return null;
	}

    // CREATE MATCH
    @RequestMapping(value = "/match", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> create() {
    	return null;
	}

    // DELETE MATCH
    @RequestMapping(value = "/match", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<String> delete() {
    	return null;
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
	@RequestMapping(value = "/gametypes", method = RequestMethod.GET)
	@ResponseBody
	public GameType[] getGameTypes() {
		GameType[] gameTypes = matchService.getGameTypes();
		return gameTypes;
	}
}
