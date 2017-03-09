package projectoo;

public class Controller implements Runnable {

	private Model model;
	private View view;
	private Timer timer;
	private int state;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		this.timer = new Timer(Model.PERIOD);
		timer.reset();
	}

	public void run() {
		while (true) {
			enforceGravitation(model.getPlayer());
			//switch between the diffrent MODI
			switch (this.view.getMode()) {
			case 0:
				while (true) {
					updateModel(System.currentTimeMillis());
					view.updateView();
					if (isGameOver(model.getPlayer(), model.getObstacleList())) {
						timer.pause();
						break;
					}
					timer.pause();
				}
				break;
			case 3:
				this.state = 0;

				while (true) {
					updateModel(System.currentTimeMillis());
					view.updateView();
					this.model.getPlayer().setGravitationOn(false);
					moveMode3(this.state);
					// ist auf false für Versuche
					if (isGameOver(model.getPlayer(), model.getObstacleList())) {
						timer.pause();
						break;
					}
					timer.pause();
				}
				break;
			}
			this.view.removeAll();
			//activates only after a game. At the beginnin mode =
			//-1
			if (this.view.getMode() != -1) {
				this.view.setGame(false);
				this.model.getObstacleList().reset();
				this.view.gameOverView();
			}
			this.view.requestRestart();
		}
	}

	/*
	 * Command methods
	 */

	public void jump() {
		model.getPlayer().jump(Model.JV0);
	}

	public void jetUp() {
		model.getPlayer().setJetUp(1);
		model.getPlayer().setGravitationOn(true);
	}

	public void jetUpOff() {
		model.getPlayer().setJetUp(0);
	}

	public void jetDown() {
		model.getPlayer().setJetDown(-1);
	}

	public void jetDownOff() {
		model.getPlayer().setJetDown(0);
	}

	public void moveUp() {
		switch (this.view.getMode()) {
				//2 ist dein ASTROIDENspiel
		case 2:
			model.getPlayer().setMoveUp(500);
			model.getPlayer().setGravitationOn(true);
			break;
		case 3:
			moveAllObstaclesUp(500);
		}
	}

	public void moveUpOff() {
		switch (this.view.getMode()) {
		case 2:
			model.getPlayer().setMoveUp(0);
			break;
		case 3:
			moveAllObstaclesUp(0);
		}
	}

	public void moveDown() {
		switch (this.view.getMode()) {
		case 2:
			model.getPlayer().setMoveDown(-500);
			break;
		case 3:
			moveAllObstaclesDown(-500);
		}
	}

	public void moveDownOff() {
		switch (this.view.getMode()) {
		case 2:
			model.getPlayer().setMoveDown(0);
			break;
		case 3:
			moveAllObstaclesDown(0);
		}
	}

	// make Obstacle faster
	public void moveFasterOn() {
		switch (this.view.getMode()) {
		case 3:
			moveAllObstaclesFaster(-200);
		}
	}

	public void moveFasterOff() {
		switch (this.view.getMode()) {
		case 3:
			moveAllObstaclesFaster(0);
		}
	}

	// make Obstacle slower
	public void moveSlowerOn() {
		switch (this.view.getMode()) {
		case 3:
			moveAllObstaclesSlower(100);
		}
	}

	public void moveSlowerOff() {
		switch (this.view.getMode()) {
		case 3:
			moveAllObstaclesSlower(0);
		}
	}

	// Move all Obstacles
	/**
	 * moves all present obstacle about move high up
	 * 
	 * @param move
	 *            how far the obstacles move up
	 */
	public void moveAllObstaclesUp(int move) {
		for (int i = 0; i < this.model.getObstacleList().size(); i++) {
			//not letting the obstacles move above the top
			if (this.model.getObstacleList().get(i).getY()
					+ this.model.getObstacleList().get(i).getHeight() < Model.RES_Y) {
				this.model.getObstacleList().get(i).setMoveUp(move);
			} else if (this.model.getObstacleList().get(i).getY()
					+ this.model.getObstacleList().get(i).getHeight() >= Model.RES_Y) {
				this.model.getObstacleList().get(i).setMoveUp(0);
			}
		}
	}

	/**
	 * moves all present obstacle about move deep down
	 * 
	 * @param move
	 *            how far the obstacles move down
	 */
	public void moveAllObstaclesDown(int move) {
		for (int i = 0; i < this.model.getObstacleList().size(); i++) {
			//not letting the obstacles move below the bottom
			if (this.model.getObstacleList().get(i).getY() > 0) {
				this.model.getObstacleList().get(i).setMoveDown(move);
			} else if (this.model.getObstacleList().get(i).getY() <= 0) {
				this.model.getObstacleList().get(i).setMoveDown(0);
			}
		}
	}

	public void moveAllObstaclesFaster(int move) {
		for (int i = 0; i < this.model.getObstacleList().size(); i++) {
			this.model.getObstacleList().get(i).setMoveFaster(move);
		}
	}

	public void moveAllObstaclesSlower(int move) {
		for (int i = 0; i < this.model.getObstacleList().size(); i++) {
			this.model.getObstacleList().get(i).setMoveSlower(move);
		}
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

	// moves the player continuously up and down
	public void moveMode3(int state) {

		if (state == 0) {
			model.getPlayer().setMoveUp(450);
			if (model.getPlayer().getY() + model.getPlayer().getHeight() >= Model.RES_Y) {
				this.state = 1;
				model.getPlayer().setMoveUp(0);
			}
		} else if (state == 1) {
			model.getPlayer().setMoveDown(-450);
			if (model.getPlayer().getY() <= 0) {
				this.state = 0;
				model.getPlayer().setMoveDown(0);
			}
		}
	}

	/*
	 * Update Method
	 */

	public void updateModel(long thisTime) {
		int deltaT = (int) (thisTime - model.getLastTime());
		model.getPlayer().updateObject(deltaT, 1);
		controlObstacleSpawn();
		if (model.getObstacleList() != null) {
			model.getObstacleList().updateAllObstacles(deltaT);
			model.getObstacleList().removeOldObstacles();
		}
		if (this.view.getMode() != 3) {
			unduckPlayer(thisTime);
		}
		hitGround(model.getPlayer());
		enforceGravitation(model.getPlayer());
		model.setLastTime(thisTime);
	}

	public void unduckPlayer(long thisTime) {
		if ((model.getPlayer().getIsDucked() > 0) && (thisTime - model.getPlayer().getIsDucked()) >= Model.DDUR) {
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
		// falls beides der falls ist, so liegt ein punkt von b in der fl??che
		// von a

		// liegt die x koordinate eines punktes von ain der x ausdehnung von b?
		boolean condition3 = (bx1 < ax1 && ax1 < bx2) || (bx1 < ax2 && ax2 < bx2);
		// und eine y koordinate von b in denen von a?
		boolean condition4 = (by1 < ay1 && ay1 < by2) || (by1 < ay2 && ay2 < by2);
		// falls beides der falls ist, so liegt ein punkt von a in der fl??che
		// von b

		if ((condition1 && condition2) || (condition3 && condition4)) {
			answer = true;
		}

		return answer;
	}

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
				// if the right vertical border of the obstacle is on the right
				// of
				// the player, then a collision is still possible
				// only the obstacles with the lowest indexes could have
				// surpassed
				// the player.
				// and always only the first possible obstacle is woth checking
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
		if (a.isGravitationOn() && a.getY() <= Model.GND) {
			a.setVy(0);
			a.setY(Model.GND);
			a.setGravitationOn(false);
		}
	}

	// setzt Gravitation in Kraft, wenn Objekt ??ber dem Boden ist
	// should not be used on obstacles, because the shall stay in the air, when
	// they are set there
	private void enforceGravitation(GeoObject a) {
		if (a.getY() > Model.GND) {
			a.setGravitationOn(true);
		}
	}

	/**
	 * experimental method that shall control the spawning flow of new
	 * obstacles. for now i'll make it static, later it should be randomized
	 */
	public void controlObstacleSpawn() {
		if (model.getObstacleList().getDistance() > Model.OBD) {
			// if the random generator says "1", the obstacle will spawn low, if
			// it says "0" it'll spawn high
			if (model.getRgen().nextInt(2) == 1) {
				model.getObstacleList().add(new Rectangle(1400, Model.OBLY, Model.OBW, Model.OBLH,
						model.getObstacleSpeed(), 0, 0, 0, model.getPhysics(), Model.OBC, 0, 1));
			} else {
				model.getObstacleList().add(new Rectangle(1400, Model.OBHY, Model.OBW, Model.OBHH,
						model.getObstacleSpeed(), 0, 0, 0, model.getPhysics(), Model.OBC, 0, 0));
			}
		}
	}
}
