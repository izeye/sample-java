package learningtest.java.net.tcp.server;

import learningtest.java.net.tcp.common.DefaultTcpHandler;
import learningtest.java.net.tcp.common.DefaultTcpHandlerFactory;

public class DefaultTcpServer extends
		AbstractTcpServer<DefaultTcpHandler> {

	public DefaultTcpServer(int port) {
		super(port, new DefaultTcpHandlerFactory());
	}

}
