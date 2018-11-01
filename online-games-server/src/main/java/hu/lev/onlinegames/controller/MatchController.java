package hu.lev.onlinegames.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {

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
}
