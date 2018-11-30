package hu.lev.onlinegames.model.fiveinarow;

public class FiveInARowField {
	
	private int xCoord;
	private int yCoord;
	private int value;
	
	// constructors
	public FiveInARowField() {
		super();
		value = 0;
	}

	public FiveInARowField(int xCoord, int yCoord, int value) {
		super();
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.value = value;
	}
	
	// toString
	@Override
	public String toString() {
		return "Field [xCoord=" + xCoord + ", yCoord=" + yCoord + ", value=" + value + "]";
	}

	// setters and getters
	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
