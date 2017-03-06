import java.awt.event.KeyEvent;
import java.util.StringTokenizer;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.program.*;

@SuppressWarnings("serial")
public class View extends GraphicsProgram {

	private Controller controller;
	private Model model;
	private int mode;

	public View() {
		this.model = new Model();
		this.controller = new Controller(model);
		controller.setView(this);
	}

	public void init() {
		setSize(Model.RES_X, Model.RES_Y);
		addKeyListeners();
	}

	public void run() {
		getGCanvas().requestFocus();
		Thread ctrlThread = new Thread(controller);
		ctrlThread.start();
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE && mode != 3) {
			controller.jump();
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			controller.duck(System.currentTimeMillis());
		}
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
