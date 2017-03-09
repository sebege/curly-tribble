package projektoo;

import java.awt.Color;
import java.io.IOException;
import java.net.UnknownHostException;

import acm.graphics.GRect;

/**
 * an unmotivated approach of implementing the lighthouse api. extends graphics
 * program so that i can add key listeners.
 */
@SuppressWarnings("serial")
public class SpaceLighthouseView extends SpaceView {

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
	public SpaceLighthouseView() throws UnknownHostException, IOException {
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

	public void gameOverView() {
		// get the spacecraft into a more convenient variable
		Rectangle ship = model.getStuff().get(model.getStuff().size()-1);
		insertRectangle(new Rectangle(ship.getX() - 50, ship.getY() - 50, ship.getWidth() + 100, ship.getHeight() + 100,
				0, 0, 0, 0, model, Color.ORANGE, 0, 0, 0));
		insertRectangle(new Rectangle(ship.getX(), ship.getY(), ship.getWidth(), ship.getHeight(), 0, 0, 0, 0, model,
				Color.RED, 0, 0, 0));
		try {
			this.getNet().send(this.getbArray());
		} catch (IOException e) {
			System.out.println("go get some coffee and fix this.");
		}
		pause(1000);
	}

	/**
	 * constructs the Byte Array that is to be sent to the lighthouse
	 */
	public void updateByteArray() {
		// calls the insert rectangle method for every square that is supposed
		// to be displayed
		for (int i = 0; i < model.getStuff().size(); i++) {
			insertRectangle(model.getStuff().get(i));
		}
	}

	/**
	 * takes a rectangle scales its coordinates and extension down to the low
	 * res view, therefore dividing it into low res pixels and then fils the
	 * appropiate bytes in the byte array with the color of the rectangle. also
	 * inserts the rectangle into the high res canvas.
	 * 
	 * @param rec
	 */
	public void insertRectangle(Rectangle rec) {
		// compute y coordinate. intern origin is at the lower left corner, so
		// it has to be inverted
		int y = (this.lResY - 1) - (rec.getY() / 50);
		// if the original y isn't exactly at the border of an low res square,
		// then adjust for it by rounding up or down
		if (rec.getY() % 50 > 25) {
			y -= 1;
		}
		// same for x and the height and the width
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
		// a nested widht*height loop. one iteration for every square
		for (int l = 0; l < width; l++) {
			for (int m = 0; m < height; m++) {
				if (0 <= x + l && x + l < lResX) {
					// the windows are numerated. this formula aims at the right
					// window
					i = (y - m) * this.lResX + (x + l);
					fillWindow(rec.getColor(), i);
					// addGSquare(rec.getColor(), x+l, y-m);
				}
			}
		}
		// you can't play this game properly in the pixel view, because then you
		// can't control the acceleration, so here the code for the high res
		// view for the client.
		// adds the grect also to the canvas
		GRect grect = new GRect(rec.getX(), model.getResY() - rec.getY() - rec.getHeight(), rec.getWidth(),
				rec.getHeight());
		if (rec.getColor() != null) {
			grect.setColor(rec.getColor());
			grect.setFilled(true);
		}
		add(grect);
	}

	/**
	 * fills the window with the index i from 0 to 391 of an byte[1176] with a
	 * given color
	 * 
	 * @param color
	 * @param i
	 */
	public void fillWindow(Color color, int i) {
		// ignore method, if there is no ith window
		if (i < 392 && 0 <= i) {
			this.getbArray()[i * 3] = (byte) color.getRed();
			this.getbArray()[1 + i * 3] = (byte) color.getGreen();
			this.getbArray()[2 + i * 3] = (byte) color.getBlue();
		}
	}

	/**
	 * takes downscaled coordinates to scale them up again to put a square at
	 * the resulting coordinate onto the canvas
	 * 
	 * @param color
	 * @param x
	 * @param y
	 */
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
