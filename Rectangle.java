package projektoo;

/**
 * An Rectangle is a geometric Object, that has extension in x and y direction,
 * width and height by name.
 *
 */
public class Rectangle extends GeoObject {

	protected int width;
	protected int height;

	public Rectangle(int x, int y, int width, int height, int vx, int vy, int ax, int ay, Physics physics, boolean isOnGround) {
		super(x, y, vx, vy, ax, ay, physics, isOnGround);
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
