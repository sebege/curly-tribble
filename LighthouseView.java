package projektoo;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class LighthouseView {


	/*
	 * ivars
	 */
	private Runnable controller;
	private Model model;
	private Timer timer;
	private byte[] bArray;
	private LighthouseNetwork net;
	private ArrayList<GeoObject> stuffList;
	
	/*
	 * constructor
	 */
	public LighthouseView() throws UnknownHostException, IOException {
		this.timer = new Timer(100);
		timer.reset();
		this.bArray = new byte[1176];
		this.stuffList = new ArrayList<GeoObject>();
		this.net = new LighthouseNetwork();
		this.model = new Model();
		this.controller = new Controller(model);
		((Controller) this.controller).setlView(this);
		this.net.connect();
	}

	/**
	 *  our main method that initializes stuff and then starts the loop that sends fresh array to the lighthouse
	 * @param args
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		LighthouseView view = new LighthouseView();
		
		Thread ctrlThread = new Thread(view.getController());
		ctrlThread.start();
		
	}

	/*
	 * useful methods
	 */
	/**
	 * clears and fills our stuffList with all the objects of the model that are to be displayed on the lighthouse
	 */
	public void updateStuffList() {

	}
	
	public void updateView() throws IOException {
		this.updateStuffList();
		this.getNet().send(this.getbArray());
	}

	
	/*
	 * getter and setter
	 */
	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public byte[] getbArray() {
		return bArray;
	}

	public void setbArray(byte[] bArray) {
		this.bArray = bArray;
	}

	public LighthouseNetwork getNet() {
		return net;
	}

	public void setNet(LighthouseNetwork net) {
		this.net = net;
	}

	public ArrayList<GeoObject> getStuffList() {
		return stuffList;
	}

	public void setStuffList(ArrayList<GeoObject> stuffList) {
		this.stuffList = stuffList;
	}

	public Runnable getController() {
		return controller;
	}

	public void setController(Runnable controller) {
		this.controller = controller;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
}
