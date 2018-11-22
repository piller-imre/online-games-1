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
	
	@Autowired
	private FiveInARowService fiveInARowService;
	
	private ConverterService converterService;
	
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
		MatchWaiting matchWaiting = matchDao.getMatchWaiting(req.getMatchId());
		int [] options = converterService.convertOptions(matchWaiting.getOptions());
		String initFields = initFields(
				matchWaiting.getGameTypeId().getGameTypeId(), options);
		
		if(matchWaiting != null) {
			int id = matchDao.createMatchActive(req.getUserid(), matchWaiting, initFields);
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

	private String initFields(int gameTypeId, int[] options) {
		String fields = "";
		switch (gameTypeId) {
		case 1:
			fields = fiveInARowService.initFields(options);
			break;

		default:
			break;
		}
		return fields;
	}
}
