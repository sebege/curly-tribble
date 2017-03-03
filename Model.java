package projektoo;

import java.awt.Color;
import java.util.ArrayList;

import acm.util.RandomGenerator;

public class Model {

	// Resolution
	public static final int RES_X = 1400;
	public static final int RES_Y = 700;
	// Refresh Rate in milliseconds
	// 1 because of integer precision the physics doesn't work properly with low
	// 2 could be fixed by using double values and converting to int only when
	// setting up the view
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
	// GND is the effective height of the ground
	public static final int GNDY = 10;
	public static final int GNDH = 90;	
	public static final int GND = GNDY + GNDH;
	// some properties for the generated obstacles
	public static final int OBW = 50;
	public static final int OBH = 50;
	// Obstacle starting speed
	public static final int OBV0 = 100;

	private RandomGenerator rgen;
	private Physics physics;
	private Rectangle player;
	private Rectangle background;
	/*
	 * ich denke, einen animierten boden könnte man am einfachsten
	 * implementiere, dass man 1400p breite böden designt und aneinanderreiht
	 * und sich mit den obstacles fortbewegen lässt und wieder am ende anfügt,
	 * wenn er aus dem bild raus ist.
	 */
	private Rectangle ground;
	private long lastTime;
	private int obstacleSpeed;
	private ObstacleList obstacleList;

	public Model() {
		this.physics = new Physics(GRAV);
		this.player = new Rectangle(PLX, PLY, PLW, PLH, 0, 0, 0, 0, physics, Color.YELLOW);
		this.lastTime = System.currentTimeMillis();
		this.obstacleSpeed = OBV0;
		this.rgen = new RandomGenerator();
		this.obstacleList = new ObstacleList(1400, obstacleSpeed, OBW, OBH, physics);
		this.background = new Rectangle(0, 0, 1400, 700, 0, 0, 0, 0, physics, Color.BLUE);
		this.ground = new Rectangle(0, GNDY, 1400, GNDH, 0, 0, 0, 0, physics, Color.GREEN);
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

	public Rectangle getBackground() {
		return background;
	}

	public Rectangle getGround() {
		return ground;
	}

	public ObstacleList getObstacleList() {
		return obstacleList;
	}

	public RandomGenerator getRgen() {
		return rgen;
	}

}
