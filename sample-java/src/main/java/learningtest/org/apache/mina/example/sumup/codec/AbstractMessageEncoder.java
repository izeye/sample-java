package learningtest.org.apache.mina.example.sumup.codec;

import learningtest.org.apache.mina.example.sumup.message.AbstractMessage;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

public abstract class AbstractMessageEncoder<T extends AbstractMessage>
		implements MessageEncoder<T> {

	private final int type;

	protected AbstractMessageEncoder(int type) {
		this.type = type;
	}

	@Override
	public void encode(IoSession session, T message, ProtocolEncoderOutput out)
			throws Exception {
		IoBuffer buf = IoBuffer.allocate(16);
		buf.setAutoExpand(true);

		buf.putShort((short) type);
		buf.putInt(message.getSequence());

		encodebody(session, message, buf);
		buf.flip();
		out.write(buf);
	}

	protected abstract void encodebody(IoSession session, T message,
			IoBuffer out);

}
