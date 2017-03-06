package projektoo;

import java.awt.event.KeyEvent;

import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class SpaceView extends GraphicsProgram {

	protected SpaceController controller;
	protected SpaceModel model;

	public SpaceView() {
		this.model = new SpaceModel();
		this.controller = new SpaceController(model, this);
	}

	public void init() {
		setSize(SpaceModel.RES_X, SpaceModel.RES_Y);
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

	public SpaceController getController() {
		return controller;
	}

	public void setController(SpaceController controller) {
		this.controller = controller;
	}

	public SpaceModel getModel() {
		return model;
	}

	public void setModel(SpaceModel model) {
		this.model = model;
	}

}
