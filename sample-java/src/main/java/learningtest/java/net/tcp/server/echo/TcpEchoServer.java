package learningtest.java.net.tcp.server.echo;

import learningtest.java.net.tcp.server.AbstractTcpServer;

public class TcpEchoServer extends AbstractTcpServer<TcpEchoHandler> {

	public TcpEchoServer(int port) {
		super(port, new TcpEchoHandlerFactory());
	}

}
