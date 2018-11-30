package hu.lev.onlinegames.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FiveInARowController {

    // USER ACTION
    @RequestMapping(value = "/fiveinarow/action", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> action() {
		
	}

    // CHECK ACTION
    @RequestMapping(value = "/fiveinarow/checkaction", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> checkAction() {
		
	}

    // TIMEOUT
    @RequestMapping(value = "/fiveinarow/timeout", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> timeOut() {
		
	}

    // GIVE UP
    @RequestMapping(value = "/fiveinarow/giveup", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> giveUp() {
		
	}

