package learningtest.java.net.tcp.client;

public class TcpClientRunner {

	public static void main(String[] args) {
		String host = "localhost";
		int port = 10000;

		TcpClient client = new TcpClient(host, port);
		client.start();

		String message = "Hello, world!\n";
		client.send(message);
	}

}
