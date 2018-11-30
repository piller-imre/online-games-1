package hu.lev.onlinegames.model.request;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import hu.lev.onlinegames.model.MatchActive;
import hu.lev.onlinegames.model.User;

public class PlayersRq {

	private int match;
	private int player1;
	private int player2;
	private int activePlayer;
	
	// constructors
	public PlayersRq() {
		super();
	}	
	public PlayersRq(int match, int player1, int player2, int activePlayer) {
		super();
		this.match = match;
		this.player1 = player1;
		this.player2 = player2;
		this.activePlayer = activePlayer;
	}

	// toString
	@Override
	public String toString() {
		return "Players [player1=" + player1 
				+ ", player2=" + player2
				+ ", activePlayer=" + activePlayer 
				+ ", matchId=" + match + "]";
	}
	
	// getters and setters	
	public int getMatch() {
		return match;
	}
	public void setMatch(int match) {
		this.match = match;
	}
	public int getPlayer1() {
		return player1;
	}
	public void setPlayer1(int player1) {
		this.player1 = player1;
	}
	public int getPlayer2() {
		return player2;
	}
	public void setPlayer2(int player2) {
		this.player2 = player2;
	}
	public int getActivePlayer() {
		return activePlayer;
	}
	public void setActivePlayer(int activePlayer) {
		this.activePlayer = activePlayer;
	}

	
}
