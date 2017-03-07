package projektoo;

import java.awt.Color;

public class Space extends Rectangle{
	public Space(Model model) {
		super(0, 0, model.getResX(), model.getResY(), 0, 0, 0, 0, model, Color.BLACK, 0, 0, 0);
	}

}
