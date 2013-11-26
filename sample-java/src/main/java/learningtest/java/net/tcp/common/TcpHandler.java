package learningtest.java.net.tcp.common;

import java.net.Socket;

public interface TcpHandler extends Runnable {

	void init(Socket socket);

	String getRemoteIpAddress();

	void send(String message);

	void destroy();

}
