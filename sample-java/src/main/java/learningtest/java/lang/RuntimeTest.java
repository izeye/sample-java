package learningtest.java.lang;

public class RuntimeTest {

	public static void main(String[] args) throws InterruptedException {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				System.out.println("test");
			}
		});

		Thread.sleep(Long.MAX_VALUE);
	}

}
