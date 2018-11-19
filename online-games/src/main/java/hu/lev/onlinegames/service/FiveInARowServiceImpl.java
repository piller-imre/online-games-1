package hu.lev.onlinegames.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import hu.lev.onlinegames.model.FiveInARowFields;
import hu.lev.onlinegames.model.MatchActive;

@Service
public class FiveInARowServiceImpl implements FiveInARowService {

	@Override
	public boolean validateAction(MatchActive match) {
		// TODO Auto-generated method stub
		
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









