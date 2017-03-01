package projektoo;

public class Obstacle {

	private int startX;
	private int x;
	private int y;
	private int height;
	private int width;
	
	public Obstacle(int x, int y, int width, int height) {
		this.startX = x;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	
	public int getY() {
		return this.y;
	}
	
	public void setY(int y){
		this.y = y;
	}

	public int getNewX() {
		if (this.x <= -this.width)
			return this.x;
		else
			return this.x -= 1;
	}
	
	public int getX(){
		return this.x;
	}
	
	public void setX(int x){
		this.x = x;
	}

	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}

}
