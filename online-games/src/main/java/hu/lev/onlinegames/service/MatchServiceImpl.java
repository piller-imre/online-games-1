package hu.lev.onlinegames.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.lev.onlinegames.model.GameType;
import hu.lev.onlinegames.model.MatchActive;
import hu.lev.onlinegames.model.MatchWaiting;
import hu.lev.onlinegames.model.request.MatchStartRq;
import hu.lev.onlinegames.model.request.MatchWaitingRq;
import hu.lev.onlinegames.model.response.MatchWaitingResp;
import hu.lev.onlinegames.persist.MatchDao;

@Service
public class MatchServiceImpl implements MatchService {
	
	@Autowired
	private MatchDao matchDao;
	
	public MatchServiceImpl() {
		super();
	}

	@Override
	public GameType[] getGameTypes() {
		 return matchDao.getGameTypes();
	}

	@Override
	public int createNewMatch(MatchWaitingRq req) {
		return matchDao.insertMatchWaiting(req);
	}

	
	@Override
	public MatchWaitingResp[] getMatchesWaiting() {
		MatchWaiting[] matchesRaw = matchDao.getMatchesWaiting();
		MatchWaitingResp[] matches = new MatchWaitingResp[matchesRaw.length];
		
		for (int i = 0; i < matchesRaw.length; i++) {
			matches[i] = matchesRaw[i].toMatchWaitingResp();
		}
		
		return matches;
	}

	@Override
	public boolean deleteMatchWaiting(int id) {
		return matchDao.deleteMatchWaiting(id);
	}

	@Override
	public MatchActive startMatch(MatchStartRq req) {		
		MatchActive matchActive = null;
		MatchWaiting matchWainting = matchDao.getMatchWaiting(req.getMatchId());
		
		if(matchWainting != null) {
			int id = matchDao.createMatchActive(req.getUserid(), matchWainting);
			if(id > 0) {
				matchActive = matchDao.getMatchActive(id);
				matchDao.deleteMatchWaiting(req.getMatchId());
			}
		}		
		return matchActive;
	}

	@Override
	public MatchActive checkStart(int userId) {
		int matchId = matchDao.getMatchActiveId(userId);
		MatchActive match = matchDao.getMatchActive(matchId);
		return match;
	}

	@Override
	public void updateMatchActive(MatchActive match) {
		matchDao.updateMatchActive(match);
		
	}

	
}
