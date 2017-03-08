package projectoo;

import java.util.ArrayList;

/**
 * this class shall be an ArrayList that contains Obstacles and provides some
 * methods useful for our assignment. i guess it will be comfy to have an own
 * class for it.
 * 
 *
 */
@SuppressWarnings("serial")
public class ObstacleList extends ArrayList<Rectangle> {
	
	private int resX;

	public ObstacleList(int resX) {
		this.resX = resX;
	}

	/**
	 * returns the distance between a hypothetical freshly spawned obstacle and
	 * the obstacle newest obstacle in the list. if the list is empty, it
	 * returns the X Resolution for that there is no obstacle at the whole
	 * distance of the screen.
	 * 
	 * @param i
	 * @return
	 */
	public int getDistance() {
		if (size() >= 1) {
			return resX - (get(size() - 1).getX() + get(size() - 1).getWidth());
		} else {
			return resX;
		}
	}

	/**
	 * removes every obstacle, that left the visible area of the screen, by
	 * removing it, if its right vertical lines x coordinate is below 0
	 */
	public void removeOldObstacles() {
		for (int i = 0; i < size(); i++) {
			if ((get(i).getX()) + (get(i).getWidth()) < 0) {
				remove(i);
			}
		}
	}

	/**
	 * calls the updateObject Method on every obstacle in the list
	 * 
	 * @param deltaT
	 */
	public void updateAllObstacles(int deltaT) {
		for (int i = 0; i < size(); i++) {
			if(get(i) != null)
			get(i).updateObject(deltaT,0);
		}
	}
	
	//delete all obstacles
	public void reset(){
		for(int i = 0; i < size(); i++){
			this.clear();
		}
	}

}
