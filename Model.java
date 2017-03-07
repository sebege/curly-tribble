package projektoo;

import java.util.Random;

/**
 * a template for the creation of actual usable models, that unifies general
 * characteristics, which will be necessary by every controller and view
 *
 */
public class Model {
	/*
	 * integer ivars
	 */
	protected int resX;
	// the internal resolution of 'r world. above the horizontal, below the
	// vertical axis
	protected int resY;
	// in milliseconds
	protected int refreshInterval;
	// earth acceleration in millipixel per millisecond
	protected int gravitation;

	/*
	 * other ivars
	 */
	// the view will take the rectangles it displays out of this list, including
	// the obstacles
	protected RectangleList stuffList;
	// for us, obstacles are universal, so every model should have a list with
	// 'em
	protected ObstacleList obstacleList;
	// some random generator which will most likely turn out useful
	protected Random rgen;
	// contains the time of the models last update in milliseconds
	protected long lastUpdate;

	/*
	 * constructor
	 */
	/**
	 * the lastUpdate field should be set to the current time at the latest
	 * possible opportunity before the while-true start to give the player some
	 * time
	 */
	public Model(int gravitation) {
		// that's something we can set as a standard, so we define it at this
		// level
		this.resX = 1400;
		this.resY = 700;
		this.gravitation = gravitation;
		// like 50 frames per secons, which is a good value
		this.refreshInterval = 20;
		this.stuffList = new RectangleList();
		this.obstacleList = new ObstacleList(this);
		this.rgen = new Random();
		// it is recommended, to update this value before entering the
		// while-true loop, but setting it also here, minimizes the damage done
		// be by not complying to the recommendation
		this.lastUpdate = System.currentTimeMillis();
	}
	
	/*
	 * getter and setter
	 */

	public int getResX() {
		return resX;
	}

	public void setResX(int resX) {
		this.resX = resX;
	}

	public int getResY() {
		return resY;
	}

	public void setResY(int resY) {
		this.resY = resY;
	}

	public int getRefreshInterval() {
		return refreshInterval;
	}

	public void setRefreshInterval(int refreshInterval) {
		this.refreshInterval = refreshInterval;
	}

	public int getGravitation() {
		return gravitation;
	}

	public void setGravitation(int gravitation) {
		this.gravitation = gravitation;
	}

	public RectangleList getStuff() {
		return stuffList;
	}

	public void setStuffList(RectangleList stuffList) {
		this.stuffList = stuffList;
	}

	public ObstacleList getObstacleList() {
		return obstacleList;
	}

	public void setObstacleList(ObstacleList obstacleList) {
		this.obstacleList = obstacleList;
	}

	public Random getRgen() {
		return rgen;
	}

	public void setRgen(Random rgen) {
		this.rgen = rgen;
	}

	public long getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(long lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
