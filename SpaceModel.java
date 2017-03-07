package projektoo;

public class SpaceModel extends Model{

	// our player character
	private Spacecraft spacecraft;
	// kinda like a backround
	private Space space;

	public SpaceModel() {
		// in space rules zero-g, after all.
		super(0);
		this.spacecraft = new Spacecraft(this);
		this.space = new Space(this);
	}

	public Spacecraft getSpacecraft() {
		return spacecraft;
	}

	public void setSpacecraft(Spacecraft spacecraft) {
		this.spacecraft = spacecraft;
	}

	public Space getSpace() {
		return space;
	}

	public void setSpace(Space space) {
		this.space = space;
	}

}
