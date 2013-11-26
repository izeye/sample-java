package learningtest.java.net.tcp.server.echo;

import java.net.Socket;

import learningtest.java.net.tcp.common.TcpHandlerFactory;

public class TcpEchoHandlerFactory implements TcpHandlerFactory<TcpEchoHandler> {

	@Override
	public TcpEchoHandler create(Socket socket) {
		return new TcpEchoHandler(socket);
	}

}
