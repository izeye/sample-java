package learningtest.java.util;

import java.util.HashMap;

import org.junit.Test;

public class HashMapTest {

	// NOTE:
	// Tried infinite loop based on the following article:
	// http://mailinator.blogspot.kr/2009/06/beautiful-race-condition.html
	//
	// But only data corruption occurred. Why?
	@Test
	public void testRaceCondition() {
		int initialCapacity = 2;
		float loadFactor = 1;

		final HashMap<Integer, Integer> hashMap = new HashMap<>(
				initialCapacity, loadFactor);
		hashMap.put(3, 3);
		hashMap.put(7, 7);

		Thread thread1 = new Thread() {
			@Override
			public void run() {
				System.out.println("Before put, in thread 1, hash map: "
						+ hashMap);
				System.out.println("Thread 1 is trying to put 5.");
				hashMap.put(5, 5);
				System.out.println("Thread 1 put 5.");
				System.out.println("After put, in thread 1, hash map: "
						+ hashMap);
			}
		};
		Thread thread2 = new Thread() {
			@Override
			public void run() {
				System.out.println("Before put, in thread 2, hash map: "
						+ hashMap);
				System.out.println("Thread 2 is trying to put 5.");
				hashMap.put(5, 5);
				System.out.println("Thread 2 put 5.");
				System.out.println("After put, in thread 2, hash map: "
						+ hashMap);
			}
		};
		thread1.start();
		thread2.start();

		try {
			Thread.sleep(Long.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
