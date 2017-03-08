package projectoo;

import java.awt.event.KeyEvent;

import acm.graphics.GLabel;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class View extends GraphicsProgram {

	public void textTest() {

		GLabel gameOver = new GLabel("Test");
		gameOver.setFont("SansSerif-36");
		int height = (int) ((getHeight() - gameOver.getHeight()) / 2);
		add(gameOver, (getWidth() - gameOver.getWidth()) / 2, height + gameOver.getHeight());
	}

	protected Controller controller;
	protected Model model;
	private int mode;

	// game is still playing or not
	private boolean game;

	public View() {
		this.model = new Model();
		this.controller = new Controller(model, this);
	}

	public void init() {
		setSize(Model.RES_X, Model.RES_Y);
		addKeyListeners();
	}

	/*
	 * here change modi!
	 * 0 = normal
	 * 3 = obstacle
	 */
	public void run() {
		this.game = true;
		this.mode = 3;
		Thread ctrlThread = new Thread(controller);
		ctrlThread.start();
		getGCanvas().requestFocus();
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			controller.jump();
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			controller.duck(System.currentTimeMillis());
		}

		if (e.getKeyCode() == 'W') {
			switch (mode) {
			case 2:
				controller.jetUp();
				break;
			case 3:
				controller.moveUp();
				break;
			}
		}

		if (e.getKeyCode() == 'S') {
			switch (mode) {
			case 2:
				controller.jetDown();
				break;
			case 3:
				controller.moveDown();
				break;
			}
		}

		if (e.getKeyCode() == 'A') {
			switch (mode) {
			case 3:
				controller.moveFasterOn();
			}
		}

		if (e.getKeyCode() == 'D') {
			switch (mode) {
			case 3:
				controller.moveSlowerOn();
			}
		}

		if (e.getKeyCode() == '0' && this.game == false) {
			this.mode = 0;
			this.game = true;
			System.out.println("0");
		}

		if (e.getKeyCode() == '1' && this.game == false) {
			this.mode = 1;
			this.game = true;
			System.out.println("1");
		}

		if (e.getKeyCode() == '2' && this.game == false) {
			this.mode = 2;
			this.game = true;
			System.out.println("2");
		}

		if (e.getKeyCode() == '3' && this.game == false) {
			this.mode = 3;
			this.game = true;
			System.out.println("3");
		}

		if (e.getKeyCode() == 'R' && game == false) {
		}
	}

	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == 'W') {
			switch (mode) {
			case 2:
				controller.jetUpOff();
				break;
			case 3:
				controller.moveUpOff();
				break;
			}
		}

		if (e.getKeyCode() == 'S') {
			switch (mode) {
			case 2:
				controller.jetDownOff();
				break;
			case 3:
				controller.moveDownOff();
				break;
			}
		}

		if (e.getKeyCode() == 'A') {
			switch (mode) {
			case 3:
				controller.moveFasterOff();
			}
		}

		if (e.getKeyCode() == 'D') {
			switch (mode) {
			case 3:
				controller.moveSlowerOff();
			}
		}
	}

	public void updateView() {
	}

	public void gameOverView() {
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public boolean getGame() {
		return this.game;
	}

	public void setGame(boolean game) {
		this.game = game;
	}
}
