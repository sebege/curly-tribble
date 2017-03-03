package projektoo;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.program.*;

public class View extends GraphicsProgram {

	private Controller controller;
	private Model model;
	private ArrayList<GRect> gRectList;

	public View() {
		this.model = new Model();
		this.controller = new Controller(model, this);
		this.gRectList = new ArrayList<GRect>();
	}

	public void init() {
		setSize(Model.RES_X, Model.RES_Y);
		addKeyListeners();
	}

	public void run() {
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
	}

	public void updateView() {
		removeAll();
		addGRects();
	}
	
	public void gameOverView() {
		removeAll();
		add(new GLabel("his existence was only brief, but he enjoyed it thoroughly."), 50, 200);
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
