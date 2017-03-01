package projektoo;

import acm.program.*;

import java.awt.Color;
import java.awt.event.*;
import acm.graphics.*;

/**
 * Dies ist unsere runnable Class. Diese Klasse extends das GraphicsProgram aus
 * der acm library. Ich bin ein wenig unsicher, wie die funktioniert. Diese
 * Klasse hat einen Konstruktor und zwei Instanzvariablen. Das GraphicsProgram
 * muss beim Ausführen automatisch auch den Konstruktor aufrufen, sonst würde
 * das alles nicht funktionieren, aber es funktionert.
 * 
 * Nun denn. Diese Klasse ist für zwei Sachen zuständig. Zum einen initialisiert
 * sie ein Spiel, indem sie im Konstruktor ein Model erstellt, mit diesem auch
 * einen Controller erstellt, und initNewGame() aufruft. Zum anderen bereitet
 * sie für den Nutzer die View auf mit der true-schleife, und leitet Input
 * Events an den Controller weiter.
 * 
 * @author bsg
 *
 */
public class View extends GraphicsProgram {

	/*
	 * instance variables
	 */
	private Model model;
	private Controller contrl;
	private Timer timer;
	
	private GRect obstacle0;
	private GRect obstacle1;
	private GRect obstacle2;

	private int y0;
	private int y1;
	private int y2;

	private boolean o0;
	private boolean o1;
	private boolean o2;

	private int i;

	/*
	 * constructor
	 */
	public View() {
		this.model = new Model();
		this.contrl = new Controller(model);
		this.timer = new Timer(Model.REFRESH_INTERVALL);
		timer.reset();
	}

	/*
	 * initializes game and adds KeyListener
	 */
	public void init() {
		//
		this.setSize(Model.RESOLUTION_X, Model.RESOLUTION_Y);		
		this.model.initNewGame();


		addKeyListeners();
	}

	/*
	 * contains the loop that refreshes the canvas at given interval
	 */
	public void run() {
		i = 0;
		while (true) {
			this.update();
			this.contrl.updateModel(System.currentTimeMillis());
			this.timer.pause();
		}
	}

	/*
	 * interprets keyboard input as in game commands and sends these to the
	 * controller
	 */
	public void keyPressed(KeyEvent e) {
		// Space means to trigger a jump
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.contrl.jump();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			this.contrl.duck();
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			this.contrl.unduck();
		}
	}

	/*
	 * refreshes the view. first clears the canvas, then takes data from the
	 * model and constructs the view out of that
	 */
	public void update() {
		
		i++;
		
		this.removeAll();
		// adds the player rectangle to the view

		GRect background = makeGRect(this.model.getBackground());
		background.setFillColor(this.model.getBackground().getColor());
		background.setFilled(true);
		this.add(background);
		
		GRect ground = makeGRect(this.model.getGround());
		ground.setFillColor(this.model.getGround().getColor());
		ground.setFilled(true);
		this.add(ground);

		GRect player = makeGRect(this.model.getHero());
		player.setFillColor(this.model.getHero().getColor());
		player.setFilled(true);
		this.add(player);

	}
	
	public GRect makeGRect(Rectangle rec) {
		GRect grect = new GRect(rec.getX(), rec.getY(), rec.getWidth(), rec.getHeight());
		return grect;
	}

}
