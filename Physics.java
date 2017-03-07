package projektoo;

/**
 * contains the physical laws used by us
 *
 */
public class Physics {

	static int zeitWegGesetz(int a, int v, int t) {
		int s = v * t + ((a * t * t) >> 1);
		return s;
	}

	static int zeitGeschwindigkeitGesetz(int a, int t) {
		int v = (a * t);
		return v;
	}

}