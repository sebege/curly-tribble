package projektoo;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import acm.program.GraphicsProgram;

/**
 * an unmotivated approach of implementing the lighthouse api. extends graphics
 * program so that i can add key listeners.
 */
public class LighthouseView extends GraphicsProgram {

	/*
	 * ivars
	 */
	private Controller controller;
	private Model model;
	private byte[] bArray;
	private LighthouseNetwork net;
	private int lResX;
	private int lResY;

	/*
	 * constructor
	 */
	public LighthouseView() throws UnknownHostException, IOException {
		this.lResX = 28;
		this.lResY = 14;
		this.bArray = new byte[1176];
		this.net = new LighthouseNetwork();
		this.model = new Model();
		this.controller = new Controller(model);
		this.controller.setlView(this);
		this.net.connect();
	}

	public void init() {
		addKeyListeners();
	}

	public void run() {
		getGCanvas().requestFocus();
		Thread ctrlThread = new Thread(this.getController());
		ctrlThread.start();
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			controller.jump();
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			controller.duck(System.currentTimeMillis());
		}
	}

	/*
	 * useful methods
	 */

	public void updateView() throws IOException {
		this.updateByteArray();
		this.getNet().send(this.getbArray());
	}

	/**
	 * constructs the Byte Array that is to be sent to the lighthouse
	 */
	public void updateByteArray() {
		insertBackground();
		insertGround();
		insertObstacles();
		insertRectangle(this.model.getPlayer());

	}

	// needs proper comments
	public void insertRectangle(Rectangle rec) {
		int y = (this.lResY - 1) - (rec.getY() / 50);
		if (rec.getY() % 50 > 25) {
			y -= 1;
		}
		int x = rec.getX() / 50;
		if (rec.getX() % 50 > 25) {
			x += 1;
		}
		int width = rec.getWidth() / 50;
		if (rec.getWidth() % 50 > 25) {
			width += 1;
		}
		int height = rec.getHeight() / 50;
		if (rec.getHeight() % 50 > 25) {
			height += 1;
		}
		int i;
		for (int l = 0; l < width; l++) {
			for (int m = 0; m < height; m++) {
				if (0 <= x + l && x + l < lResX) {
					i = (y - m) * this.lResX + (x + l);
					fillWindow(rec.getColor(), i);
				}
			}
		}
	}

	public void insertObstacles() {
		ObstacleList list = this.getModel().getObstacleList();
		for (int i = 0; i < list.size(); i++) {
			insertRectangle(list.get(i));
		}
	}

	public void insertGround() {
		Rectangle ground = this.getModel().getGround();
		for (int i = (392 - 56); i < 392; i++) {
			fillWindow(ground.getColor(), i);
		}
	}

	public void insertBackground() {
		Rectangle background = this.getModel().getBackground();
		for (int i = 0; i < 392; i++) {
			fillWindow(background.getColor(), i);
		}
	}

	/**
	 * fills the window with the index i from 0 to 391 of an byte[1176] with a
	 * given color
	 * 
	 * @param color
	 * @param i
	 */
	public void fillWindow(Color color, int i) {
		if (i < 392 && 0 <= i) {
			this.getbArray()[i * 3] = (byte) color.getRed();
			this.getbArray()[1 + i * 3] = (byte) color.getGreen();
			this.getbArray()[2 + i * 3] = (byte) color.getBlue();
		}
	}

	/*
	 * getter and setter
	 */

	public byte[] getbArray() {
		return bArray;
	}

	public void setbArray(byte[] bArray) {
		this.bArray = bArray;
	}

	public LighthouseNetwork getNet() {
		return net;
	}

	public void setNet(LighthouseNetwork net) {
		this.net = net;
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
