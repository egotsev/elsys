package org.elsys.first;

public class HelloWorld {

	public static void main(String[] args) {
		System.out.println(getGreeting(args[0]));
	}

	private static String getGreeting(String name) {
		return "Hello, " + name + "'s world!";
	}

}
