package learningtest.java.net.tcp.server.echo;

import java.net.Socket;

import learningtest.java.net.tcp.common.AbstractTcpHandler;

public class TcpEchoHandler extends AbstractTcpHandler {

	public TcpEchoHandler(Socket socket) {
		super(socket);
	}

	@Override
	protected void handle(String line) {
		System.out.println("Received line: " + line);

		send(line + '\n');
	}

}
