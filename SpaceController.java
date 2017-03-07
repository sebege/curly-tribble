package projektoo;

public class SpaceController extends Controller {

	private SpaceModel model;
	private View view;

	public SpaceController(SpaceModel model, View view) {
		super(model);
		this.model = model;
		this.view = view;
	}

	public void run() {
		Spacecraft ship = model.getSpacecraft();
		model.setLastUpdate(System.currentTimeMillis());
		while (true) {
			updateModel(System.currentTimeMillis());
			view.updateView();
			if (isGameOver(ship, model.getObstacleList())) {
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

	public void jetUp() {
		Spacecraft ship = model.getSpacecraft();
		ship.setJetUp(ship.getJetBoost());
		ship.setGravitationOn(true);
	}

	public void jetUpOff() {
		Spacecraft ship = model.getSpacecraft();
		ship.setJetUp(0);
	}

	public void jetDown() {
		Spacecraft ship = model.getSpacecraft();
		ship.setJetDown(-(ship.getJetBoost()));
	}

	public void jetDownOff() {
		Spacecraft ship = model.getSpacecraft();
		ship.setJetDown(0);
	}

	public void moveUp() {
		Spacecraft ship = model.getSpacecraft();
		ship.setMoveUp(ship.getMovingSpeed());
		ship.setGravitationOn(true);
	}

	public void moveUpOff() {
		Spacecraft ship = model.getSpacecraft();
		ship.setMoveUp(0);
	}

	public void moveDown() {
		Spacecraft ship = model.getSpacecraft();
		ship.setMoveDown(-(ship.getMovingSpeed()));
	}

	public void moveDownOff() {
		Spacecraft ship = model.getSpacecraft();
		ship.setMoveDown(0);
	}

	/*
	 * Update Method
	 */

	public void updateModel(long thisTime) {
		int deltaT = (int) (thisTime - model.getLastUpdate());
		model.getObstacleList().removeOldObstacles();
		// clear and fill the stuffList
		model.getStuff().clear();
		model.getStuff().add(model.getSpace());			
		model.getStuff().add(model.getSpacecraft());
		for (int i = 0; i < model.getObstacleList().size(); i++) {
			model.getStuff().add(model.getObstacleList().get(i));
		}
		model.getStuff().updateAllRectangles(deltaT);
		controlObstacleSpawn();
		model.setLastUpdate(thisTime);
	}

	/*
	 * Control Methods
	 */

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

	/**
	 * adds a new asteroid to the obstacle list every 300 pixel
	 */
	public void controlObstacleSpawn() {
		if (model.getObstacleList().getDistance() > 300) {
			model.getObstacleList().add(new Asteroid(model));

		}
	}
}
