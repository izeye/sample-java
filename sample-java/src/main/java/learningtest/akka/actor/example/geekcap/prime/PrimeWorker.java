package learningtest.akka.actor.example.geekcap.prime;

import learningtest.akka.actor.example.geekcap.prime.message.NumberRangeMessage;
import learningtest.akka.actor.example.geekcap.prime.message.TreeSetResult;
import akka.actor.UntypedActor;

public class PrimeWorker extends UntypedActor {

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof NumberRangeMessage) {
			NumberRangeMessage numberRangeMessage = (NumberRangeMessage) message;
			System.out.println("Number Range: "
					+ numberRangeMessage.getStartNumber() + " to "
					+ numberRangeMessage.getEndNumber());

			TreeSetResult result = new TreeSetResult();
			for (long l = numberRangeMessage.getStartNumber(); l <= numberRangeMessage
					.getEndNumber(); l++) {
				if (isPrime(l)) {
					result.getResults().add(l);
				}
			}

			getSender().tell(result, getSelf());
		} else {
			unhandled(message);
		}
	}

	private boolean isPrime(long n) {
		if (n < 2) {
			return false;
		}

		if (n == 2 || n == 3) {
			return true;
		}

		if (n % 2 == 0) {
			return false;
		}

		for (long l = 3; l * l <= n; l += 2) {
			if (n % l == 0) {
				return false;
			}
		}

		return true;
	}

}
