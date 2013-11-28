package learningtest.akka.actor.example.geekcap.prime;

import learningtest.akka.actor.example.geekcap.prime.message.TreeSetResult;
import akka.actor.UntypedActor;

public class PrimeListener extends UntypedActor {

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof TreeSetResult) {
			TreeSetResult result = (TreeSetResult) message;

			System.out.println("Results:");
			for (Long value : result.getResults()) {
				System.out.print(value + ", ");
			}
			System.out.println();

			getContext().system().shutdown();
		} else {
			unhandled(message);
		}
	}

}
