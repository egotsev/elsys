package org.elsys.echo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {

	private Socket server;

	public Client(String host, int port) {
		try {
			server = new Socket(host, port);
		} catch (IOException e) {
			System.err.println("Server not found..");
		}
	}
	
	@Override
	public void run() {
		try (PrintWriter outWriter = 
				new PrintWriter(server.getOutputStream(), true);
			Scanner serverScanner = new Scanner(server.getInputStream());
			Scanner consoleScanner = new Scanner(System.in);
		) {
			String s;
			while((s = consoleScanner.nextLine()) != null) {
				outWriter.println(s);
				String echo = serverScanner.nextLine();
				System.out.println(echo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Client("localhost", 4444).start();
	}

}
