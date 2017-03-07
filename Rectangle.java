package projektoo;

import java.awt.Color;

/**
 * An Rectangle is a geometric Object, that has extension in x and y direction,
 * width and height by name.
 *
 */
public class Rectangle extends GeoObject {

	protected int width;
	protected int height;
	protected Color color;
	// -1 means it's not ducked. else it contains the time point it ducked
	protected long isDucked;
	protected int duckDistance;
	// for how long the rectangle stays ducked
	protected int duckDuration;
	// the speed with which the jump starts
	protected int jumpV0;

	/**
	 * 
	 * personally i had some disorienting experiences with reduced constructors,
	 * so we'll provide only one for all. also, i learned about interfaces just
	 * the day before yesterday, so i don't want to think about how to use them
	 * in an intelligent way, yet.
	 */
	public Rectangle(int x, int y, int width, int height, int vx, int vy, int ax, int ay, Model model, Color color,
			int jumpV0, int duckDistance, int duckDuration) {
		super(x, y, vx, vy, ax, ay, model);
		this.width = width;
		this.height = height;
		this.color = color;
		this.isDucked = -1;
		this.duckDistance = duckDistance;
		this.duckDuration = duckDuration;
		this.jumpV0 = jumpV0;
	}

	public void duck(long duckStart) {
		setIsDucked(duckStart);
	}

	public void updateDucking(long thisTime) {
		if (getIsDucked() > 0 && (int) (thisTime - isDucked) > duckDuration) {
			setIsDucked(-1);
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		if (isDucked >= 0) {
			return height - getDuckDistance();
		} else {
			return height;
		}
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public long getIsDucked() {
		return isDucked;
	}

	public void setIsDucked(long isDucked) {
		this.isDucked = isDucked;
	}

	public int getDuckDistance() {
		return duckDistance;
	}

	public void setDuckDistance(int duckDistance) {
		this.duckDistance = duckDistance;
	}

	public int getDuckDuration() {
		return duckDuration;
	}

	public void setDuckDuration(int duckDuration) {
		this.duckDuration = duckDuration;
	}

	public int getJumpV0() {
		return jumpV0;
	}

	public void setJumpV0(int jumpV0) {
		this.jumpV0 = jumpV0;
	}

}
