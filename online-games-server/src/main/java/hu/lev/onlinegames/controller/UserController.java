package hu.lev.onlinegames.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	// @Autowired
	// private AuthService authService;
	
	// @Autowired
	// private TokenService tokenService;
	
	// @Autowired
	// private UserService userService;
	

    // REGISTER USER
    @RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> register() {
    	return null;
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
	public ResponseEntity<String> authenticate() {
    	return null;
	}

    // FORGOTTEN PASSWORD
	@RequestMapping(value = "/user/forgottenpassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> forgottenpassword() {
		return null;
	}
}
