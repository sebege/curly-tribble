package projektoo;

import java.awt.Color;

/**
 * so viele unserer figuren sind schlicht rectangles, die gemeinsamkeiten haben.
 * lass uns einer superklasse dafür erstellen! yay \o/
 * 
 * @author bsg
 *
 */
public class Rectangle {
	/*
	 * instance variables
	 */
	protected int x;
	protected int y;
	protected int height;
	protected int width;
	protected Color color;
	
	/*
	 * constructor
	 */
	
	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	
	/*
	 * getters and setters
	 */
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

	public int getHeight() {
		return height;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
