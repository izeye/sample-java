package learningtest.org.apache.mina.example.sumup;

import learningtest.org.apache.mina.example.sumup.message.AddMessage;
import learningtest.org.apache.mina.example.sumup.message.ResultMessage;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientSessionHandler extends IoHandlerAdapter {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ClientSessionHandler.class);

	private final int[] values;

	private boolean finished;

	public ClientSessionHandler(int[] values) {
		this.values = values;
	}

	public boolean isFinished() {
		return finished;
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		for (int i = 0; i < values.length; i++) {
			AddMessage m = new AddMessage();
			m.setSequence(i);
			m.setValue(values[i]);
			session.write(m);
		}
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		ResultMessage rm = (ResultMessage) message;
		if (rm.isOk()) {
			if (rm.getSequence() == values.length - 1) {
				LOGGER.info("The sum: " + rm.getValue());
				session.close(true);
				finished = true;
			}
		} else {
			LOGGER.warn("Server error, disconnecting...");
			session.close(true);
			finished = true;
		}
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		session.close(true);
	}

}
