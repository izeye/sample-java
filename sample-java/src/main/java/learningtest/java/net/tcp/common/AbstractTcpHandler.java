package learningtest.java.net.tcp.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public abstract class AbstractTcpHandler implements TcpHandler {

	private Socket socket;
	private String remoteIpAddress;

	private BufferedReader br;
	private BufferedWriter bw;

	public AbstractTcpHandler() {
	}

	public AbstractTcpHandler(Socket socket) {
		init(socket);
	}

	@Override
	public void init(Socket socket) {
		this.socket = socket;
		this.remoteIpAddress = ((InetSocketAddress) socket
				.getRemoteSocketAddress()).getAddress().getHostAddress();
		try {
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			this.br = new BufferedReader(new InputStreamReader(is));
			this.bw = new BufferedWriter(new OutputStreamWriter(os));
		} catch (IOException e) {
			e.printStackTrace();

			throw new RuntimeException(e);
		}
	}

	@Override
	public void run() {
		System.out.println("Handler is running for " + remoteIpAddress);

		try {
			String line;
			while ((line = br.readLine()) != null) {
				handle(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getRemoteIpAddress() {
		return remoteIpAddress;
	}

	@Override
	public void send(String message) {
		try {
			bw.write(message);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	protected abstract void handle(String line);

}
