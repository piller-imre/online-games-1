package hu.lev.onlinegames.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "match_active")
public class MatchActive {
	
	@Id
	@GeneratedValue(generator="increment")
	@Column(name = "id")
	private int id;
	
	@OneToOne(mappedBy="match")
//	@OneToOne(fetch = FetchType.EAGER, mappedBy="match", cascade=CascadeType.ALL)
	@JsonIgnore
	private Players players;

	@ManyToOne
	@JoinColumn(name="game_type_fk")
	@JsonIgnore
	private GameType gameType;

	@Column(name="turn")
	private int turn;
		
	@Column(name="board_state")
	@JsonIgnore
	private String boardstate;

	@Column(name="options")
	private String options;
	
	// constructors
	public MatchActive() {
		super();
		this.players = new Players();
		this.boardstate = "";
	}
	public MatchActive(int id, Players players, GameType gameType, int turn, String boardstate) {
		super();
		this.id = id;
		this.players = players;
		this.gameType = gameType;
		this.turn = turn;
		this.boardstate = boardstate;
	}
	
	// toString
	@Override
	public String toString() {
		return "MatchActive [id=" + id
				+ ", players=" + players.toString() 
				+ ", gameType=" + gameType.getGameTypeName() 
				+ ", turn=" + turn
				+ ", boardstate=" + boardstate + "]";
	}
	
	// getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Players getPlayers() {
		return players;
	}
	public void setPlayers(Players players) {
		this.players = players;
	}
	public GameType getGameType() {
		return gameType;
	}
	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	public String getBoardstate() {
		return boardstate;
	}
	public void setBoardstate(String boardstate) {
		this.boardstate = boardstate;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	
	
}
