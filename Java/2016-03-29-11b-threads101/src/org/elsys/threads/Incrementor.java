package org.elsys.threads;

public class Incrementor implements Runnable {

	private Long counter;
	
	public Incrementor(Long counter) {
		this.counter = counter;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10000; i++) {
			counter += 1;
		}
	}

}
