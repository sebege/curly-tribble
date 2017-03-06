package projectoo;

import acm.graphics.GLabel;
import acm.graphics.GRect;

@SuppressWarnings("serial")
public class HighResView extends View {

	public HighResView() {
		super();
	}

	public void updateView() {
		removeAll();
		addGRects();
	}

	public void gameOverView() {
		removeAll();
		String[] halfText = this.controller.gameOverText().split("\n");
		for (int i = 0; i < halfText.length; i++) {

			GLabel gameOver = new GLabel(halfText[i]);
			gameOver.setFont("SansSerif-36");
			int height = (int) ((getHeight() - ((halfText.length) * gameOver.getHeight())) / 2);
			add(gameOver, (getWidth() - gameOver.getWidth()) / 2,
					height + (halfText.length * i) * gameOver.getHeight());
		}
	}
	
	/**
	 * calls addGrect on every Rectangle that is to be put onto the canvas
	 */
	public void addGRects() {
		addGRect(model.getBackground());
		addGRect(model.getGround());
		for (int i = 0; i < model.getObstacleList().size(); i++) {
			addGRect(model.getObstacleList().get(i));
		}
		addGRect(model.getPlayer());
	}

	/**
	 * takes a rectangle and adds a GRect built out of the rectangle to the
	 * canvas
	 * 
	 * @param rec
	 * @return
	 */
	public void addGRect(Rectangle rec) {
		GRect grect = new GRect(rec.getX(), Model.RES_Y - rec.getY() - rec.getHeight(), rec.getWidth(),
				rec.getHeight());
		if (rec.getColor() != null) {
			grect.setColor(rec.getColor());
			grect.setFilled(true);
		}
		add(grect);
	}
}
