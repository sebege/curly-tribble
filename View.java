package projektoo;

import java.awt.Color;
import java.awt.event.KeyEvent;

import acm.graphics.GRect;
import acm.program.*;

public class View extends GraphicsProgram {

	private Controller controller;
	private Model model;
	private Timer timer;

	public View() {
		model = new Model();
		controller = new Controller(model);
		timer = new Timer(Model.INTERVALL);
		timer.reset();
	}

	public void init() {
		setSize(Model.RES_X, Model.RES_Y);
		addKeyListeners();
	}

	public void run() {

		while (true) {
			controller.updateModel(System.currentTimeMillis());
			updateView();
			timer.pause();
		}
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
		GRect ground = new GRect(0, 600, 1400, 10);
		ground.setColor(Color.WHITE);
		ground.setFilled(true);
		add(ground);
		GRect player = makeGRect(model.getPlayer());
		player.setColor(Color.YELLOW);
		player.setFilled(true);
		add(player);
	}

	public GRect makeGRect(Rectangle rec) {
		GRect grect = new GRect(rec.getX(), Model.RES_Y - rec.getY() - rec.getHeight(), rec.getWidth(),
				rec.getHeight());
		return grect;
	}

}
