package org.elsys.lambdas;

import java.util.Arrays;
import java.util.Comparator;

public class SortingExample {

	public static void main(String[] args) {
		String[] words = new String[] { "1", "two", "three", "4", "five" };
		// Sorting prior to Java 8
		Arrays.sort(words, new Comparator<String>() {

			@Override
			public int compare(String arg0, String arg1) {
				return arg0.length() - arg1.length();
			}

		});
		for (String word : words) {
			System.out.print(word + " ");
		}
		// with argument types
		Arrays.sort(words, (String s1, String s2) -> {
			return s1.length() - s2.length();
		});
		// without argument types
		Arrays.sort(words, (s1, s2) -> {
			return s1.length() - s2.length();
		});
		// single lined lambda
		Arrays.sort(words, (s1, s2) -> s1.length() - s2.length());
		Arrays.stream(words).forEach(word -> System.out.print(word + 
				" "));
	}
}
