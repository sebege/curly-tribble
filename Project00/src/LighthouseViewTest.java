import java.io.IOException;
import java.net.UnknownHostException;

public class LighthouseViewTest {

	public static Timer timer = new Timer(1);

	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("hello world");
		timer.reset();
		LighthouseNetwork lnet = new LighthouseNetwork();
		lnet.connect();

		byte[] lara = new byte[1176];

		for (int i = 0; i < 1176; i++) {
			lara[i] = -1;
			lnet.send(lara);
			timer.pause();
		}
	}

}
