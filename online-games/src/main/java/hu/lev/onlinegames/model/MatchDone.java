package hu.lev.onlinegames.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "match_done")
public class MatchDone {

	@Id
	@GeneratedValue(generator = "increment")
	@Column(name = "id")
	private int id;
	
	@Column(name="date")
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name="game_type_fk")
	private GameType gameType;
	
	@Column(name="match_count_total")
	private int matchCountTotal;

	@Column(name="match_won_total")
	private int matchWinTotal;

	
	@ManyToOne
	@JoinColumn(name="player_fk")
	private User player;

	public MatchDone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MatchDone(int id, LocalDate date, GameType gameType, int matchCountTotal, int matchWinTotal, User player) {
		super();
		this.id = id;
		this.date = date;
		this.gameType = gameType;
		this.matchCountTotal = matchCountTotal;
		this.matchWinTotal = matchWinTotal;
		this.player = player;
	}

	@Override
	public String toString() {
		return "MatchDone [id=" + id 
				+ ", date=" + date 
				+ ", gameType=" + gameType.getGameTypeName() 
				+ ", matchCountTotal=" + matchCountTotal 
				+ ", matchWinTotal=" + matchWinTotal 
				+ ", player=" + player.getUsername() 
				+ "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	public int getMatchCountTotal() {
		return matchCountTotal;
	}

	public void setMatchCountTotal(int matchCountTotal) {
		this.matchCountTotal = matchCountTotal;
	}

	public int getMatchWinTotal() {
		return matchWinTotal;
	}

	public void setMatchWinTotal(int matchWinTotal) {
		this.matchWinTotal = matchWinTotal;
	}

	public User getPlayer() {
		return player;
	}

	public void setPlayer(User player) {
		this.player = player;
	}
	
	public void incrementCountTotal() {
		this.matchCountTotal++;
	}
	
	public void incrementWinTotal() {
		this.matchWinTotal++;
	}
}
