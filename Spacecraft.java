package projektoo;

import java.awt.Color;

/**
 * the model for the player used in the asteroid setting
 *
 */
public class Spacecraft extends Rectangle {
	private int movingSpeed;
	private int jetBoost;
	public Spacecraft(Model model) {
		super(200, 300, 100, 50, 0, 0, 0, 0, model, Color.GREEN, 0, 0, 0);
		this.movingSpeed = 800;
		this.jetBoost = 3;
	}
	public int getMovingSpeed() {
		return movingSpeed;
	}
	public void setMovingSpeed(int movingSpeed) {
		this.movingSpeed = movingSpeed;
	}
	public int getJetBoost() {
		return jetBoost;
	}
	public void setJetBoost(int jetBoost) {
		this.jetBoost = jetBoost;
	}
}
