package projektoo;

import java.awt.Color;
import java.awt.event.KeyEvent;

import acm.graphics.GRect;
import acm.program.*;

public class View extends GraphicsProgram {

	private Controller controller;
	private Model model;

	public View() {
		this.model = new Model();
		this.controller = new Controller(model, this);
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
		GRect background = new GRect(0, 0, 1400, 700);
		background.setColor(Color.RED);
		background.setFilled(true);
		add(background);
		GRect ground = new GRect(0, (Model.RES_Y - Model.GNDY), 1400, 90);
		ground.setColor(Color.WHITE);
		ground.setFilled(true);
		add(ground);
		GRect player = makeGRect(model.getPlayer());
		player.setColor(model.getPlayer().getColor());
		player.setFilled(true);
		add(player);
	}

	public GRect makeGRect(Rectangle rec) {
		GRect grect = new GRect(rec.getX(), Model.RES_Y - rec.getY() - rec.getHeight(), rec.getWidth(),
				rec.getHeight());
		return grect;
	}

}
