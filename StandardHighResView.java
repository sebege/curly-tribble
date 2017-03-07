package projektoo;

import acm.graphics.GLabel;
import acm.graphics.GRect;

@SuppressWarnings("serial")
public class StandardHighResView extends StandardView {
	
	public StandardHighResView() {
		super();
	}

	public void updateView() {
		removeAll();
		addGRects();
	}

	public void gameOverView() {
		removeAll();
		add(new GLabel("his existence was only brief, but he enjoyed it thoroughly."), 50, 200);
	}

	/**
	 * calls addGrect on every Rectangle that is to be put onto the canvas
	 */
	public void addGRects() {
		for (int i = 0; i < model.getStuff().size(); i++) {
			addGRect(model.getStuff().get(i));
		}
	}

	/**
	 * takes a rectangle and adds a GRect built out of the rectangle to the
	 * canvas
	 * 
	 * @param rec
	 * @return
	 */
	public void addGRect(Rectangle rec) {
		GRect grect = new GRect(rec.getX(), model.getResY() - rec.getY() - rec.getHeight(), rec.getWidth(),
				rec.getHeight());
		if (rec.getColor() != null) {
			grect.setColor(rec.getColor());
			grect.setFilled(true);
		}
		add(grect);
	}
}
