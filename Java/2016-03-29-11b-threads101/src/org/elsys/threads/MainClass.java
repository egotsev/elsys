package org.elsys.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class MainClass {

	static Long counter = 0L;
	
	static Object lock = new Object();

	public static void main(String[] args) throws InterruptedException {
		List<Thread> threads = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			threads.add(new Thread(() -> {
				for(int j = 0; j < 1000000; j++) {
					synchronized(lock) {
						counter++;
					}
				}
				synchronized(lock) {
					lock.notify();
				}
			}));
		}
		for (Thread thread : threads) {
			thread.start();
		}
		
		synchronized(lock) {
			while (counter < 10_000_000)
				lock.wait();
		}
		System.out.println(counter);
	}

	private static void exampleWithAtomicaLong() throws InterruptedException {
		AtomicLong counter = new AtomicLong();
		List<Thread> threads = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			threads.add(new Thread(() -> {
				for(int j = 0; j < 10000; j++) {
					counter.incrementAndGet();
				}
			}));
		}
		for (Thread thread : threads) {
			thread.start();
		}
		
		Thread.sleep(1000);
		System.out.println(counter.get());
	}

	private static void synchronizedMethodExample() throws InterruptedException {
		SynchronizedLong counter = new SynchronizedLong(0);
		
		List<Thread> threads = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			threads.add(new Thread(() -> {
				for(int j = 0; j < 10000; j++) {
					counter.increment();
				}
			}));
		}
		for (Thread thread : threads) {
			thread.start();
		}
		
		Thread.sleep(1000);
		System.out.println(counter.getValue());
	}

	private static void exampleWithIncrementingLong() throws InterruptedException {
		List<Thread> threads = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			threads.add(new Thread(() -> {
				for(int j = 0; j < 10000; j++) {
					synchronized(lock) {
						counter++;
					}
				}
			}));
		}
		for (Thread thread : threads) {
			thread.start();
		}
		
		Thread.sleep(5000);
		System.out.println(counter);
	}

	/**
	 * Extending java.lang.Thread, implementing java.lang.Runnable.
	 * Anonymous and named classes.
	 */
	private static void firstExample() {
		Thread thread = new ThreadImplementation(); // the thread hasn't started yet
		thread.run(); // won't start a separate thread
		System.out.println("After sync call of thread.run()");
		thread.start();
		
		Thread runnable = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 100; i++) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("I'm awake");
				}
			}
		});
		runnable.start();
		
		System.out.println("Exit from main...");
	}
}
