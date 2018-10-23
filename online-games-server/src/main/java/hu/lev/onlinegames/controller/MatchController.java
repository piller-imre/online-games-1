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
		
	}

    // CREATE MATCH
    @RequestMapping(value = "/match", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> create() {
		
	}

    // DELETE MATCH
    @RequestMapping(value = "/match", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<String> delete() {
		
	}

    // ACCEPT AND START NEW MATCH
    @RequestMapping(value = "/match/start", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> start() {
		
	}

    // CHECK ACTION HAPPENING
	@RequestMapping(value = "/match/start", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> checkStart() {
		
	}
}
