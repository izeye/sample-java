package learningtest.java.net.tcp.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import learningtest.java.net.tcp.common.DefaultTcpHandler;
import learningtest.java.net.tcp.common.TcpHandler;

public class TcpClient {

	private final String host;
	private final int port;

	private final TcpHandler handler;

	public TcpClient(String host, int port) {
		this(host, port, new DefaultTcpHandler());
	}

	public TcpClient(String host, int port, TcpHandler handler) {
		this.host = host;
		this.port = port;

		this.handler = handler;
	}

	public void start() {
		try {
			Socket socket = new Socket(host, port);
			handler.init(socket);
			new Thread(handler).start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void send(String message) {
		handler.send(message);
	}

}
