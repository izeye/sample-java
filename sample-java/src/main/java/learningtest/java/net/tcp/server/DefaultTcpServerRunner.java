package learningtest.java.net.tcp.server;

public class DefaultTcpServerRunner {

	public static void main(String[] args) {
		int port = 10000;

		TcpServer server = new DefaultTcpServer(port);
		server.start();
	}

}
