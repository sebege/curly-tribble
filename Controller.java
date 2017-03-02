package projektoo;

public class Controller {
	
	private Model model;

	public Controller(Model model) {
		this.model = model;
	}
	
	public void updateModel(long thisTime) {
		model.updateModel(thisTime);
	}
	
	public void jump() {
		model.getPlayer().jump(Model.JV0);
	}
	
}
