package learningtest.org.apache.mina.example.sumup;

import learningtest.org.apache.mina.example.sumup.message.AddMessage;
import learningtest.org.apache.mina.example.sumup.message.ResultMessage;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerSessionHandler extends IoHandlerAdapter {

	private static final String SUM_KEY = "sum";

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ServerSessionHandler.class);

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 60);

		session.setAttribute(SUM_KEY, new Integer(0));
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		AddMessage am = (AddMessage) message;

		int sum = ((Integer) session.getAttribute(SUM_KEY)).intValue();
		int value = am.getValue();
		long expectedSum = (long) sum + value;
		if (expectedSum > Integer.MAX_VALUE || expectedSum < Integer.MIN_VALUE) {
			ResultMessage rm = new ResultMessage();
			rm.setSequence(am.getSequence());
			rm.setOk(false);
			session.write(rm);
		} else {
			sum = (int) expectedSum;
			session.setAttribute(SUM_KEY, new Integer(sum));

			ResultMessage rm = new ResultMessage();
			rm.setSequence(am.getSequence());
			rm.setOk(true);
			rm.setValue(sum);
			session.write(rm);
		}
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		LOGGER.info("Disconnecting the idle.");

		session.close(true);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		session.close(true);
	}

}
