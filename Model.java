package projektoo;

import java.awt.Color;
import java.util.List;

/**
 * Im Model sammeln sich Konstanten und Objekte und ein paar Methoden. Ein paar
 * Konstanten müssen nicht unbedingt Konstanten bleiben.
 * 
 * @author bsg
 * @param <E>
 *
 */
public class Model<E> {

	/*
	 * constants
	 */

	/*
	 * i guess it is von vorteil, die supportete resolution im model zu
	 * behandeln. die view kann dann hochskalieren oder herunterskalieren. die
	 * werte hier sind praktischerweise ein vielfaches der auflösung des
	 * hochhauses. das sollte alles durch 50 teilbar sein. nah, man darf sich
	 * doch auch mehr genauigkeit erlauben. wenn man dann eine low-res version
	 * auf einem high-res bildschirm spielenmöchte, dann skaliert man auf der
	 * view erst herunter und verliert genauigkeit und skaliert dann wieder
	 * hoch. dann hat mans pixelig. das model liefert daten in seiner präzision
	 * und das view baut sich daraus eine niedrigauflösende sicht. ähnlich wird
	 * man dann für dass pixelarray die graphischen objekte, die man auf das
	 * canvas setzen würde wie man es auf unseren konventionellen views getan
	 * hat, überprüfen, wo im niedrigauflösenden raster sie ausreichend sind, um
	 * sie als "daseiend" bewerten zu können. ich finde, das klingt umsetzbar.
	 * (dies sind selbstgespräche, nevermind)
	 */
	public static final int RESOLUTION_X = 1400;
	public static final int RESOLUTION_Y = 700;

	// wie lange die true-schleife am ende pausiert
	public static final double REFRESH_INTERVALL = 17;

	// constants for the creation of the player rectangle
	public static final int HERO_HEIGHT = 200;
	public static final int HERO_WIDTH = 50;
	public static final int HERO_START_X = 200;
	public static final int HERO_START_Y = 400;
	
	public static final int GROUND_Y = HERO_HEIGHT + HERO_START_Y;
	public static final int GROUND_HEIGHT = RESOLUTION_Y - GROUND_Y;

	// constants for the physics. that's a cool word "physics". we implemented
	// physics. heard that? physics.
	// the start_speed:gravition 20:1 feels like a reasonable relation for now.
	public static final int START_SPEED = 1600;
	public static final int GRAVITATION = 8;
	// adjusts stuff so it fits into the screen. increment one halves
	// everything, decrement doubles it.
	// higher value makes greater precision in the other values possible
	public static final int ADJUSTMENT_RIGHTSHIFT = 10;
	
	// Color constants
	public static final Color BACKGROUND_COLOR = Color.GRAY;
	
	//Obastacle variables
	public static final int OBSTACLE_HEIGHT = 150;
	public static final int OBSTACLE_WIDTH = 50;
	public static final int OBSTACLE_START_X = 1400;
	public static final int OBSTACLE_START_Y_ABOVE = 300;
	public static final int OBSTACLE_START_Y_BELOW = 450;

	static RandomGenerator rgen = new RandomGenerator();
	int random;
	
	int i = 0;

	/*
	 * class variables
	 */

	/*
	 * instance variables
	 */
	private long oldTime;
	private Hero hero;
	private Background background;
	private Ground ground;
	private obstacles;

	/*
	 * constructor
	 */
	public Model() {
	}

	/*
	 * does stuff actually, if one was consequent, this method should be in the
	 * controller, but it feels like it belongs here.
	 */
	public void initNewGame() {
		this.oldTime = System.currentTimeMillis();
		this.hero = new Hero(HERO_START_X, HERO_START_Y, HERO_WIDTH, HERO_HEIGHT, Color.YELLOW);
		this.background = new Background(BACKGROUND_COLOR);
		this.ground = new Ground(GROUND_Y, GROUND_HEIGHT, Color.GREEN);
		this.obstacle = new Obstacle(OBSTACLE_START_X,obstacleState(), OBSTACLE_WIDTH, OBSTACLE_HEIGHT);
		
	}
	
	public int obstacleState() {
		random = rgen.nextInt(1, 2);
		if(i == 1) {
			i= random;
			return OBSTACLE_START_Y_ABOVE;
		}else{
			i= random;
			return OBSTACLE_START_Y_BELOW;
		}
	}

	/*
	 * getters and setters
	 */
	public Hero getHero() {
		return hero;
	}

	public Ground getGround() {
		return ground;
	}

	public Background getBackground() {
		return background;
	}

	public void setOldTime(long oldTime) {
		this.oldTime = oldTime;
	}

	public long getOldTime() {
		return oldTime;
	}
	
	public Obstacle getObstacle() {
		return obstacle;
	}	
}
