package projektoo;

/**
 * An Rectangle is a geometric Object, that has extension in x and y direction,
 * width and height by name.
 *
 */
public class Rectangle extends GeoObject {

	protected int width;
	protected int height;

	public Rectangle(int x, int y, int width, int height, int vx, int vy, Physics physics, boolean isOnGround) {
		super(x, y, vx, vy, physics, isOnGround);
		this.width = width;
		this.height = height;
	}

	public Rectangle(int x, int y, int width, int height, Physics physics, boolean isOnGround) {
		super(x, y, physics, isOnGround);
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	

}
