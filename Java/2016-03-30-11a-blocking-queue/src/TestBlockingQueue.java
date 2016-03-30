import java.util.ArrayList;
import java.util.List;

public class TestBlockingQueue {

	public static void main(String[] args) {
		BlockingQueue<Double> queue = new BlockingQueue<>(3);
		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			threads.add(new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 10000; i++)
						queue.get();
				}
			}));
			threads.add(new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 10000; i++)
						queue.put(Double.valueOf(Math.random()));
				}
			}));
		}
		for (Thread thread : threads) {
			thread.start();
		}
	}

}
