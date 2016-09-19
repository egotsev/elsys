import java.util.ArrayList;
import java.util.List;

public class MainClass {

	private static Long counter = 0L;

	private static Object lock = new Object();

	public static void main(String[] args) throws InterruptedException {
		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 1_000_000; j++) {
						synchronized (lock) {
							counter++;
						}
					}
					synchronized (lock) {
						lock.notify();
					}
				}
			}));
		}
		for (Thread thread : threads) {
			thread.start();
		}
		synchronized (lock) {
			while (counter != 10_000_000) {
				lock.wait();
			}
		}
		System.out.println(counter);
	}
}
