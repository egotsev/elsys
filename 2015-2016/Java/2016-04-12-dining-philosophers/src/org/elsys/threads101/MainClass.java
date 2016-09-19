package org.elsys.threads101;

import java.util.Arrays;
import java.util.List;

public class MainClass {

	public static void main(String[] args) {
		List<String> names = Arrays.asList(
				"Aristotle", "Plato", "Thales", "Pythagorus", 
				"Heraclis");
		Table table = new Table(5);
		for(int i = 0; i < 5; i++) {
			Philosopher p = new Philosopher(i, names.get(i), table);
			new Thread(p).start();
		}
		
	}

}
