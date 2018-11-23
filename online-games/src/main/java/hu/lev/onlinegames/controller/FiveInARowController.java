package hu.lev.onlinegames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hu.lev.onlinegames.model.MatchActive;
import hu.lev.onlinegames.model.request.MatchActiveRq;
import hu.lev.onlinegames.service.FiveInARowService;
import hu.lev.onlinegames.service.MatchService;

@RestController
public class FiveInARowController {
	
	@Autowired
	private FiveInARowService fiveInARowService;
	
	@Autowired
	private MatchService matchService;

    // USER ACTION
    @RequestMapping(value = "/fiveinarow/action", method = RequestMethod.POST)
	@ResponseBody
	public boolean action(@RequestBody MatchActiveRq matchRq) {
    	if(fiveInARowService.validateAction(matchRq.getAction(), matchRq.getFields(), matchRq.getOptions())) {
    		boolean win = fiveInARowService.checkWin(matchRq.getFields(), matchRq.getActivePlayer(), matchRq.getAction());
        	matchRq.setCheckedWin(win);
        	System.out.println("WIN: " + win);
        	matchRq = fiveInARowService.applyAction(matchRq);
        	MatchActive matchActive = fiveInARowService.convertMatchRq(matchRq);
        	matchActive.incrementTurn();
//        	System.out.println("NEW TURN: " + matchActive.getTurn());
//        	System.out.println("ACTIVE PLAYER before: " + matchActive.getPlayers().getActivePlayer());
        	matchActive.setActivePlayer();

//        	System.out.println("ACTIVE PLAYER after: " + matchActive.getPlayers().getActivePlayer());
        	matchService.updateMatchActive(matchActive);
        	return true;
    	}
		return false;
	}

    // CHECK ACTION
    @RequestMapping(
    		value = "/fiveinarow/checkaction", 
    		method = RequestMethod.GET)
	@ResponseBody
	public MatchActive checkAction(
        @RequestParam(value = "matchId") int matchId,
        @RequestParam(value = "turn") int turn
			) {
        System.out.println("MATCH ID: " + matchId);
        System.out.println("TURN: " + turn);
    	
    	MatchActive match =  fiveInARowService.checkAction(matchId, turn);
    	
    	return match;
	}

    // TIMEOUT
    @RequestMapping(value = "/fiveinarow/timeout", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> timeOut() {
    	return null;
	}

    // GIVE UP
    @RequestMapping(value = "/fiveinarow/giveup", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> giveUp() {
    	return null;
	}
}
