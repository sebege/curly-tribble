package projektoo;

public class Physics {

	private int gravitation;

	public Physics(int gravitation) {
		this.gravitation = gravitation;
	}

	public int zeitWegGesetz(int a, int v, int t) {
		int s = v * t + ((a * t * t) >> 1);
		return s;
	}

	public int zeitGeschwindigkeitGesetz(int a, int t) {
		int v = (a * t);
		return v;
	}

	public int getGravitation() {
		return gravitation;
	}
}