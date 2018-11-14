package hu.lev.onlinegames.persist;

import hu.lev.onlinegames.model.GameType;
import hu.lev.onlinegames.model.MatchWaiting;
import hu.lev.onlinegames.model.request.MatchStartRq;
import hu.lev.onlinegames.model.request.MatchWaitingRq;

public interface MatchDao {
	public GameType[] getGameTypes();
	public int insertNewMatch(MatchWaitingRq req);
	public MatchWaiting[] getMatchesWaiting();
	public boolean deleteMatchWaiting(int id);
	public boolean isMatchWaiting(MatchStartRq req);
	public int createMatchActive();
}
