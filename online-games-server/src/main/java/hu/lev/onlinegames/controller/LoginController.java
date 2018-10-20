package hu.lev.onlinegames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hu.lev.onlinegames.model.User;
import hu.lev.onlinegames.model.request.LoginRq;
import hu.lev.onlinegames.model.request.RegisterRq;
import hu.lev.onlinegames.service.AuthService;
import hu.lev.onlinegames.service.TokenService;
import hu.lev.onlinegames.service.UserService;

@RestController
public class LoginController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> test() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body("Fasza");
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> login(@RequestBody LoginRq req) {
		if(req.getUsername() != null || req.getPassword() != null) {
			String password = authService.getHash(req.getPassword()); // get hashcode of password
			User user = new User(req.getUsername(), password);
			
			int userId = authService.authenticate(user);

			if(userId > 0) {
				String token = tokenService.createJWT(user.getUsername(), 10000);
				tokenService.saveToken(userId, token);
				return ResponseEntity
						.status(HttpStatus.OK)
						.body(token);
			}
		}
		// case of missing or incorrect fields
		return ResponseEntity
				.status(HttpStatus.FORBIDDEN)
				.body("Incorrect username or password");
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> register(@RequestBody RegisterRq req) {
		if(req.getUsername() != null &&
			req.getPassword() != null &&
			req.getPasswordConfirmed() != null &&
			req.getEmail() != null &&
			userService.validateEmail(req.getEmail()) &&
			req.getPassword() == req.getPasswordConfirmed()) {
			
			String password = authService.getHash(req.getPassword()); // get hashcode of password
			User user = new User(
					req.getUsername(),
					password,
					req.getEmail());
			boolean saveSuccess = userService.registerUser(user);
			
			if(saveSuccess) {
				return ResponseEntity
						.status(HttpStatus.CREATED)			// 201
						.body("Registration successfull, please login to have fun!");
			} else {
				return ResponseEntity
						.status(HttpStatus.NOT_ACCEPTABLE)	// 406
						.body("User already exists (or server error)");
			}
		}

		// case of missing or incorrect fields
		return ResponseEntity
				.status(HttpStatus.EXPECTATION_FAILED)
				.body("Incorrect fields");
	}
}
