package projektoo;

import java.awt.Color;

/**
 * diese Klasse soll ein rectangle werden, dass als farbiger hintergrund für
 * unser schönes canvas dienen kann.
 * 
 * @author bsg
 *
 */
public class Background extends Rectangle {
	
	
	public Background(Color color) {
		super(0, 0, Model.RESOLUTION_X , Model.RESOLUTION_Y);
		this.color = color;
	}

}
