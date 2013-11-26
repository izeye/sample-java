package learningtest.org.apache.mina.example.sumup.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import learningtest.org.apache.mina.example.sumup.message.AddMessage;

public class AddMessageEncoder<T extends AddMessage> extends
		AbstractMessageEncoder<T> {

	public AddMessageEncoder() {
		super(Constants.ADD);
	}

	@Override
	protected void encodebody(IoSession session, T message, IoBuffer out) {
		out.putInt(message.getValue());
	}

}
