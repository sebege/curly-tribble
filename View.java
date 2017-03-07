package projektoo;

import java.awt.event.KeyEvent;

import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class View extends GraphicsProgram {

	protected Controller controller;
	protected Model model;

	public View() {
	}

	public void init() {
		setSize(model.getResX(), model.getResY());
		addKeyListeners();
	}

	public void run() {
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
			controller.jetUp();
		}

		if (e.getKeyCode() == 'S') {
			controller.jetDown();
		}
		
		if (e.getKeyCode() == 'E') {
			controller.moveUp();
		}

		if (e.getKeyCode() == 'D') {
			controller.moveDown();
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 'W') {
			controller.jetUpOff();
		}
		
		if (e.getKeyCode() == 'S') {
			controller.jetDownOff();
		}
		
		if (e.getKeyCode() == 'E') {
			controller.moveUpOff();
		}
		
		if (e.getKeyCode() == 'D') {
			controller.moveDownOff();
		}
	}

	public void updateView() {
	}

	public void gameOverView() {
	}

}
