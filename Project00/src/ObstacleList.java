import java.awt.Color;
import java.util.ArrayList;

/**
 * this class shall be an ArrayList that contains Obstacles and provides some
 * methods useful for our assignment. i guess it will be comfy to have an own
 * class for it.
 *
 */
@SuppressWarnings("serial")
public class ObstacleList extends ArrayList<Rectangle> {

	private int spawnX;
	private int spawnVx;
	private int spawnWidth;
	private Physics physics;

	public ObstacleList(int spawnX, int spawnVx, int spawnWidth, Physics physics) {
		this.spawnX = spawnX;
		this.spawnVx = spawnVx;
		this.spawnWidth = spawnWidth;
		this.physics = physics;
	}

	/**
	 * returns the distance between a hypothetical freshly spawned obstacle and
	 * the obstacle newest obstacle in the list. if the list is empty, it
	 * returns the X Resolution for that there is no obstacle at the whole
	 * distance of the screen.
	 * 
	 * @param i
	 * @return
	 */
	public int getDistance() {
		if (size() >= 1) {
			return spawnX - (get(size() - 1).getX() + get(size() - 1).getWidth());
		} else {
			return spawnX;
		}
	}

	/**
	 * takes a start y coordinate and a color and adds a nicely standardized
	 * obstacle to the List
	 * 
	 * @param y
	 * @param color
	 */
	public void addNewObstacle(int y, int spawnHeight, Color color) {
		add(new Rectangle(spawnX, y, spawnWidth, spawnHeight, spawnVx, 0, 0, 0, physics, color, 0));
	}

	/**
	 * removes every obstacle, that left the visible area of the screen, by
	 * removing it, if its right vertical lines x coordinate is below 0
	 */
	public void removeOldObstacles() {
		for (int i = 0; i < size(); i++) {
			if ((get(i).getX()) + (get(i).getWidth()) < 0) {
				remove(i);
			}
		}
	}

	/**
	 * sets all obstacles speed to vx and adjusts the spawn vx for the not yet
	 * created obstacles
	 * 
	 * @param vx
	 *            horizontal velocity
	 */
	public void setAllObstaclesXVelocity(int vx) {
		spawnVx = vx;
		for (int i = 0; i < size(); i++) {
			get(i).setVx(vx);
		}
	}

	/**
	 * calls the updateObject Method on every obstacle in the list
	 * 
	 * @param deltaT
	 */
	public void updateAllObstacles(int deltaT) {
		for (int i = 0; i < size(); i++) {
			get(i).updateObject(deltaT);
		}
	}
}
