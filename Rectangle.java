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

	public Rectangle(int x, int y, int width, int height, int vx, int vy, int ax, int ay, Physics physics, Color color) {
		super(x, y, vx, vy, ax, ay, physics);
		this.width = width;
		this.height = height;
		this.color = color;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Color getColor() {
		return color;
	}
	
}
