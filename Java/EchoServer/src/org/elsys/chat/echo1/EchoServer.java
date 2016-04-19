package org.elsys.chat.echo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	private static final String CLOSE_COMMAND = "CLOSE";
	private static final int PORT = 4444;

	public static void main(String[] args) throws IOException {
		boolean closing = false;
		// create socket
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			System.out.println("Started server on port " + PORT);

			// repeatedly wait for connections, and process
			while (!closing) {

				// a "blocking" call which waits until a connection is requested
				try (Socket clientSocket = serverSocket.accept()) {
					System.out.println("Accepted connection from client: " + clientSocket);

					// open up IO streams
					InputStream inStream = clientSocket.getInputStream();
					OutputStream outStream = clientSocket.getOutputStream();

					try (BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
							PrintWriter out = new PrintWriter(outStream, true);) {
						// waits for data and reads it in until connection dies
						// readLine() blocks until the server receives a new
						// line
						// from client
						String s;
						while ((s = in.readLine()) != null) {
							if (s.equals(CLOSE_COMMAND)) {
								closing = true;
								out.println("good bye!");
								break;
							}
							out.println(s);
						}
					}
				}
			}
		}
	}
}
