package projektoo;

public class SpaceController implements Runnable {

	private SpaceModel model;
	private SpaceView view;
	private Timer timer;

	public SpaceController(SpaceModel model, SpaceView view) {
		this.model = model;
		this.view = view;
		this.timer = new Timer(SpaceModel.PERIOD);
		timer.reset();
	}

	public void run() {
		enforceGravitation(model.getPlayer());
		while (true) {
			updateModel(System.currentTimeMillis());
			view.updateView();
			if (isGameOver(model.getPlayer(), model.getObstacleList())) {
				timer.pause();
				break;
			}
			timer.pause();
		}
		view.gameOverView();
	}

	/*
	 * Command methods
	 */

	public void jump() {
		model.getPlayer().jump(SpaceModel.JV0);
	}

	public void jetUp() {
		model.getPlayer().setJetUp(3);
		model.getPlayer().setGravitationOn(true);
	}

	public void jetUpOff() {
		model.getPlayer().setJetUp(0);
	}

	public void jetDown() {
		model.getPlayer().setJetDown(-3);
	}

	public void jetDownOff() {
		model.getPlayer().setJetDown(0);
	}

	public void moveUp() {
		model.getPlayer().setMoveUp(900);
		model.getPlayer().setGravitationOn(true);
	}

	public void moveUpOff() {
		model.getPlayer().setMoveUp(0);
	}

	public void moveDown() {
		model.getPlayer().setMoveDown(-900);
	}

	public void moveDownOff() {
		model.getPlayer().setMoveDown(0);
	}

	/**
	 * 
	 * @param duckStart
	 *            the time in milliseconds, the duck command was given
	 */
	public void duck(long duckStart) {
		if (model.getPlayer().getIsDucked() < 0) {
			model.getPlayer().duck(duckStart);
		}
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
		unduckPlayer(thisTime);
		// hitGround(model.getPlayer());
		enforceGravitation(model.getPlayer());
		model.setLastTime(thisTime);
	}

	public void unduckPlayer(long thisTime) {
		if ((model.getPlayer().getIsDucked() > 0) && (thisTime - model.getPlayer().getIsDucked()) >= SpaceModel.DDUR) {
			model.getPlayer().unduck();
		}
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
		int ax2 = a.getX() + a.getWidth();
		int ay1 = a.getY();
		int ay2 = a.getY() + a.getHeight();

		// the 4 different coordinates of the 8 coordinates of the 4 points of b
		int bx1 = b.getX();
		int bx2 = bx1 + b.getWidth();
		int by1 = b.getY();
		int by2 = by1 + b.getHeight();

		// liegt die x koordinate eines punktes von b in der x ausdehnung von a?
		boolean condition1 = (ax1 < bx1 && bx1 < ax2) || (ax1 < bx2 && bx2 < ax2);
		// und eine y koordinate von b in denen von a?
		boolean condition2 = (ay1 < by1 && by1 < ay2) || (ay1 < by2 && by2 < ay2);
		// falls beides der falls ist, so liegt ein punkt von b in der fläche
		// von a

		// liegt die x koordinate eines punktes von ain der x ausdehnung von b?
		boolean condition3 = (bx1 < ax1 && ax1 < bx2) || (bx1 < ax2 && ax2 < bx2);
		// und eine y koordinate von b in denen von a?
		boolean condition4 = (by1 < ay1 && ay1 < by2) || (by1 < ay2 && ay2 < by2);
		// falls beides der falls ist, so liegt ein punkt von a in der fläche
		// von b

		if ((condition1 && condition2) || (condition3 && condition4)) {
			answer = true;
		}

		return answer;
	}

	/**
	 * takes the player and the obstacle list and determines, whether the player
	 * overlaps with an obstacle.
	 * 
	 * @param player
	 * @param obstacleList
	 * @return
	 */
	public boolean isGameOver(Rectangle player, ObstacleList obstacleList) {
		boolean answer = false;
		for (int i = 0; i < obstacleList.size(); i++) {
			Rectangle theObstacle = obstacleList.get(i);
			if (doOverlap(player, theObstacle)) {
				answer = true;
			}
		}
		return answer;
	}

	// verbesserungsvorschlag: diese methode passiert dann, wenn in richtung der
	// gravitation eine kollision passiert
	// dazu muss der boden erstmal zu einem echten objekt werden
	private void hitGround(GeoObject a) {
		if (a.isGravitationOn() && a.getY() <= SpaceModel.GND) {
			a.setVy(0);
			a.setY(SpaceModel.GND);
			a.setGravitationOn(false);
		}
	}

	// setzt Gravitation in Kraft, wenn Objekt über dem Boden ist
	// should not be used on obstacles, because the shall stay in the air, when
	// they are set there
	private void enforceGravitation(GeoObject a) {
		if (a.getY() > SpaceModel.GND) {
			a.setGravitationOn(true);
		}
	}

	/**
	 * experimental method that shall control the spawning flow of new
	 * obstacles. for now i'll make it static, later it should be randomized
	 */
	public void controlObstacleSpawn() {
		if (model.getObstacleList().getDistance() > SpaceModel.OBD) {
				model.getObstacleList().add(new Asteroid(model.getPhysics()));
			
		}
	}
}
