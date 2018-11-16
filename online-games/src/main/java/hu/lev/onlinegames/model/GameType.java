package hu.lev.onlinegames.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
	private Set<GameTypeOption> gameTypeOptions;

	@OneToMany(fetch = FetchType.EAGER, mappedBy="gameType")
    @JsonIgnore
	private Set<MatchWaiting> newMatch;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="gameType")
    @JsonIgnore
	private Set<MatchActive> matchActive;
	
	public GameType() {
		super();
	}
	
	public GameType(int gameTypeId, String gameTypeName, Set<GameTypeOption> gameTypeOptions) {
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
	public Set<GameTypeOption> getOptions() {
		return gameTypeOptions;
	}
	public void setOptions(Set<Object> list) {
		Set<GameTypeOption> options = new HashSet<>();
		for (Object l : list) {
			options.add((GameTypeOption) l);
		}
		
		this.gameTypeOptions = options;
	}

	public Set<GameTypeOption> getGameTypeOptions() {
		return gameTypeOptions;
	}

	public void setGameTypeOptions(Set<GameTypeOption> gameTypeOptions) {
		this.gameTypeOptions = gameTypeOptions;
	}

	public Set<MatchWaiting> getNewMatch() {
		return newMatch;
	}

	public void setNewMatch(Set<MatchWaiting> newMatch) {
		this.newMatch = newMatch;
	}

	public Set<MatchActive> getMatchActive() {
		return matchActive;
	}

	public void setMatchActive(Set<MatchActive> matchActive) {
		this.matchActive = matchActive;
	}

}
