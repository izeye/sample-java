package learningtest.io.netty.example.telnet;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class TelnetClientInitializer extends ChannelInitializer<SocketChannel> {

	private static final StringDecoder DECODER = new StringDecoder();
	private static final StringEncoder ENCODER = new StringEncoder();
	private static final TelnetClientHandler CLIENT_HANDLER = new TelnetClientHandler();

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();

		pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192,
				Delimiters.lineDelimiter()));
		pipeline.addLast("decoder", DECODER);
		pipeline.addLast("encoder", ENCODER);

		pipeline.addLast("handler", CLIENT_HANDLER);
	}

}
