package learningtest.java.net.tcp.common;

import java.net.Socket;

public class DefaultTcpHandler extends AbstractTcpHandler {

	public DefaultTcpHandler() {
	}

	public DefaultTcpHandler(Socket socket) {
		super(socket);
	}

	@Override
	protected void handle(String line) {
		System.out.println(line);
	}

}
