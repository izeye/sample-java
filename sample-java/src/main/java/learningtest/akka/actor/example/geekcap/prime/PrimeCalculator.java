package learningtest.akka.actor.example.geekcap.prime;

import learningtest.akka.actor.example.geekcap.prime.message.NumberRangeMessage;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class PrimeCalculator {

	public void calculate(long startNumber, long endNumber) {
		ActorSystem actorSystem = ActorSystem.create("primeCalculator");

		final ActorRef primeListener = actorSystem.actorOf(
				Props.create(PrimeListener.class), "primeListener");

		ActorRef primeMaster = actorSystem.actorOf(Props.create(
				PrimeMaster.class, 10, primeListener));

		primeMaster.tell(new NumberRangeMessage(startNumber, endNumber),
				ActorRef.noSender());
	}

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out
					.println("Usage: java learningtest.akka.actor.example.geekcap.prime.PrimeCalculator <start-number> <end-number>");
			System.exit(0);
		}

		long startNumber = Long.parseLong(args[0]);
		long endNumber = Long.parseLong(args[1]);

		PrimeCalculator primeCalculator = new PrimeCalculator();
		primeCalculator.calculate(startNumber, endNumber);
	}

}
