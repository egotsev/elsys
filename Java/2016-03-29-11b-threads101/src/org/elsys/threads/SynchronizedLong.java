package org.elsys.threads;

public class SynchronizedLong {
	private long value;
	
	public SynchronizedLong(long value) {
		this.value = value;
	}
	
	public long getValue() {
		return value;
	}
	
	// synchronizes on this
	public synchronized void increment() {
		value++;
	}
}
