package projektoo;

@SuppressWarnings("serial")
public class SpaceView extends View {

	protected SpaceModel spModel;
	public SpaceView() {
		this.spModel = new SpaceModel();
		this.model = spModel;
		this.controller = new SpaceController(spModel, this);
	}
}
