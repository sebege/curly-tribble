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
public class GeoObject {

	/*
	 * the origin of the coordinate system shall be in the lower left corner,
	 * because we can think easier that way. for the acm graphics it'll be
	 * adjusted in the view.
	 */
	protected int x;
	protected int y;
	// the "unit" shall be set later. probably it'll be "Pixel per Millisecond".
	protected int vx;
	protected int vy;
	protected int ax;
	protected int ay;
	// means that this object under some laws of physics
	protected Physics physics;
	/*
	 * actually GeoObject would need a superclass "Environment" that knows a
	 * Physics Object, so that being on the ground or not could be calculated.
	 * but that isn't implemented (yet, i fear).
	 */
	/*
	 * hmm, because it's dependent on extension, groundedness would probably better be managed by each subclass.
	 */
	protected boolean isOnGround;
	protected boolean isOnGroundTriggered;

	public GeoObject(int x, int y, int vx, int vy, int ax, int ay, Physics physics, boolean isOnGround) {
		this.isOnGround = isOnGround;
		this.isOnGroundTriggered = false;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.ax = ax;
		this.ay = ay;
		this.physics = physics;
		if (!isOnGround) {
			this.ay = physics.getGravitation() + this.ay;
		}
	}

	// creates an Object with constant velocity
	public GeoObject(int x, int y, int vx, int vy, Physics physics, boolean isOnGround) {
		new GeoObject(x, y, vx, vy, 0, 0, physics, isOnGround);
	}

	// creates an Object that stands still
	public GeoObject(int x, int y, Physics physics, boolean isOnGround) {
		new GeoObject(x, y, 0, 0, physics, isOnGround);
	}

	// could be useful some
	public void move(int deltaX, int deltaY) {
		this.setX(this.getX() + deltaX);
		this.setY(this.getY() + deltaY);
	}

	// by now, this doesn't prohibit jumping while airborne. could be
	// implemented by some additional booleans by some workaholic programmer...
	public void jump(int vy0) {
		this.vy += vy0;
		if (isOnGround) {
			this.isOnGroundTriggered = true;
		}
		/* TO-DO landing must be implemented in the model
		 * by triggering isOnGround when the is ground under the object
		 * and setting vy to 0
		 */
	}
	
	// povisorische method that checks if an object landed on an constant ground at y=0
	public void isHittingGround() {
		if (this.x < 0 && !isOnGround) {
			vy = 0;
			y = 0;
			isOnGroundTriggered = true;
		}
	}

	/**
	 * 
	 * @param deltaT
	 *            should be the time difference between now and the last update
	 *            in milliseconds
	 */
	public void updatePlace(int deltaT) {
		setX(physics.zeitWegGesetz(ax, vx, x, deltaT));
		setY(physics.zeitWegGesetz(ay, vy, y, deltaT));
	}

	public void updateVelocity(int deltaT) {
		setVx(physics.zeitGeschwindigkeitGesetz(ax, vx, deltaT));
		setVy(physics.zeitGeschwindigkeitGesetz(ay, vy, deltaT));
	}

	public void updateAcceleration() {

		/*
		 * if one stands on the ground, the counter force neutralizes
		 * gravitation. that in mind, this if-clause simulates getting airborne
		 * and landing by adding and subtracting gravitation from objects
		 */
		if (isOnGroundTriggered) {
			if (isOnGround) {
				ay = ay + physics.getGravitation();
			} else {
				ay = ay - physics.getGravitation();
			}
			// triggering complete; invert booleans
			isOnGround = !isOnGround;
			isOnGroundTriggered = false;
		}
	}
	
	// this method unifies the updateMethods for the individual properties into one method
	public void updateObject(int deltaT) {
		// the order is important here because of some dependencies;
		// alternatively the had to be an old and an updated version of everything
		updatePlace(deltaT);
		updateVelocity(deltaT);
		updateAcceleration();
		isHittingGround();
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVx() {
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}

	public int getAx() {
		return ax;
	}

	public void setAx(int ax) {
		this.ax = ax;
	}

	public int getAy() {
		return ay;
	}

	public void setAy(int ay) {
		this.ay = ay;
	}

	public Physics getPhysics() {
		return physics;
	}

	public void setPhysics(Physics physics) {
		this.physics = physics;
	}

	public boolean isOnGround() {
		return isOnGround;
	}

	public void setOnGround(boolean isOnGround) {
		this.isOnGround = isOnGround;
	}

	public boolean isOnGroundTriggered() {
		return isOnGroundTriggered;
	}

	public void setOnGroundTriggered(boolean isOnGroundTriggered) {
		this.isOnGroundTriggered = isOnGroundTriggered;
	}

}
