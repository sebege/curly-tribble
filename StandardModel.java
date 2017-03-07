package projektoo;

public class StandardModel extends Model {
	
	// marks the y coordinate of our flat ground
	private int groundHeight;
	// marks the distance between obstacles
	private int obstacle_distance;
	private Rectangle player;
	private Rectangle background;
	private Rectangle ground;

	public StandardModel() {
		super(-4);
		this.player = new Jumper(this);
		this.background = new Sky(this);
		this.groundHeight = 100;
		this.ground = new GreenGround(this, groundHeight);
	}

	public int getGroundHeight() {
		return groundHeight;
	}

	public void setGroundHeight(int groundHeight) {
		this.groundHeight = groundHeight;
	}

	public int getObstacle_distance() {
		return obstacle_distance;
	}

	public void setObstacle_distance(int obstacle_distance) {
		this.obstacle_distance = obstacle_distance;
	}

	public Rectangle getPlayer() {
		return player;
	}

	public void setPlayer(Rectangle player) {
		this.player = player;
	}

	public Rectangle getBackground() {
		return background;
	}

	public void setBackground(Rectangle background) {
		this.background = background;
	}

	public Rectangle getGround() {
		return ground;
	}

	public void setGround(Rectangle ground) {
		this.ground = ground;
	}

}
