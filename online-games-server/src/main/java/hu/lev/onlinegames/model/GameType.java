package hu.lev.onlinegames.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "game_type")
public class GameType {

	@Id
	@GeneratedValue(generator="increment")
	@Column(name = "id")
	private int gameTypeId;
	
	@Column(name = "name")
	private String gameTypeName;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="gameType")
	private List<GameTypeOption> gameTypeOptions;
	
	
	public GameType() {
		super();
	}
	
	public GameType(int gameTypeId, String gameTypeName, List<GameTypeOption> gameTypeOptions) {
		super();
		this.gameTypeId = gameTypeId;
		this.gameTypeName = gameTypeName;
		this.gameTypeOptions = gameTypeOptions;
	}

	@Override
	public String toString() {
		String opString = "";
		for (GameTypeOption gameTypeOption : gameTypeOptions) {
			opString += gameTypeOption.toString() + "  ";
		}
		return "GameType [gameTypeId=" + gameTypeId 
				+ ", gameTypeName=" + gameTypeName 
				+ ", options= " + opString
				+ "]";
	}

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
	public List<GameTypeOption> getOptions() {
		return gameTypeOptions;
	}
	public void setOptions(List<Object> list) {
		List<GameTypeOption> options = new ArrayList<>();
		for (Object l : list) {
			options.add((GameTypeOption) l);
		}
		
		this.gameTypeOptions = options;
	}
}
