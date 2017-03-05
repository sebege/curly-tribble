package projektoo;

import java.awt.event.KeyEvent;

import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class View extends GraphicsProgram {

	protected Controller controller;
	protected Model model;

	public View() {
		this.model = new Model();
		this.controller = new Controller(model, this);
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
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			controller.jump();
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			controller.duck(System.currentTimeMillis());
		}
		
//		if (e.getKeyCode() == 'W') {
//			controller.jetUp();
//		}
//
//		if (e.getKeyCode() == 'S') {
//			controller.jetDown();
//		}
	}
	
//	public void keyReleased(KeyEvent e) {
//		if (e.getKeyCode() == 'W') {
//			controller.jetUpOff();
//		}
//		
//		if (e.getKeyCode() == 'S') {
//			controller.jetDownOff();
//		}
//	}

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

}
