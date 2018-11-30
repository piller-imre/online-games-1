package hu.lev.onlinegames.model.fiveinarow;

public class FiveInARowAction {

	private int x;
	private int y;
	private int value;
	
	// constructors
	public FiveInARowAction() {
		super();
	}

	public FiveInARowAction(int x, int y, int value) {
		super();
		this.x = x;
		this.y = y;
		this.value = value;
	}
	
	// toString
	@Override
	public String toString() {
		return "{\"x\":" + x + ",\"y\":" + y + ",\"value\":" + value + "}";
	}

	// setters and getters
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
