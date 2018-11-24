package hu.lev.onlinegames.persist;

import hu.lev.onlinegames.model.GameType;
import hu.lev.onlinegames.model.MatchActive;
import hu.lev.onlinegames.model.MatchWaiting;
import hu.lev.onlinegames.model.User;
import hu.lev.onlinegames.model.request.MatchWaitingRq;

public interface MatchDao {
	public GameType[] getGameTypes();
	
	public int insertMatchWaiting(MatchWaitingRq req);
	public MatchWaiting[] getMatchesWaiting();
	public boolean deleteMatchWaiting(int id);
	public MatchWaiting getMatchWaiting(int id);
	
	public int getMatchActiveId(int userId);
	public int createMatchActive(int acceptingUserId, MatchWaiting matchWaiting, String fields);
	public MatchActive getMatchActive(int id);
	public MatchActive checkStart(int userId);

	public void updateMatchActive(MatchActive match);
	public boolean checkAction(int matchId, int turn);
	public String toString();
}
