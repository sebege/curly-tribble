package projektoo;

import java.awt.Color;

public class LowObstacle extends Rectangle {
	public LowObstacle(Model model, int y) {
		super(model.getResX(), y, 50, 250, -500, 0, 0, 0, model, Color.GRAY, 0, 0, 0);
	}
}
