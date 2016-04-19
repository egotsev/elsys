package org.elsysbg.courses.it.stream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class StreamExample {
	public static void main(String[] args) throws IOException {
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(
				System.in));
		
		System.out.print("Please enter some text: ");
		String line = stdIn.readLine();
		
		PrintWriter stdOut = new PrintWriter(System.out, true);
		stdOut.printf("You have entered %s,\nsome formated output: %15.2f %10d\n", line, 1.1, 5);
	}
}
