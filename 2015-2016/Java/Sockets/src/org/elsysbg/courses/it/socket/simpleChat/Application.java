package org.elsysbg.courses.it.socket.simpleChat;

import java.io.IOException;

/**
 * @author NIE
 * Simple application that tests simple chat server
 */
public class Application {

	public static void main(String[] args) throws IOException, InterruptedException {
		final SimpleChatServer server = new SimpleChatServer();
		server.bind();
		server.run();
		
/*		if we want to stop server after given time:
  		new Thread(server).start();
		
		Thread.sleep(15000);
		server.stop();
*/	
	}

}
