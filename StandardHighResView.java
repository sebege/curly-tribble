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
		String gameOverText = null;
		int r = model.rgen.nextInt(2);
		if (r == 1) {
			gameOverText = "his existence was only brief, but he enjoyed it thoroughly.";
		} else {
			gameOverText = "Key Vigenère: gameover \n nie ildwkknoi kvw ftlk ffdiw, huf ls zrauyqh wo xyurayucpp.";
		}
		String[] halfText = gameOverText.split("\n");
		for (int i = 0; i < halfText.length; i++) {
			GLabel gameOver = new GLabel(halfText[i]);
			gameOver.setFont("Serif-36");
			int height = (int) ((getHeight() - ((halfText.length) * gameOver.getHeight())) / 2);
			add(gameOver, (getWidth() - gameOver.getWidth()) / 2,height + (halfText.length * i) * gameOver.getHeight());
		}
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
