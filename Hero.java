package projektoo;

import java.awt.Color;

/**
 * this class specifies the player rectangle and his methods. for now it can
 * know whether it started to jump and can compute it's current position.
 * 
 * @author bsg
 *
 */
public class Hero extends Rectangle {

	/*
	 * instance variables
	 */
	boolean jumpTriggered;
	long jumpTime;
	boolean isDucked;

	/*
	 * constructor
	 */
	public Hero(int x, int y, int width, int height, Color color) {
		super(x, y, width, height);
		this.jumpTriggered = false;
		this.isDucked = false;
		this.color = color;
	}

	public Hero(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.jumpTriggered = false;
		this.isDucked = false;
	}

	/**
	 * triggers a jumping action
	 */
	public void triggerJump() {
		// our hero can't do air jumps. therefore no jumps while jumping.
		if (!jumpTriggered) {
			this.jumpTriggered = true;
			this.jumpTime = System.currentTimeMillis();
		}
	}

	public void duck() {
		if(!isDucked) {
			this.isDucked = true;
			this.height = this.height >> 1;
			this.y = this.y + this.height;
		}
	}
	
	public void unduck() {
		this.isDucked = false;
		this.y =  this.y - this.height;
		this.height = this.height << 1;
	}
	
	// zum aktualisieren zeitabhängiger veränderungen
	public void updateHero(long newTime) {
		updateHeroJumpY(newTime);
		
	}

	// updates the heroes y-coordinates in case of a jumo
	public void updateHeroJumpY (long newTime) {

		// if the hero is jumping, then the y-coordinate is computed out of the
		// starting place and time.
		if (jumpTriggered) {
			// gives back the time that our hero has been in the air
			int jumpDuration = (int) (newTime - jumpTime);
			// s = v * t + a * t ^ 2
			int place_difference = (((Model.GRAVITATION * jumpDuration * jumpDuration) >> 2)
					- (Model.START_SPEED * jumpDuration)) >> Model.ADJUSTMENT_RIGHTSHIFT;
			y = (place_difference + Model.GROUND_Y - height);
			// monitoring whether the physics work appropriate
			System.out.println(y);
			// if the hero reaches the ground, then the jump is complete and the
			// y-coordinate is reset
			if ((y + height) > Model.GROUND_Y) {
				jumpTriggered = false;
				y = Model.GROUND_Y - height;
			}
		}

	}

	/*
	 * Getters and Setters
	 */
	/**
	 * if the hero is jumping, then the y-coordinate is computed out of the
	 * starting place and time.
	 * 
	 * another solution would probably include actually keeping the y-coordinate
	 * and with it also the current velocity at another place, which would be
	 * more complicated than this and wouldn't consume less computational power
	 * at all. this could be implemented for more complex scenarios. for example
	 * if our hero could throw stuff and would throw while jumping the thrown
	 * thing would still have the upwards velocity of the jumper. in this
	 * scenario having the current velocity at hand would be comfortable. then
	 * we'd probably design a updateModel(long currentTime) (the current time as
	 * parameter would ensure, that everything is updated synchronous, i guess)
	 * method and implement updating the y-coordinate the, which maybe would
	 * have been the more intelligent approach anyway. looking at it know,
	 * computing the new coordinate in the getter every time the getter is
	 * called seems a little bit stupid. but who cares? no, i should
	 * definitively change this. that concept could be applied to the
	 * environment and other objects too. the time of the last update would be a
	 * class variable and the model and each object inside could compute its new
	 * internal states by the time difference and its current internal states.
	 * but not today. nah, just did it anyway today.
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
