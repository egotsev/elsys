package org.elsys.echo;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer extends Thread {

	private ServerSocket socket;

	private boolean running = true;

	private ExecutorService threadPool;

	public EchoServer(int port) {
		try {
			socket = new ServerSocket(port);
		} catch (IOException e) {
			System.err.println("Port already in use.");
			System.exit(-1);
		}
		threadPool = Executors.newFixedThreadPool(10);
		System.out.println("Server started on port " + port);
	}

	@Override
	public void run() {
		while (running) {
			try {
				ClientHandler clientHandler = 
						new ClientHandler(socket.accept(), this);
				threadPool.submit(clientHandler);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void shutDown() {
		System.out.println("Server shutting down...");
		running = false;
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new EchoServer(4444).start();
	}

}
