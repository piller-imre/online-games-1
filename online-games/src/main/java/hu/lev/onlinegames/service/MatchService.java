package hu.lev.onlinegames.service;

import hu.lev.onlinegames.model.GameType;
import hu.lev.onlinegames.model.request.MatchWaitingRq;
import hu.lev.onlinegames.model.response.MatchWaitingResp;

public interface MatchService {
	public GameType[] getGameTypes();
	public int createNewMatch(MatchWaitingRq req);
	public MatchWaitingResp[] getMatchesWaiting();
	public boolean deleteMatchWaiting(int id);
}
