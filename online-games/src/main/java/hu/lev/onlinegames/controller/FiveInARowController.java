package hu.lev.onlinegames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hu.lev.onlinegames.model.MatchActive;
import hu.lev.onlinegames.model.fiveinarow.FiveInARowAction;
import hu.lev.onlinegames.model.fiveinarow.FiveInARowField;
import hu.lev.onlinegames.model.fiveinarow.FiveInARowFields;
import hu.lev.onlinegames.service.FiveInARowService;

@RestController
public class FiveInARowController {
	
	@Autowired
	private FiveInARowService fiveInARowService;

    // USER ACTION
    @RequestMapping(value = "/fiveinarow/action", method = RequestMethod.POST)
	@ResponseBody
	public boolean action(@RequestBody MatchActive match) {
    	
    	FiveInARowFields fields = fiveInARowService.convertBoardstate(match.getBoardstate());
    	int[] options = fiveInARowService.convertOptions(match.getOptions());
    	System.out.println(match.getAction());
    	
    	if(fiveInARowService.validateAction(match.getAction(), fields, options)) {
    		boolean win = fiveInARowService.checkWin(fields, match.getPlayers().getActivePlayer(), match.getAction());
        	match.setWin(win);
//        	match = fiveInARowService.applyAction(match);
//        	fiveInARowService.updateMatch(match);
        	return true;
    	}
		return false;
	}

    // CHECK ACTION
    @RequestMapping(value = "/fiveinarow/checkaction", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> checkAction() {
    	return null;
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
