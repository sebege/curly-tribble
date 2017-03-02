package projektoo;

public class Model {
	
	// Resolution
	public static final int RES_X = 1400;
	public static final int RES_Y = 700;
	// Refresh Rate in milliseconds
	// because of integer precision the physics doesn't work properly with low INTERVALL and low velocities
	public static final double INTERVALL = 20;
	// player start coordinates
	public static final int PLX = 200;
	public static final int PLY = 100;
	// player extension
	public static final int PLW = 100;
	public static final int PLH = 100;
	// jump start speed in (approximately) millipixel per millisecond
	public static final int JV0 = 2000;
	// gravitation in (approximately) millipixel per square millisecond
	public static final int GRAV = -4;
	public static final int GNDX = 100;
	
	private Physics physics;
	private Rectangle player;
	private long lastTime;

	public Model() {
		this.physics = new Physics(GRAV);
		this.player = new Rectangle(PLX, PLY, PLW, PLH, 0, 0, 0, 0, physics, true);
		this.lastTime = System.currentTimeMillis();
	}
	
	public void updateModel(long thisTime) {
		int deltaT = (int) (thisTime - lastTime);
		player.updateObject(deltaT);
		lastTime = thisTime;
	}

	public long getLastTime() {
		return lastTime;
	}

	public Rectangle getPlayer() {
		return player;
	}

}
