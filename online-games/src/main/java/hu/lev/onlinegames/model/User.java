package hu.lev.onlinegames.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(generator="increment")
	private int id;
	private String username;
	private String password;
	private String email;
	private String token;

	@OneToMany(fetch = FetchType.EAGER, mappedBy="user")
//    @JsonIgnore
	private Set<MatchWaiting> newMatch;

	// constructors
	public User() {}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	

	@Override
	public String toString() {
		return "User [id=" + id 
				+ ", username=" + username 
				+ ", password=" + password 
				+ ", email=" + email 
				+ ", token=" + token + "]";
	}

	// getters, setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public Set<MatchWaiting> getNewMatch() {
		return newMatch;
	}

	public void setNewMatch(Set<MatchWaiting> newMatch) {
		this.newMatch = newMatch;
	}
	
}