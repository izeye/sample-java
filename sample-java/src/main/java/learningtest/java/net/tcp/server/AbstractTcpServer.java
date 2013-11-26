package learningtest.java.net.tcp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import learningtest.java.net.tcp.common.TcpHandler;
import learningtest.java.net.tcp.common.TcpHandlerFactory;

public abstract class AbstractTcpServer<T extends TcpHandler> implements
		TcpServer {

	private final int port;
	private final TcpHandlerFactory<T> handlerFactory;

	public AbstractTcpServer(int port, TcpHandlerFactory<T> handlerFactory) {
		this.port = port;
		this.handlerFactory = handlerFactory;
	}

	@Override
	public void start() {
		Map<String, T> handlerMap = new HashMap<String, T>();
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Listening on " + port);
			while (true) {
				System.out.println("Waiting for connecting...");
				Socket socket = serverSocket.accept();

				T handler = handlerFactory.create(socket);
				handlerMap.put(handler.getRemoteIpAddress(), handler);

				new Thread(handler).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

				for (T handler : handlerMap.values()) {
					handler.destroy();
				}
			}
		}
	}

}
