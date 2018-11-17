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
	public int startMatch(MatchStartRq req) {
		int id = 0;
		
		MatchWaiting match = matchDao.getMatchWaiting(req.getMatchId());
		
		if(match == null) {
			id = -1;
		} else if (matchDao.isUserPlaying(req.getUserid())) {
			id = -2;
		} else {
			id = matchDao.createMatchActive(req.getUserid(), match);
			if(id > 0) {
				matchDao.deleteMatchWaiting(req.getMatchId());
			}
		}
				
		return id;
	}

	@Override
	public boolean checkStart(int userId) {
		boolean match = matchDao.isUserPlaying(userId);
		return match;
	}

	
}
