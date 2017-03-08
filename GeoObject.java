package projectoo;

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
 * gegenstand halt bietet und dann und wenn er als nicht am boden z??hlt (also
 * insgesamt getriggert wurde) die gravitation neutralisiert und im umgekehrten
 * fall draufzieht. dazu braucht man nat??rlich eine methode, die sowas
 * ??berpr??fen kann und die geh??rt auf die controller ebene.
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
	protected boolean isGravitationOn;
	protected int jetUp;
	protected int jetDown;
	protected int moveUp;
	protected int moveDown;
	protected int moveFaster;
	protected int moveSlower;

	public int getMoveFaster() {
		return moveFaster;
	}

	public void setMoveFaster(int moveFaster) {
		this.moveFaster = moveFaster;
	}

	public int getMoveSlower() {
		return moveSlower;
	}

	public void setMoveSlower(int moveSlower) {
		this.moveSlower = moveSlower;
	}

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
	public GeoObject(int x, int y, int vx, int vy, int ax, int ay, Physics physics) {
		// set false by standard. if that's wrong, the controller will correct
		// it on start
		this.isGravitationOn = false;
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
		if (!isGravitationOn()) {
			// sets y velocity to the jump speed
			setVy(vy0);
			setGravitationOn(true);
		}
	}

	/*
	 * below you'll see the methods responsible for updating properties
	 */

	/**
	 * computes the new place and updates the old
	 * 
	 * @param deltaT
	 *            vergangene zeit
	 * @param modelType
	 *            0: Obstacle 1: player
	 */
	private void updatePlace(int deltaT, int modelType) {
		// since shift right 10 is almost like division by 1000, this makes
		// pixel out of millipixel
		x += (physics.zeitWegGesetz(getAx(), getVx(), deltaT)) >> 10;
		y += (physics.zeitWegGesetz(getAy(), getVy(), deltaT)) >> 10;
		
		//for not letting the objects out of the box in vertical way
		if (y < 0) {
			y = 0;
		} else
			switch (modelType) {
			case 0:
				if (y + Model.OBH > Model.RES_Y) {
					y = Model.RES_Y - Model.OBH;
				}
				break;
			case 1: 
				if (y + Model.PLH> Model.RES_Y) {
					y = Model.RES_Y - Model.PLH;
				}
				break;
			}

	}

	/**
	 * computes the new velocity and updates the old
	 * 
	 * @param deltaT
	 *            vergangene Zeit
	 */
	private void updateVelocity(int deltaT) {
		vx += (physics.zeitGeschwindigkeitGesetz(getAx(), deltaT));
		vy += (physics.zeitGeschwindigkeitGesetz(getAy(), deltaT));
	}

	/**
	 * computes the new acceleration and update the old
	 */
	private void updateAcceleration(int deltaT) {

	}

	/**
	 * collects the individual update methods for the properties into ones
	 * 
	 * @param deltaT
	 */
	public void updateObject(int deltaT, int modelType) {
		updatePlace(deltaT,modelType);
		updateVelocity(deltaT);
		updateAcceleration(deltaT);
	}

	public int getAy() {
		if (isGravitationOn) {
			return ay + physics.getGravitation() + getJetUp() + getJetDown();
		} else {
			return ay + getJetUp() + getJetDown();
		}
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
		return vx + getMoveFaster() + getMoveSlower();
	}

	public int getVy() {
		return vy + getMoveUp() + getMoveDown();
	}

	public void setVx(int vx) {
		this.vx = vx;
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

	public Physics getPhysics() {
		return physics;
	}

	public void setPhysics(Physics physics) {
		this.physics = physics;
	}

	public boolean isGravitationOn() {
		return isGravitationOn;
	}

	public void setGravitationOn(boolean isGravitationOn) {
		this.isGravitationOn = isGravitationOn;
	}

	public void setAy(int ay) {
		this.ay = ay;
	}

	public int getJetUp() {
		return jetUp;
	}

	public void setJetUp(int jetUp) {
		this.jetUp = jetUp;
	}

	public int getJetDown() {
		return jetDown;
	}

	public void setJetDown(int jetDown) {
		this.jetDown = jetDown;
	}

	public int getMoveUp() {
		return moveUp;
	}

	public void setMoveUp(int moveUp) {
		this.moveUp = moveUp;
	}

	public int getMoveDown() {
		return moveDown;
	}

	public void setMoveDown(int moveDown) {
		this.moveDown = moveDown;
	}
}
