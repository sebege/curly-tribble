package projektoo;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class LighthouseView {

	/*
	 * ivars
	 */
	private Timer timer;
	private byte[] bArray;
	private LighthouseNetwork net;
	private ArrayList<GeoObject> stuffList;
	
	/*
	 * constructor
	 */
	public LighthouseView() {
		this.timer = new Timer(100);
		timer.reset();
		this.bArray = new byte[1176];
		this.stuffList = new ArrayList<GeoObject>();
		this.net = new LighthouseNetwork();
	}

	/**
	 *  our main method that initializes stuff and then starts the loop that sends fresh array to the lighthouse
	 * @param args
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		LighthouseView view = new LighthouseView();
		view.getNet().connect();
		
		// main loop
		while (true) {
			// our stuff List needs to be updated every cycle
			view.updateStuffList();
			view.getNet().send(view.getbArray());
			view.getTimer().pause();
		}
	}

	/*
	 * useful methods
	 */
	/**
	 * clears and fills our stuffList with all the objects of the model that are to be displayed on the lighthouse
	 */
	public void updateStuffList() {

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
}
