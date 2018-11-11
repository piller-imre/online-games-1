package hu.lev.onlinegames.persist;

import hu.lev.onlinegames.model.GameType;

public interface MatchDao {
	public GameType[] getGameTypes();
}
