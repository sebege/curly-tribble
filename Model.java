package projektoo;

import java.awt.Color;
import java.util.List;

public class Model {

	// Resolution
	public static final int RES_X = 1400;
	public static final int RES_Y = 700;
	// Refresh Rate in milliseconds
	// 1 because of integer precision the physics doesn't work properly with low
	// 2 could be fixed by using double values and converting to int only when setting up the view
	// INTERVALL and low velocities
	public static final double INTERVALL = 16;
	// player start coordinates
	public static final int PLX = 150;
	// if you set this PLY value high, but the player doesn't appear high:
	// that's because gravitation dragged him down before you saw the canvas
	public static final int PLY = 100;
	// player extension
	public static final int PLW = 100;
	public static final int PLH = 100;
	// jump start speed in (approximately) millipixel per millisecond
	public static final int JV0 = 3000;
	// gravitation in (approximately) millipixel per square millisecond
	public static final int GRAV = -10;
	public static final int GNDY = 100;

	private Physics physics;
	private Rectangle player;
	private long lastTime;

	public Model() {
		this.physics = new Physics(GRAV);
		this.player = new Rectangle(PLX, PLY, PLW, PLH, 0, 0, 0, 0, physics, Color.YELLOW);
		this.lastTime = System.currentTimeMillis();
	}

	public long getLastTime() {
		return lastTime;
	}

	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}

	public Rectangle getPlayer() {
		return player;
	}

}
