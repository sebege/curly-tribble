package projectoo;

import java.awt.event.KeyEvent;

import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class View extends GraphicsProgram {

	protected Controller controller;
	protected Model model;
	private int mode;

	public View() {
		this.model = new Model();
		this.controller = new Controller(model, this);
	}

	public void init() {
		setSize(Model.RES_X, Model.RES_Y);
		addKeyListeners();
	}

	public void run() {
		mode = 3;
		getGCanvas().requestFocus();
		Thread ctrlThread = new Thread(controller);
		ctrlThread.start();
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

}
