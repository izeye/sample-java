package learningtest.akka.actor.example.geekcap.prime;

import learningtest.akka.actor.example.geekcap.prime.message.NumberRangeMessage;
import learningtest.akka.actor.example.geekcap.prime.message.TreeSetResult;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinRouter;

public class PrimeMaster extends UntypedActor {

	private final ActorRef workerRouter;
	private final ActorRef listener;

	private final int numberOfWorkers;
	private int numberOfResults = 0;

	private TreeSetResult finalResults = new TreeSetResult();

	public PrimeMaster(final int numberOfWorkers, ActorRef listener) {
		this.numberOfWorkers = numberOfWorkers;
		this.listener = listener;

		workerRouter = this.getContext().actorOf(
				Props.create(PrimeWorker.class).withRouter(
						new RoundRobinRouter(numberOfWorkers)), "workerRouter");
	}

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof NumberRangeMessage) {
			NumberRangeMessage numberRangeMessage = (NumberRangeMessage) message;

			long numberOfNumbers = numberRangeMessage.getEndNumber()
					- numberRangeMessage.getStartNumber();
			long segmentLength = numberOfNumbers / 10;

			for (int i = 0; i < numberOfWorkers; i++) {
				long startNumber = numberRangeMessage.getStartNumber()
						+ (i * segmentLength);
				long endNumber = startNumber + segmentLength - 1;

				if (i == numberOfWorkers - 1) {
					endNumber = numberRangeMessage.getEndNumber();
				}

				workerRouter.tell(
						new NumberRangeMessage(startNumber, endNumber),
						getSelf());
			}
		} else if (message instanceof TreeSetResult) {
			TreeSetResult result = (TreeSetResult) message;
			finalResults.getResults().addAll(result.getResults());

			if (++numberOfResults >= 10) {
				listener.tell(finalResults, getSelf());

				getContext().stop(getSelf());
			}
		} else {
			unhandled(message);
		}
	}

}
