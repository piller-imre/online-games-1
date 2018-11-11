package hu.lev.onlinegames.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hu.lev.onlinegames.model.Token;
import hu.lev.onlinegames.model.User;
import hu.lev.onlinegames.model.request.LoginRq;
import hu.lev.onlinegames.model.request.RegisterRq;
import hu.lev.onlinegames.service.UserService;

@RestController
public class UserController {
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
		
	
	@Autowired
	private UserService userService;

    // REGISTER USER
    @RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseBody
	public int register(@RequestBody RegisterRq req) {
    	int id = -1;
    	if(req.getUsername() != null
	    		&& req.getPassword() != null
	    		&& req.getPasswordConfirmed() != null
	    		&& req.getPassword().equals(req.getPasswordConfirmed())
	    		&& userService.validateEmail(req.getEmail())){
    		
    		String password = userService.getHash(req.getPassword()); // get hashcode of password
			User user = new User(
					req.getUsername(),
					password,
					req.getEmail());
    		id = userService.registerUser(user);
    	}
    	return id;
	}

    // UPDATE USER
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> update() {
    	return null;
	}

    // DELETE USER
    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<String> delete() {
    	return null;
	}

    // LOGIN USER
    @RequestMapping(value = "/user/authenticate", method = RequestMethod.POST)
	@ResponseBody
	public Token authenticate(@RequestBody LoginRq req) {
		Token token = null;
		if(req.getUsername() != null || req.getPassword() != null) {
			String password = userService.getHash(req.getPassword()); // get hashcode of password
			User user = new User(req.getUsername(), password);
			
			token = userService.authenticate(user);
		}
		return token;
	}

    // FORGOTTEN PASSWORD
	@RequestMapping(value = "/user/forgottenpassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> forgottenpassword() {
		return null;
	}
}
