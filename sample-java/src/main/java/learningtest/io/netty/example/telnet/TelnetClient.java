package learningtest.io.netty.example.telnet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TelnetClient {

	private final String host;
	private final int port;

	public TelnetClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void run() throws InterruptedException, IOException {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
					.handler(new TelnetClientInitializer());

			Channel ch = b.connect(host, port).sync().channel();

			ChannelFuture lastWriteFuture = null;
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			for (;;) {
				String line = in.readLine();
				if (line == null) {
					break;
				}

				lastWriteFuture = ch.writeAndFlush(line + "\r\n");

				if ("bye".equals(line.toLowerCase())) {
					ch.closeFuture().sync();
					break;
				}
			}

			if (lastWriteFuture != null) {
				lastWriteFuture.sync();
			}
		} finally {
			group.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws InterruptedException,
			IOException {
		if (args.length != 2) {
			System.err.println("Usage: "
					+ TelnetClientHandler.class.getSimpleName()
					+ " <host> <port>");
			return;
		}

		String host = args[0];
		int port = Integer.parseInt(args[1]);

		new TelnetClient(host, port).run();
	}

}
