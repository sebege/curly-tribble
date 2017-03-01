package projektoo;

public class Physics {

	// unit will probably be Pixel per squared Millisecond
	private int gravitation;

	/*
	 * "Pixel per Millisecond" is quite fast. therefore it can be scaled down by
	 * factor 2^this. 10 is an okay value, like dividing by 1000. it'll be done
	 * at the end of the computation in order to maintain precision while using
	 * integer. so with this the unit will be more like
	 * "millipixel per millisecond".
	 */
	private int downscalePower;

	/**
	 * 
	 * @param gravitation
	 * 					gravitation in (milli)Pixel per square Millisecond. should usually be negative.
	 */
	public Physics(int gravitation, int downscalePower) {
		this.gravitation = gravitation;
		this.downscalePower = downscalePower;
	}

	/*
	 * in the following i want to write some methods, that manipulate
	 * coordinates of geometric Objects in dependence of velocity and time. for
	 * uniform accelerated movement
	 */
	public int zeitWegGesetz(int a, int v, int s0, int t) {
		int s = (v * t + a * t * t) >> this.downscalePower + s0;
		return s;
	}

	public int zeitGeschwindigkeitGesetz(int a, int v0, int t) {
		int v = (a * t) >> this.downscalePower + v0;
		return v;
	}

	public int getGravitation() {
		return gravitation;
	}

	public void setGravitation(int gravitation) {
		this.gravitation = gravitation;
	}

}
