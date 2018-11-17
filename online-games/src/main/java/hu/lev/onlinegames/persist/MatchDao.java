package hu.lev.onlinegames.persist;

import hu.lev.onlinegames.model.GameType;
import hu.lev.onlinegames.model.MatchActive;
import hu.lev.onlinegames.model.MatchWaiting;
import hu.lev.onlinegames.model.request.MatchWaitingRq;

public interface MatchDao {
	public GameType[] getGameTypes();
	public int insertNewMatch(MatchWaitingRq req);
	public MatchWaiting[] getMatchesWaiting();
	public boolean deleteMatchWaiting(int id);
	public MatchWaiting getMatchWaiting(int id);
	public int createMatchActive(int acceptingUserId, MatchWaiting matchWaiting);
	public MatchActive getMatchActive(int id);
	public MatchActive checkStart(int userId);
}
