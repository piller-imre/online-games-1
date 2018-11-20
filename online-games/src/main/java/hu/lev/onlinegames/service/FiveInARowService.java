package hu.lev.onlinegames.service;

import hu.lev.onlinegames.model.MatchActive;
import hu.lev.onlinegames.model.fiveinarow.FiveInARowAction;
import hu.lev.onlinegames.model.fiveinarow.FiveInARowFields;

public interface FiveInARowService {

	boolean validateAction(FiveInARowAction action, FiveInARowFields fields, int[] options);
	MatchActive applyAction(MatchActive match);
	boolean checkWin(FiveInARowFields fieldsObj, int player, FiveInARowAction action);
	void updateMatch(MatchActive match);
	FiveInARowFields convertBoardstate(String boardstate);
	int[] convertOptions(String options);

}
