package org.elsys.chat.echo.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class EchoClient extends Thread {

	private static final String TEXT = "the text sent to server";
	private static final String HOST = "localhost";
	private static final int PORT = 4444;

	@Override
	public void run() {
		try (Socket echoSocket = new Socket(HOST, PORT);
				OutputStream outStream = echoSocket.getOutputStream();
				InputStream inStream = echoSocket.getInputStream();) {

			outStream.write(TEXT.getBytes());
			outStream.flush();

			byte[] buffer = new byte[TEXT.length()];
			// do something...
			inStream.read(buffer);
			System.out.println("echo: " + new String(buffer));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		EchoClient client = new EchoClient();
		client.start();
	}
	
}
