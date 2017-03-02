package projektoo;

/**
 * (Actually this class is somewhat like a mass point. Only without any mass,
 * yet. Probably it should be renamed to "Point".) This class shall outline
 * general geometric objects. For now every geometric Object has coordinates.
 * And every geometric objects coordinates can be manipulated by physics.
 * Therefore every geometric Object must have some physical properties like
 * x-velocity and y-velocity. Acceleration could be implemented later, Mass,
 * too. Several constructors should be delivered. In our world everything is an
 * integer as long as it can be, because computing is more easy with integers.
 */
/*
 * das abschalten und anschalten von gravitation sollte besser so geregelt
 * werden, dass der controller checkt, in richtung der gravitation ein
 * gegenstand halt bietet und dann und wenn er als nicht am boden zählt (also
 * insgesamt getriggert wurde) die gravitation neutralisiert und im umgekehrten
 * fall draufzieht. dazu braucht man natürlich eine methode, die sowas
 * überprüfen kann und die gehört auf die controller ebene.
 */
public class GeoObject {

	// place
	protected int x;
	protected int y;
	// velocity
	protected int vx;
	protected int vy;
	// acceleration
	protected int ax;
	protected int ay;
	// the physics
	protected Physics physics;
	// whether a ground offers resistance to gravity
	protected boolean isInAir;

	/**
	 * constructor with many parameters. adding emptier constructors somehow
	 * caused weird bugs, so i removed them
	 * 
	 * @param x
	 *            horizontal coordinate
	 * @param y
	 *            vertical coordinate
	 * @param vx
	 *            horizontal velocity
	 * @param vy
	 *            vertical velocity
	 * @param ax
	 *            horizontal acceleration
	 * @param ay
	 *            vertical acceleration
	 * @param physics
	 * @param isInAir
	 */
	public GeoObject(int x, int y, int vx, int vy, int ax, int ay, Physics physics, boolean isInAir) {
		this.isInAir = isInAir;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.ax = ax;
		this.ay = ay;
		this.physics = physics;
	}

	/**
	 * makes the object jump in y-direction with the velocity @code vy0
	 * 
	 * @param vy0
	 *            the new vertical velocity caused by the jump
	 */
	public void jump(int vy0) {
		// sets y velocity to the jump speed
		this.vy = vy0;
		// if jumping while in air, gravity isn't triggered
		if (!isInAir) {
			triggerGravitation();
		}
	}

	/*
	 * below you'll see the methods responsible for updating properties
	 */

	/**
	 * implements newtons actio = reactio for gravity. if there is ground under
	 * feet, gravity gets neutralized, if not it'll come into effect.
	 */
	public void triggerGravitation() {
		if (!isInAir) {
			ay = ay + physics.getGravitation();
		} else {
			ay = ay - physics.getGravitation();
		}
		isInAir = !isInAir;
	}

	/**
	 * this method would better be implemented on the level of the Model. this
	 * is only provisorisch
	 */
	 private void isHittingGround() {
	 if (this.y <= 100 && isInAir) {
	 vy = 0;
	 y = 100;
	 triggerGravitation();
	 this.isInAir = false;
	 }
	 }

	/**
	 * computes the new place and updates the old
	 * 
	 * @param deltaT
	 *            vergangene zeit
	 */
	private void updatePlace(int deltaT) {
		// since shift right 10 is almost like division by 1000, this makes
		// pixel out of millipixel
		x += (physics.zeitWegGesetz(ax, vx, deltaT)) >> 10;
		y += (physics.zeitWegGesetz(ay, vy, deltaT)) >> 10;
	}

	/**
	 * computes the new velocity and updates the old
	 * 
	 * @param deltaT
	 *            vergangene Zeit
	 */
	private void updateVelocity(int deltaT) {
		vx += (physics.zeitGeschwindigkeitGesetz(ax, deltaT));
		vy += (physics.zeitGeschwindigkeitGesetz(ay, deltaT));
	}

	/**
	 * computes the new acceleration and update the old
	 */
	private void updateAcceleration(int deltaT) {
		// // what this conditional does would better be implemented on the
		// level of the model
		 if (!isInAir && y > 100) {
		 triggerGravitation();
		 }
	}

	/**
	 * collects the individual update methods for the properties into ones
	 * 
	 * @param deltaT
	 */
	public void updateObject(int deltaT) {
		updatePlace(deltaT);
		updateVelocity(deltaT);
		updateAcceleration(deltaT);
		 isHittingGround();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getVx() {
		return vx;
	}

	public int getVy() {
		return vy;
	}

	public int getAx() {
		return ax;
	}

	public int getAy() {
		return ay;
	}

	public Physics getPhysics() {
		return physics;
	}

	public boolean isInAir() {
		return isInAir;
	}

}
