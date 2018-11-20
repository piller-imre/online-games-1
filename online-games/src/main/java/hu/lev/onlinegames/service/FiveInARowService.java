package hu.lev.onlinegames.service;

import hu.lev.onlinegames.model.MatchActive;
import hu.lev.onlinegames.model.fiveinarow.FiveInARowAction;
import hu.lev.onlinegames.model.fiveinarow.FiveInARowField;
import hu.lev.onlinegames.model.fiveinarow.FiveInARowFields;

public interface FiveInARowService {

	boolean validateAction(FiveInARowAction action, FiveInARowField[][] fields, int[] options);
	MatchActive applyAction(MatchActive match, FiveInARowFields fieldsObj, FiveInARowAction action);
	boolean checkWin(FiveInARowField[][] fields, int player, FiveInARowAction action);
	void updateMatch(MatchActive match);
	FiveInARowFields convertBoardstate(String boardstate);
	int[] convertOptions(String options);

}
