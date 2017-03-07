package projektoo;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class RectangleList extends ArrayList<Rectangle> {

	public RectangleList() {
	}

	/**
	 * calls the updateObject Method on every Rectangle in the list
	 * 
	 * @param deltaT
	 */
	public void updateAllRectangles(int deltaT) {
		for (int i = 0; i < size(); i++) {
			get(i).updateObject(deltaT);
		}
	}

}
