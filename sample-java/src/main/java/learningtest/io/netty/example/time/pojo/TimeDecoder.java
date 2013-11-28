package learningtest.io.netty.example.time.pojo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class TimeDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		if (in.readableBytes() < 4) {
			return;
		}

		long readUnsignedInt = in.readUnsignedInt();
		System.out.println(readUnsignedInt);
		out.add(new UnixTime(readUnsignedInt));
	}

}
