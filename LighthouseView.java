package projektoo;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import acm.program.GraphicsProgram;

/**
 * an unmotivated approach of implementing the lighthouse api.
 * extends graphics program so that i can add key listeners.
 */
public class LighthouseView extends GraphicsProgram {

	/*
	 * ivars
	 */
	private Controller controller;
	private Model model;
	private byte[] bArray;
	private LighthouseNetwork net;
	private ArrayList<GeoObject> stuffList;
	private int lResX;
	private int lResY;

	/*
	 * constructor
	 */
	public LighthouseView() throws UnknownHostException, IOException {
		this.lResX = 28;
		this.lResY = 14;
		this.bArray = new byte[1176];
		this.stuffList = new ArrayList<GeoObject>();
		this.net = new LighthouseNetwork();
		this.model = new Model();
		this.controller = new Controller(model);
		this.controller.setlView(this);
		this.net.connect();
	}

	/**
	 * our main method that initializes stuff and then starts the loop that
	 * sends fresh array to the lighthouse
	 * 
	 * @param args
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	// public static void main(String[] args) throws UnknownHostException,
	// IOException {
	//
	// LighthouseView view = new LighthouseView();
	//
	// Thread ctrlThread = new Thread(view.getController());
	// ctrlThread.start();
	//
	// }

	public void init() {
		addKeyListeners();
	}

	public void run() {
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
	/**
	 * constructs the Byte Array that ist to be sent to the lighthouse
	 */
	public void updateByteArray() {
		insertBackground();
		insertGround();
		insertObstacles();
		insertPlayer();

	}

	public void insertObstacles() {
		ObstacleList list = this.getModel().getObstacleList();
		for (int k = 0; k < list.size(); k++) {
			Rectangle obstacle = list.get(k);
			int x = obstacle.getX() / 50;
			if (obstacle.getX() % 50 > 25) {
				x += 1;
			}
			int y = lResY - (obstacle.getY() / 50);
			int i;
			int width = obstacle.getWidth() / 50;
			int height = obstacle.getHeight() / 50;
			// ich brauche eine downscale methode, die durch 50 teilt
			for (int l = 0; l < width; l++) {
				for (int m = 0; m < height; m++) {
					i = (y - m) * this.lResX + (x + l);
					fillWindow(obstacle.getColor(), i);
				}
			}
		}
	}

	public void insertGround() {
		Rectangle ground = this.getModel().getGround();
		for (int i = (392 - 28); i < 392; i++) {
			fillWindow(ground.getColor(), i);
		}
	}

	/**
	 * another unelegant method that changes every window to the color of the
	 * background
	 */
	public void insertBackground() {
		Rectangle background = this.getModel().getBackground();
		for (int i = 0; i < 392; i++) {
			fillWindow(background.getColor(), i);
		}
	}

	/**
	 * a very unelegant method that puts the player into the byte arrays. much
	 * room for improvement. heavily dependent on the current setting of the
	 * model.
	 */
	public void insertPlayer() {
		Rectangle player = this.getModel().getPlayer();
		int tempy = player.getY() / 50;
		int y = this.lResY - tempy;
		if (player.getY() % 50 > 25) {
			y -= this.lResX;
		}
		int x = player.getX() / 50;
		int i;
		int width = player.getWidth() / 50;
		int height = player.getHeight() / 50;
		for (int l = 0; l < width; l++) {
			for (int m = 0; m < height; m++) {
				i = (y - m) * this.lResX + (x + l);
				fillWindow(player.getColor(), i);
			}
		}
	}

	/**
	 * fills the window with the index i from 0 to 391 of an byte[1176] with a
	 * color
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

	/**
	 * clears and fills our stuffList with all the objects of the model that are
	 * to be displayed on the lighthouse
	 */
	public void updateStuffList() {
		this.getStuffList().clear();
		this.getStuffList().add(this.getModel().getBackground());
		this.getStuffList().add(this.getModel().getGround());
		this.getStuffList().add(this.getModel().getPlayer());
		for (int i = 0; i < this.getModel().getObstacleList().size(); i++) {
			this.getStuffList().add(this.getModel().getObstacleList().get(i));
		}
	}

	public void updateView() throws IOException {
		this.updateByteArray();
		this.getNet().send(this.getbArray());
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

	public ArrayList<GeoObject> getStuffList() {
		return stuffList;
	}

	public void setStuffList(ArrayList<GeoObject> stuffList) {
		this.stuffList = stuffList;
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
