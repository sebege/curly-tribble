package projektoo;

public class Controller implements Runnable{
	
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
			model.updateModel(System.currentTimeMillis());
			view.updateView();
			timer.pause();
		}
	}
	
	public void updateModel(long thisTime) {
		model.updateModel(thisTime);
	}
	
	public void jump() {
		model.getPlayer().jump(Model.JV0);
	}
	
}
