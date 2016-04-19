package org.elsys.chat.echo2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiEchoServer extends Thread {

	public static final String CLOSE_COMMAND = "CLOSE";
	private static final int PORT = 4444;

	public static void main(String[] args) throws IOException {
		
		MultiEchoServer server = new MultiEchoServer();
		server.start();
	}
	
	@Override
	public void run() {
		// create socket
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			System.out.println("Started server on port " + PORT);

			// repeatedly wait for connections, and process
			while (!closing) {

				// a "blocking" call which waits until a connection is requested
				try (Socket clientSocket = serverSocket.accept()) {
					ClientRunnable clientRunnable = new ClientRunnable(clientSocket, this);
					new Thread(clientRunnable).start();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean closing;

	public void close() {
		this.closing = true;
		System.out.println("bye!");
	}
}
