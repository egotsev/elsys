package org.elsys.chat.echo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientRunnable implements Runnable {

	private Socket client;
	private MultiEchoServer server;

	public ClientRunnable(Socket client, MultiEchoServer server) {
		this.client = client;
		this.server = server;
	}

	@Override
	public void run() {
		System.out.println("Accepted connection from client: " + client);

		try (
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		) {
			// waits for data and reads it in until connection dies
			// readLine() blocks until the server receives a new
			// line
			// from client
			String s;
			while ((s = in.readLine()) != null) {
				if (s.equals(MultiEchoServer.CLOSE_COMMAND)) {
					server.close();
					break;
				}
				out.println(s);
			}
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
