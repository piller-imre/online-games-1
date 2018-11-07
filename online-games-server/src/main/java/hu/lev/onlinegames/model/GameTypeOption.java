package hu.lev.onlinegames.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "game_type_option")
@Entity
public class GameTypeOption {

	@Id
	@GeneratedValue(generator="increment")
	@Column(name = "option_id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;
	
	@ManyToOne
	private GameType gameType;

	
	
	public GameTypeOption() {
		super();
	}

	public GameTypeOption(int id, String name, String description, GameType gameType) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.gameType = gameType;
	}

	@Override
	public String toString() {
		return "GameTypeOption [id=" + id 
				+ ", name=" + name 
				+ ", description=" + description
				+ ", gameType=" + gameType
				+ "]";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public GameType getGameType() {
		return gameType;
	}
	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}
}
