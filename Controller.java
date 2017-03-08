package projektoo;

abstract public class Controller implements Runnable {

	protected Timer timer;

	public Controller(Model model) {
		this.timer = new Timer(model.refreshInterval);
		timer.reset();
	}

	abstract public void run();

	abstract public void updateModel(long thisTime);
	
	abstract public void controlObstacleSpawn();

	/**
	 * takes two rectangles as arguments and then computes, whether they overlap
	 * or not
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	protected boolean doOverlap(Rectangle a, Rectangle b) {
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

	/*
	 * Command methods
	 */

	public void jump() {
	}

	public void duck(long duckStart) {
	}

	public void jetUp() {
	}

	public void jetUpOff() {
	}

	public void jetDown() {
	}

	public void jetDownOff() {
	}

	public void moveUp() {
	}

	public void moveUpOff() {
	}

	public void moveDown() {
	}

	public void moveDownOff() {
	}

}
