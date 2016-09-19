package org.elsys.lambdas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.OptionalDouble;

public class StreamExample {
	public static void main(String[] args) throws IOException {
		Files.lines(Paths.get("word.txt"))
			.filter(x -> x.length() > 3)
			.map(String::toUpperCase)
			.distinct()
			.forEach(System.out::println);
		
		OptionalDouble average = Files.lines(Paths.get("word.txt"))
			.filter(x -> x.length() < 5)
			.mapToInt(String::hashCode)
			.average();
		if (average.isPresent()) {
			System.out.println("Average hashCode: " + average.getAsDouble());
		}
	}
}
