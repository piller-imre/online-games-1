package hu.lev.onlinegames.model.fiveinarow;

import java.util.Arrays;

public class FiveInARowFields {

	private FiveInARowField[][] fields;

	public FiveInARowFields() {
		super();
	}

	public FiveInARowFields(FiveInARowField[][] fields) {
		super();
		this.fields = fields;
	}

	public FiveInARowField[][] getFields() {
		return fields;
	}

	public void setFields(FiveInARowField[][] fields) {
		this.fields = fields;
	}

	@Override
	public String toString() {
		return "FiveInARowFields [fields=" + Arrays.toString(fields) + "]";
	}
	
	
}
