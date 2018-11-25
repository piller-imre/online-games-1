package hu.lev.onlinegames.persist;

import hu.lev.onlinegames.model.GameType;
import hu.lev.onlinegames.model.MatchActive;
import hu.lev.onlinegames.model.MatchDone;
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
	public int createAndInsertMatchActive(int acceptingUserId, MatchWaiting matchWaiting, String fields);
	public MatchActive getMatchActive(int id);
	public void updateMatchActive(MatchActive match);
	public boolean checkAction(int matchId, int turn);
	public boolean deleteMatchActive(int id);
	public boolean isWinner(int matchId);
	public int insertMatchDone(MatchDone matchDone);
	public MatchDone getMatchDone(GameType gameType, User player);
	public MatchDone createMatchDone(GameType gameType, User player, boolean win);
	public void updateMatchDone(MatchDone matchDone, boolean win);
}
