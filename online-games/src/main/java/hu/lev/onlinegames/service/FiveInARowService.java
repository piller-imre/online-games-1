package hu.lev.onlinegames.service;

import hu.lev.onlinegames.model.MatchActive;
import hu.lev.onlinegames.model.fiveinarow.FiveInARowAction;
import hu.lev.onlinegames.model.fiveinarow.FiveInARowField;
import hu.lev.onlinegames.model.fiveinarow.FiveInARowFields;
import hu.lev.onlinegames.model.request.MatchActiveRq;

public interface FiveInARowService {

	boolean validateAction(FiveInARowAction action, FiveInARowField[][] fields, int[] options);
	MatchActiveRq applyAction(MatchActiveRq matchRq);
	boolean checkWin(FiveInARowField[][] fields, int player, FiveInARowAction action);
//	void updateMatch(MatchActive match);
//	FiveInARowFields convertBoardstate(String boardstate);
//	int[] convertOptions(String options);
	MatchActive convertMatchRq(MatchActiveRq matchRq);
	public String initFields(int[] options);
}
