package hu.lev.onlinegames.service;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import hu.lev.onlinegames.model.MatchActive;
import hu.lev.onlinegames.model.fiveinarow.FiveInARowAction;
import hu.lev.onlinegames.model.fiveinarow.FiveInARowField;
import hu.lev.onlinegames.model.fiveinarow.FiveInARowFields;

@Service
public class FiveInARowServiceImpl implements FiveInARowService {

	@Override
	public boolean validateAction(FiveInARowAction action, FiveInARowFields fieldsObj, int[] options) {
		FiveInARowField[][] fields = fieldsObj.getFields();
		if(fields[action.getX()][action.getY()].getValue() == 0) {
			return true;
		}
		if(fields[action.getX()][action.getY()].getValue() == 4
				&& ArrayUtils.contains(options, 1)
				&& ArrayUtils.contains(options, 3)) {
			return true;
		}
		return false;
	}

	@Override
	public MatchActive applyAction(MatchActive match) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkWin(FiveInARowFields fieldsObj, int player, FiveInARowAction action) {
		FiveInARowField[][] fields = fieldsObj.getFields();
		
		int startX = action.getX() - 4;			// init min and max indexes, so we check fields in board
		int startY = action.getY() - 4;
		int endX = action.getX() + 4;
		int endY = action.getY() + 4;
		
		// diagonals first, there is a bigger chance to win this way, save some energy
		// diagonal from top-left
		int lengthSoFar = 0;
		for(int i = startX, j = startY; i<=endX && j<=endY; i++){
			if (i >= 0 && i < fields.length && j >= 0 && j < fields[0].length){
				if (fields[i][j].getValue() == player){
					lengthSoFar++;
				} else if (lengthSoFar < 5){
					lengthSoFar = 0;
				}
			}
			j++;
		}
		if (lengthSoFar >= 5){ return true; }

		// diagolal from bottom-left
		lengthSoFar = 0;
		for(int i = startX, j = endY; i<=endX && j>=startY; i++){
			if (i >= 0 && i < fields.length && j >= 0 && j < fields[0].length){
				if (fields[i][j].getValue() == player){
					lengthSoFar++;
				} else if (lengthSoFar < 5){
					lengthSoFar = 0;
				}
			}
			j--;
		}
		if (lengthSoFar >= 5){ return true; }

		// vertical
		lengthSoFar = 0;
		for(int j=startY; j<=endY ; j++){
			if (j >= 0 && j < fields[0].length){
				if (fields[action.getX()][j].getValue() == player){
					lengthSoFar++;
				} else if (lengthSoFar < 5){
					lengthSoFar = 0;
				}
			}
		}
		if (lengthSoFar >= 5){ return true; }

		// horizontal
		lengthSoFar = 0;
		for(int i=startX; i<=endX ; i++){
			if (i >= 0 && i < fields.length){
				if (fields[i][action.getY()].getValue() == player){
					lengthSoFar++;
				} else if (lengthSoFar < 5){
					lengthSoFar = 0;
				}
			}
		}
		if (lengthSoFar >= 5){ return true; }

		// no winning found
		return false;
	}

	@Override
	public void updateMatch(MatchActive match) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FiveInARowFields convertBoardstate(String boardstate) {

		FiveInARowFields fields = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			boardstate = "{\"fields\": " + boardstate + '}';
			fields = mapper.readValue(boardstate, FiveInARowFields.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fields;
	}

	@Override
	public int[] convertOptions(String optionsString) {
		int[] options = null;
		optionsString = optionsString.substring(1, optionsString.length()-1);
		if(optionsString != null) {
			String[] integerStrings = optionsString.split(","); 
			options = new int[integerStrings.length]; 
			for (int i = 0; i < options.length; i++){
				options[i] = Integer.parseInt(integerStrings[i]); 
			}
		}
		return options;
	}

}









