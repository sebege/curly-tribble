package projektoo;

@SuppressWarnings("serial")
public class StandardView extends View {

	protected StandardModel stModel;

	public StandardView() {
		this.stModel = new StandardModel();
		this.model = stModel;
		this.controller = new StandardController(stModel, this);
	}
}
