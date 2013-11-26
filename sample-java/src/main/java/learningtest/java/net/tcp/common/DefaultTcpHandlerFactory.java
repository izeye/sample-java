package learningtest.java.net.tcp.common;

import java.net.Socket;

public class DefaultTcpHandlerFactory implements
		TcpHandlerFactory<DefaultTcpHandler> {

	@Override
	public DefaultTcpHandler create(Socket socket) {
		return new DefaultTcpHandler(socket);
	}

}
