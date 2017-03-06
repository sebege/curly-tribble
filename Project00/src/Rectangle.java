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
	// -1 means it's not ducked. else it contains the time it got ducked
	protected long isDucked;
	protected int duckDistance;

	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param vx
	 * @param vy
	 * @param ax
	 * @param ay
	 * @param physics
	 * @param color
	 */
	public Rectangle(int x, int y, int width, int height, int vx, int vy, int ax, int ay, Physics physics, Color color,
			int duckDistance) {
		super(x, y, vx, vy, ax, ay, physics);
		this.width = width;
		this.height = height;
		this.color = color;
		this.isDucked = -1;
		this.duckDistance = duckDistance;
	}

	public void duck(long duckStart) {
		setIsDucked(duckStart);
	}

	public void unduck() {
		setIsDucked(-1);
	}

	public int getWidth() {
		return width;
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

}
