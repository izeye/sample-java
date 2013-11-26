package learningtest.java.net.tcp.server.echo;

public class TcpEchoServerRunner {

	public static void main(String[] args) {
		int port = 10000;

		TcpEchoServer echoServer = new TcpEchoServer(port);
		echoServer.start();
	}

}
