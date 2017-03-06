
public class Mode3 {
	private Controller controller;
	private Model model;
	int heightCount;

	private final static int HEIGHT_COUNT_LIMIT = 100;

	Mode3() {
		this.model = new Model();
		this.controller = new Controller(model);

	}

	public void run() {
		heightCount = 0;
		while (true) {
			for (int i = 0; i < this.model.getObstacleList().size(); i++) {
				int y = this.model.getObstacleList().get(i).getY();
				if (y > 0 && y < this.model.RES_Y) {
					if(heightCount <= HEIGHT_COUNT_LIMIT){
						
					}
					if(heightCount >= HEIGHT_COUNT_LIMIT){
						
					}
					this.model.getObstacleList().get(i).setY(y);
				}
			}
		}
	}

}
