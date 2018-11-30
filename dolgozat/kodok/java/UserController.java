package hu.lev.onlinegames.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {

    // REGISTER USER
    @RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> register() {
		
	}

    // UPDATE USER
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> update() {
		
	}

    // DELETE USER
    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<String> delete() {
		
	}

    // LOGIN USER
    @RequestMapping(value = "/user/authenticate", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> authenticate() {
		
	}

    // FORGOTTEN PASSWORD
	@RequestMapping(value = "/user/forgottenpassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> forgottenpassword() {
		
	}
}