package hu.lev.onlinegames.model;

public class Players {

	private User player1;
	private User player2;
	private int activePlayer;
	
	// constructors
	public Players() {
		super();
	}
	public Players(User player1, User player2, int activePlayer) {
		super();
		this.player1 = player1;
		this.player2 = player2;
		this.activePlayer = activePlayer;
	}
	
	// toString
	@Override
	public String toString() {
		return "Players [player1=" + player1 + ", player2=" + player2 + ", activePlayer=" + activePlayer + "]";
	}
	
	// getters and setters	
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
