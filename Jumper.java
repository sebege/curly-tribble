package projektoo;

import java.awt.Color;

/**
 * This is the player character rectangle for the version in which one jumps
 *
 */
public class Jumper extends Rectangle {
	public Jumper(Model model) {
		super(200, 100, 50, 150, 0, 0, 0, 0, model, Color.ORANGE, 2000, 101, 1000);
	}

}
