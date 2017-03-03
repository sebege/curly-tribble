package projektoo;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

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
	}

	public void updateView() {
		removeAll();
//		GRect background = new GRect(0, 0, 1400, 700);
//		background.setColor(Color.BLUE);
//		background.setFilled(true);
//		add(background);
//		GRect ground = new GRect(0, (Model.RES_Y - Model.GND), 1400, 90);
//		ground.setColor(Color.WHITE);
//		ground.setFilled(true);
//		add(ground);
		buildGRectList();
		for(int i=0; i < gRectList.size(); i++) {
			add(gRectList.get(i));
		}
	}
	
	/**
	 * takes information out of the model to build a list with all the GRects to
	 * put onto the canvas.
	 */
	public void buildGRectList() {
		gRectList.clear();
		gRectList.add(makeGRect(model.getBackground()));
		gRectList.add(makeGRect(model.getGround()));
		gRectList.add(makeGRect(model.getPlayer()));
		for (int i = 0; i < model.getObstacleList().size(); i++) {
			gRectList.add(makeGRect(model.getObstacleList().get(i)));
		}
	}

	/**
	 * takes a rectangle and return a GRect built out of the rectangle
	 * 
	 * @param rec
	 * @return
	 */
	public GRect makeGRect(Rectangle rec) {
		GRect grect = new GRect(rec.getX(), Model.RES_Y - rec.getY() - rec.getHeight(), rec.getWidth(),
				rec.getHeight());
		if (rec.getColor() != null) {
			grect.setColor(rec.getColor());
			grect.setFilled(true);
		}
		return grect;
	}


}
