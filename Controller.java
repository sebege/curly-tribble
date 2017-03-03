package projektoo;

public class Controller implements Runnable {

	private Model model;
	private View view;
	private Timer timer;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		this.timer = new Timer(Model.INTERVALL);
		timer.reset();
	}

	public void run() {
		enforceGravitation(model.getPlayer());
		while (true) {
			updateModel(System.currentTimeMillis());
			view.updateView();
			timer.pause();
		}
	}

	/*
	 * Command methods
	 */

	public void jump() {
		model.getPlayer().jump(Model.JV0);
	}

	/*
	 * Update Method
	 */

	public void updateModel(long thisTime) {
		int deltaT = (int) (thisTime - model.getLastTime());
		model.getPlayer().updateObject(deltaT);
		model.getObstacleList().updateAllObstacles(deltaT);
		model.getObstacleList().removeOldObstacles();
		controlObstacleSpawn();
		hitGround(model.getPlayer());
		model.setLastTime(thisTime);
	}

	/*
	 * Control Methods
	 */
	/**
	 * takes two rectangles as arguments and then computes, whether they overlap
	 * or not
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private boolean doOverlap(Rectangle a, Rectangle b) {
		boolean answer = false;
		/*
		 * this method checks, whether one point of one rectangle lies inside
		 * the other rectangle or other way round
		 */

		// the 4 different coordinates of the 8 coordinates of the 4 points of a
		int ax1 = a.getX();
		int ax2 = ax1 + a.getWidth();
		int ay1 = a.getY();
		int ay2 = ay1 + a.getHeight();

		// the 4 different coordinates of the 8 coordinates of the 4 points of b
		int bx1 = b.getX();
		int bx2 = bx1 + b.getWidth();
		int by1 = b.getY();
		int by2 = by1 + b.getHeight();

		// liegt die x koordinate eines punktes von b in der x ausdehnung von a?
		boolean condition1 = (ax1 <= bx1 && bx1 <= ax2) || (ax1 <= bx2 && bx2 <= ax2);
		// und eine y koordinate von b in denen von a?
		boolean condition2 = (ay1 <= by1 && by1 <= ax2) || (ay1 <= by2 && by2 <= ay2);
		// falls beides der falls ist, so liegt ein punkt von b in der fläche
		// von a

		// liegt die x koordinate eines punktes von b in der x ausdehnung von a?
		boolean condition3 = (bx1 <= ax1 && ax1 <= bx2) || (bx1 <= ax2 && ax2 <= bx2);
		// und eine y koordinate von b in denen von a?
		boolean condition4 = (by1 <= ay1 && ay1 <= bx2) || (by1 <= ay2 && ay2 <= by2);
		// falls beides der falls ist, so liegt ein punkt von b in der fläche
		// von a

		if ((condition1 && condition2) || (condition3 && condition4)) {
			answer = true;
		}

		return answer;
	}

	// verbesserungsvorschlag: diese methode passiert dann, wenn in richtung der
	// gravitation eine kollision passiert
	// dazu muss der boden erstmal zu einem echten objekt werden
	private void hitGround(GeoObject a) {
		if (a.isGravitationOn() && a.getY() <= Model.GND) {
			a.setVy(0);
			a.setY(Model.GND);
			a.setGravitationOn(false);
		}
	}

	// setzt Gravitation in Kraft, wenn Objekt über dem Boden ist
	// should not be used on obstacles, because the shall stay in the air, when
	// they are set there
	private void enforceGravitation(GeoObject a) {
		if (a.getY() > Model.GND) {
			a.setGravitationOn(true);
		}
	}
	
	/**
	 * experimental method that shall control the spawning flow of new obstacles.
	 * for now i'll make it static, later it should be randomized
	 */
	private void controlObstacleSpawn() {
		if(model.getObstacleList().getDistance() > Model.OBD) {
			model.getObstacleList().addNewObstacle(Model.OBLY, Model.OBC);
		}
	}

}
