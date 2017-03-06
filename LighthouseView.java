package projectoo;

import java.awt.Color;
import java.io.IOException;
import java.net.UnknownHostException;

import acm.graphics.GRect;

/**
 * an unmotivated approach of implementing the lighthouse api. extends graphics
 * program so that i can add key listeners.
 */
@SuppressWarnings("serial")
public class LighthouseView extends View {

	/*
	 * ivars
	 */
	private byte[] bArray;
	private LighthouseNetwork net;
	private int lResX;
	private int lResY;

	/*
	 * constructor
	 */
	public LighthouseView() throws UnknownHostException, IOException {
		super();
		this.lResX = 28;
		this.lResY = 14;
		this.bArray = new byte[1176];
		this.net = new LighthouseNetwork();
		this.net.connect();
	}

	/*
	 * useful methods
	 */
	@Override
	public void updateView() {
		removeAll();
		this.updateByteArray();
		try {
			this.getNet().send(this.getbArray());
		} catch (IOException e) {
			System.out.println("go get some coffee and fix this.");
		}
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
					addGSquare(rec.getColor(), x+l, y-m);
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
			addGSquare(ground.getColor(), i % 28, i / 28);
		}
	}

	public void insertBackground() {
		Rectangle background = this.getModel().getBackground();
		for (int i = 0; i < 392; i++) {
			fillWindow(background.getColor(), i);
			addGSquare(background.getColor(), i % 28, i / 28);
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
	
	public void addGSquare(Color color, int x, int y) {
		GRect grect = new GRect(x * 50, y * 50, 50, 50);
			grect.setColor(color);
			grect.setFilled(true);
			add(grect);
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

	public int getlResX() {
		return lResX;
	}

	public void setlResX(int lResX) {
		this.lResX = lResX;
	}

	public int getlResY() {
		return lResY;
	}

	public void setlResY(int lResY) {
		this.lResY = lResY;
	}

}
