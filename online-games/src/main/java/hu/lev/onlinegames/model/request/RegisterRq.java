package hu.lev.onlinegames.model.request;

public class RegisterRq {
	private String username;
	private String password;
	private String passwordConfirmed;
	private String email;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirmed() {
		return passwordConfirmed;
	}
	public void setPasswordConfirmed(String passwordConfirmed) {
		this.passwordConfirmed = passwordConfirmed;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
}
