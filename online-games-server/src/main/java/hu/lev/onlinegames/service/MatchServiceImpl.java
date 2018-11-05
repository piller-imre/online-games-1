package hu.lev.onlinegames.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.lev.onlinegames.model.GameType;
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

	
}
