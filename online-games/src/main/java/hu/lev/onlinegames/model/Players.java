package hu.lev.onlinegames.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "match_players")
public class Players implements Serializable {
	// default, Serializable needs it
	private static final long serialVersionUID = 1L;

	@Id
	@OneToOne
	@JoinColumn(name="match_fk")
	private MatchActive match;
	
	@OneToOne
	@JoinColumn(name="player1_fk")
	private User player1;
	
	@OneToOne
	@JoinColumn(name="player2_fk")
	private User player2;

	@Column(name = "active_player")
	private int activePlayer;
	
	// constructors
	public Players() {
		super();
	}
	public Players(User player1, User player2, int activePlayer, MatchActive match) {
		super();
		this.player1 = player1;
		this.player2 = player2;
		this.activePlayer = activePlayer;
		this.match = match;
	}
	
	// toString
	@Override
	public String toString() {
		return "Players [player1=" + player1.getUsername() 
				+ ", player2=" + player2.getUsername() 
				+ ", activePlayer=" + activePlayer 
				+ ", matchId=" + match.getId() + "]";
	}
	
	// getters and setters	
	public MatchActive getMatchId() {
		return match;
	}
	public void setMatchId(MatchActive match) {
		this.match = match;
	}
	public User getPlayer1() {
		return player1;
	}
	public void setPlayer1(User player1) {
		this.player1 = player1;
	}
	public User getPlayer2() {
		return player2;
	}
	public void setPlayer2(User player2) {
		this.player2 = player2;
	}
	public int getActivePlayer() {
		return activePlayer;
	}
	public void setActivePlayer(int activePlayer) {
		this.activePlayer = activePlayer;
	}
	
}
