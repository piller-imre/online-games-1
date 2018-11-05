package hu.lev.onlinegames.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "game_type")
public class GameType {

	@Id
	@GeneratedValue(generator="increment")
	@Column(name = "id")
	private int gameTypeId;
	
	@Column(name = "name")
	private String gameTypeName;
	
	public int getGameTypeId() {
		return gameTypeId;
	}
	public void setGameTypeId(int gameTypeId) {
		this.gameTypeId = gameTypeId;
	}
	public String getGameTypeName() {
		return gameTypeName;
	}
	public void setGameTypeName(String gameTypeName) {
		this.gameTypeName = gameTypeName;
	}
	@Override
	public String toString() {
		return "GameType [gameTypeId=" + gameTypeId + ", gameTypeName=" + gameTypeName + "]";
	}
	
	
}
