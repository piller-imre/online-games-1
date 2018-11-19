package hu.lev.onlinegames.service;

import hu.lev.onlinegames.model.FiveInARowFields;
import hu.lev.onlinegames.model.MatchActive;

public interface FiveInARowService {

	boolean validateAction(MatchActive match);
	MatchActive applyAction(MatchActive match);
	int checkWin(MatchActive match);
	void updateMatch(MatchActive match);
	FiveInARowFields convertBoardstate(String boardstate);
	int[] convertOptions(String options);

}
