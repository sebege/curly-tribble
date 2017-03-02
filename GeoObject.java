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

	protected int x;
	protected int y;
	protected int vx;
	protected int vy;
	protected int ax;
	protected int ay;
	protected int ayg;
	protected Physics physics;
	protected boolean isOnGround;

	public GeoObject(int x, int y, int vx, int vy, int ax, int ay, Physics physics, boolean isOnGround) {
		this.isOnGround = isOnGround;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.ax = ax;
		this.ay = ay;
		this.physics = physics;
		this.ayg = physics.getGravitation() + this.ay;
	}

	public void jump(int vy0) {
		this.vy = vy0;
		if (isOnGround) {
			triggerGravitation();
		}
	}

	public void triggerGravitation() {
		if (isOnGround) {
			ay = ay + physics.getGravitation();
		} else {
			ay = ay - physics.getGravitation();
		}
		isOnGround = !isOnGround;
	}

	public void isHittingGround() {
		if (this.y <= 100 && !isOnGround) {
			vy = 0;
			y = 100;
			triggerGravitation();
		}
	}

	public void updatePlace(int deltaT) {
		// since shift right 10 is almost like division by 1000, this makes
		// pixel out of millipixel
		x += (physics.zeitWegGesetz(ax, vx, deltaT)) >> 10;
		y += (physics.zeitWegGesetz(ay, vy, deltaT)) >> 10;
	}

	public void updateVelocity(int deltaT) {
		vx += (physics.zeitGeschwindigkeitGesetz(ax, deltaT));
		vy += (physics.zeitGeschwindigkeitGesetz(ay, deltaT));
	}

	public void updateAcceleration() {
		if (isOnGround && y > 100) {
			triggerGravitation();
		}
	}

	public void updateObject(int deltaT) {
		updatePlace(deltaT);
		updateVelocity(deltaT);
		updateAcceleration();
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

	public int getAyg() {
		return ayg;
	}

	public Physics getPhysics() {
		return physics;
	}

	public boolean isOnGround() {
		return isOnGround;
	}
}
