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

	public void requestRestart() {
		while (getGame() == false) {
			if (getMode() == -1) {
				setLabel(controlText(), 250);
			} else if (getMode() != -1) {
				setLabel(controlText(), 400);
			}
		}
		System.out.println("true");
	}

	public void gameOverView() {
		setLabel(onlyGameOverText(), 100);
		setLabel(gameOverText(), 200);
	}

	/**
	 * calls addGrect on every Rectangle that is to be put onto the canvas
	 */
	public void addGRects() {
		switch (getMode()) {
		case 0:
			addGRect(model.getBackground());
			addGRect(model.getGround());
			for (int i = 0; i < model.getObstacleList().size(); i++) {
				addGRect(model.getObstacleList().get(i));
			}
			addGRect(model.getPlayer());
			break;
		case 3:
			addGRect(model.getBackground());
			for (int i = 0; i < model.getObstacleList().size(); i++) {
				addGRect(model.getObstacleList().get(i));
			}
			addGRect(model.getPlayer());
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
		GRect grect = new GRect(rec.getX(), Model.RES_Y - rec.getY() - rec.getHeight(), rec.getWidth(),
				rec.getHeight());
		if (rec.getColor() != null) {
			grect.setColor(rec.getColor());
			grect.setFilled(true);
		}
		add(grect);
	}

	public String onlyGameOverText() {
		return "GAME OVER";
	}

	// generate random GameOver Text
	public String gameOverText() {
		// control text in case if something went wrong
		String text = "something went terribly wrong";
		switch (model.getRgen().nextInt(2)) {
		case 0:
			text = "Key Vigenère: gameover\n nie ildwkknoi kvw ftlk ffdiw, huf ls zrauyqh wo xyurayucpp.";
			break;
		case 1:
			text = "his existence was only brief, but he enjoyed it thoroughly";
			break;
		default:
			text = "wrong number";
		}
		return text;
	}

	public String controlText() {
		return "R: Resart\n0: normal\n1: nothing\2: nothing\n3: Obstacles";
	}

	public void setLabel(String insertText, int insertHeight) {
		String[] halfText = insertText.split("\n");
		for (int i = 0; i < halfText.length; i++) {
			GLabel text = new GLabel(halfText[i]);
			text.setFont("SansSerif-36");
			add(text, (getWidth() - text.getWidth()) / 2, insertHeight + i * (text.getHeight() + 10));
		}
	}
}
