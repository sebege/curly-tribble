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
		 * this method checks, whether one point of one rectangle lies inside the other rectangle or other way round
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
		// falls beides der falls ist, so liegt ein punkt von b in der fläche von a
		
		// liegt die x koordinate eines punktes von b in der x ausdehnung von a?
		boolean condition3 = (bx1 <= ax1 && ax1 <= bx2) || (bx1 <= ax2 && ax2 <= bx2);
		// und eine y koordinate von b in denen von a?
		boolean condition4 = (by1 <= ay1 && ay1 <= bx2) || (by1 <= ay2 && ay2 <= by2);
		// falls beides der falls ist, so liegt ein punkt von b in der fläche von a
		
		if((condition1 && condition2) || (condition3 && condition4)) {
			answer = true;
		}
		
		// not yet implemented

		return answer;
	}

	/**
	 * when there is one constand flat line of ground and the direction of
	 * gravity won't change, then this method can check, whether an object is on
	 * the ground or not
	 * 
	 * @param a
	 *            the object to be checked
	 * @param y
	 *            the y coordinate of the flat ground
	 * @return
	 */
	private boolean inAir(GeoObject a, int y) {
		boolean answer = false;
		// ich sehe nur die gefahr, dass eines objektes y ort beim landen etwas unter null gerät und dann alles kaputt geht
		if(a.getY() > y) {
			answer = true;
		}

		return answer;
	}
	
	private void hitGround(GeoObject a, int y) {
		if (a.isInAir() && (a.getY() <= y)) {
			a.vy = 0;
			a.y = y;
			a.triggerGravitation();
		}
	}

}
