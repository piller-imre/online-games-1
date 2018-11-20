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
import javax.persistence.Transient;

import hu.lev.onlinegames.model.fiveinarow.FiveInARowAction;

@Entity
@Table(name = "match_active")
public class MatchActive {
	
	@Id
	@GeneratedValue(generator="increment")
	@Column(name = "id")
	private int id;
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy="match", cascade=CascadeType.ALL)
//	@JsonManagedReference
	private Players players;

	@ManyToOne
	@JoinColumn(name="game_type_fk")
//	@JsonIgnore
	private GameType gameType;

	@Column(name="turn")
	private int turn;
		
	@Column(name="board_state")
	private String boardstate;

	@Column(name="options")
	private String options;
	
	@Transient
	private int win;
	
	@Column(name="action")
	private String action;
	
	
	// constructors
	public MatchActive() {
		super();
		this.players = new Players();
		this.boardstate = "";
		this.win = 0;
	}
	public MatchActive(int id, Players players, GameType gameType, int turn, String boardstate, int win, String action) {
		super();
		this.id = id;
		this.players = players;
		this.gameType = gameType;
		this.turn = turn;
		this.boardstate = boardstate;
		this.win = win;
		this.action = action;
	}
	
	// toString
	@Override
	public String toString() {
		return "MatchActive [id=" + id
				+ ", players=" + players.toString() 
				+ ", gameType=" + gameType.getGameTypeName() 
				+ ", turn=" + turn
				+ ", boardstate=" + boardstate 
				+ ", win= " + win 
				+ "]";
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
	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}
	public void incrementTurn() {
		this.turn++;
	}
	public void setActivePlayer() {
		if(this.players.getActivePlayer() == 1) {
			this.players.setActivePlayer(2);
		} else {
			this.players.setActivePlayer(1);
		}
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	
}
