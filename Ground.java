package projektoo;

import java.awt.Color;

public class Ground extends Rectangle {
	
	public Ground(int y, int height, Color color) {
		super(0, y, Model.RESOLUTION_X, height);
		this.color = color;
	}

}
