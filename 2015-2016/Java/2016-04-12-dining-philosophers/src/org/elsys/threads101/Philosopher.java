package org.elsys.threads101;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher implements Runnable {

	private int position;
	private Table table;
	private Random random;
	private String name;

	public Philosopher(int position, String name, Table table) {
		this.position = position;
		this.name = name;
		this.table = table;
		this.random = new Random();
	}
	
	@Override
	public void run() {
		while(true) {
			think();
			System.out.println(name + " is hungry!");
			getForks();
			eat();
			putForks();
		}
	}

	private void eat() {
		try {
			System.out.println(name + " is eating!");
			Thread.sleep(random.nextInt(5));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void putForks() {
		table.left(position).unlock();
		table.right(position).unlock();
	}

	private void getForks() {
		if (position % 5 == 0) {
			table.left(position).lock();
			table.right(position).lock();
		} else {
			table.right(position).lock();
			table.left(position).lock();
		}
	}
	
	

	private void think() {
		try {
			System.out.println(name + " is thinking...");
			Thread.sleep(random.nextInt(5));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
