package hu.lev.onlinegames.controller;

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
