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
		return "Field [x=" + x + ", y=" + y + ", value=" + value + "]";
	}

	// setters and getters
	public int getx() {
		return x;
	}

	public void setx(int x) {
		this.x = x;
	}

	public int gety() {
		return y;
	}

	public void sety(int y) {
		this.y = y;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
