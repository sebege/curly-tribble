package projektoo;

public class StandardController extends Controller {

	private StandardModel model;
	private View view;

	public StandardController(StandardModel model, View view) {
		super(model);
		this.model = model;
		this.view = view;
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
		model.getPlayer().jump(model.getPlayer().getJumpV0());
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
		int deltaT = (int) (thisTime - model.getLastUpdate());
		model.getObstacleList().removeOldObstacles();
		// clear and fill the stuffList
		model.getStuff().clear();
		model.getStuff().add(model.getBackground());
		model.getStuff().add(model.getGround());
		model.getStuff().add(model.getPlayer());
		for (int i = 0; i < model.getObstacleList().size(); i++) {
			model.getStuff().add(model.getObstacleList().get(i));
		}
		model.getStuff().updateAllRectangles(deltaT);
		controlObstacleSpawn();
		model.getPlayer().updateDucking(thisTime);
		hitGround(model.getPlayer());
		enforceGravitation(model.getPlayer());
		model.setLastUpdate(thisTime);
	}

	/*
	 * Control Methods
	 */

	/**
	 * takes the player and the obstacle list and determines, whether the player
	 * overlaps with an obstacle. first searches the list for the oldest
	 * obstacle, that is not behind the player and then calls do overlap on
	 * them.
	 * 
	 * @param player
	 * @param obstacleList
	 * @return
	 */
	public boolean isGameOver(Rectangle player, ObstacleList obstacleList) {
		boolean answer = false;
		if (obstacleList.size() > 0) {
			Rectangle theObstacle = null;
			// the oldest obstacle has the index 0
			int i = 0;
			while (theObstacle == null) {
				// check only the one obstacle with which a collision is possible
				if (obstacleList.get(i).getX() + obstacleList.get(i).getWidth() > player.getX()) {
					theObstacle = obstacleList.get(i);
				}
				i++;
			}
			answer = doOverlap(player, theObstacle);
		}
		return answer;
	}

	// verbesserungsvorschlag: diese methode passiert dann, wenn in richtung der
	// gravitation eine kollision passiert
	// dazu muss der boden erstmal zu einem echten objekt werden
	private void hitGround(GeoObject a) {
		if (a.isGravitationOn() && a.getY() <= model.getGroundHeight()) {
			a.setVy(0);
			a.setY(model.getGroundHeight());
			a.setGravitationOn(false);
		}
	}

	// setzt Gravitation in Kraft, wenn Objekt über dem Boden ist
	// should not be used on obstacles, because the shall stay in the air, when
	// they are set there
	private void enforceGravitation(GeoObject a) {
		if (a.getY() > model.getGroundHeight()) {
			a.setGravitationOn(true);
		}
	}

	/**
	 * experimental method that shall control the spawning flow of new
	 * obstacles. for now i'll make it static, later it should be randomized
	 */
	public void controlObstacleSpawn() {
		if (model.getObstacleList().getDistance() > 500) {
			// if the random generator says "1", the obstacle will spawn low, if
			// it says "0" it'll spawn high
			if (model.getRgen().nextInt(2) == 1) {
				model.getObstacleList().add(new HighObstacle(model));
			} else {
				model.getObstacleList().add(new LowObstacle(model, model.getGroundHeight()));
			}
		}
	}
}
