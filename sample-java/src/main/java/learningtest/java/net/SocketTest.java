package learningtest.java.net;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

public class SocketTest {

	@Test
	public void test() {
		connect("127.0.0.1", 1234);
		connect("10.1.2.3", 1234);
	}

	private void connect(String host, int port) {
		long startTime = System.currentTimeMillis();
		try {
			Socket socket = new Socket(host, port);
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
	}

}
