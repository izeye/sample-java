package learningtest.io.netty.example.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

public class FirstSolutionTimeClientHandler extends
		ChannelInboundHandlerAdapter {

	private ByteBuf buf;

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		buf = ctx.alloc().buffer(4);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		buf.release();
		buf = null;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ByteBuf m = (ByteBuf) msg;
		buf.writeBytes(m);
		m.release();

		if (buf.readableBytes() >= 4) {
			long currentTimeMills = (buf.readUnsignedInt() - 2208988800L) * 1000;
			System.out.println(new Date(currentTimeMills));
			ctx.close();
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
