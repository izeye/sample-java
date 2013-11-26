package learningtest.java.lang;

public class ThreadTest {

	public static void main(String[] args) {
		new Thread() {
			public void run() {
				try {
					Thread.sleep(Long.MAX_VALUE);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

}
