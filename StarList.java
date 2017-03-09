package projektoo;

import java.awt.Color;

@SuppressWarnings("serial")
public class StarList extends RectangleList {
	public StarList(Model model) {
		for (int i = 0; i < model.getResX(); i += 50) {
			for (int j = 0; j < model.getResY(); j += 50) {
				int x = model.getRgen().nextInt(100);
				if (x == 37 || x == 42 || x == 73) {
					add(new Rectangle(i, j, 26, 26, 0, 0, 0, 0, model, Color.WHITE, 0, 0, 0));
				}
			}
		}
	}
}
