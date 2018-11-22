package hu.lev.onlinegames.service;

import org.springframework.stereotype.Service;

@Service
public class ConverterServiceImpl implements ConverterService {

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
