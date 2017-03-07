package projektoo;

import java.awt.Color;

public class HighObstacle extends Rectangle {
	public HighObstacle(Model model) {
		super(model.getResX(), 200, 50, 250, -500, 0, 0, 0,  model, Color.GRAY, 0, 0, 0);
	}
}
