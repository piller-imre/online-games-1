package hu.lev.onlinegames.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import hu.lev.onlinegames.model.MatchActive;
import hu.lev.onlinegames.model.fiveinarow.FiveInARowAction;
import hu.lev.onlinegames.model.fiveinarow.FiveInARowField;
import hu.lev.onlinegames.model.fiveinarow.FiveInARowFields;

@Service
public class FiveInARowServiceImpl implements FiveInARowService {

	@Override
	public boolean validateAction(FiveInARowAction action, FiveInARowFields fields, int[] options) {
		
		
		return false;
	}

	@Override
	public MatchActive applyAction(MatchActive match) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int checkWin(MatchActive match) {
		// TODO Auto-generated method stub
		return 0;
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









