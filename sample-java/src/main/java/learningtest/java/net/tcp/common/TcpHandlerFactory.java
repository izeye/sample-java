package learningtest.java.net.tcp.common;

import java.net.Socket;

public interface TcpHandlerFactory<T extends TcpHandler> {

	T create(Socket socket);

}
