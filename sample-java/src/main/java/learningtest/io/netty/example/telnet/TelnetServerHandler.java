package learningtest.io.netty.example.telnet;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetAddress;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelnetServerHandler extends SimpleChannelInboundHandler<String> {

	private static final Logger logger = Logger
			.getLogger(TelnetServerHandler.class.getName());

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.write("Welcome to " + InetAddress.getLocalHost().getHostName()
				+ "!\r\n");
		ctx.write("It is " + new Date() + " now.\r\n");
		ctx.flush();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String request)
			throws Exception {
		String response;
		boolean close = false;
		if (request.isEmpty()) {
			response = "Please type something.\r\n";
		} else if ("bye".equals(request.toLowerCase())) {
			response = "Have a good day!\r\n";
			close = false;
		} else {
			response = "Did you say '" + request + "'?\r\n";
		}

		ChannelFuture future = ctx.write(response);

		if (close) {
			future.addListener(ChannelFutureListener.CLOSE);
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		logger.log(Level.WARNING, "Unexpected exception from downstream.",
				cause);
		ctx.close();
	}

}
