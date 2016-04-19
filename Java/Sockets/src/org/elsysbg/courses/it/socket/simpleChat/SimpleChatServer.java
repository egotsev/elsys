package org.elsysbg.courses.it.socket.simpleChat;

import static org.elsysbg.courses.it.socket.simpleChat.Messages.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author NIE Receives connections and pass it to ClientHandler
 */
public class SimpleChatServer implements Runnable {


	/**
	 * port to listen for connections on
	 */
	private static final int PORT = 7777;

	/**
	 * socket of the server
	 */
	private ServerSocket serverSocket = null;

	/**
	 * map of the client handlers, connected to the server (nickname - client
	 * handler)
	 */
	private Map<String, ClientHandler> handlers = new HashMap<String, ClientHandler>();

	/**
	 * list with clients, which is not yet registered to the server (only
	 * connected), that is, they are called "guests"
	 */
	private List<ClientHandler> guests = new ArrayList<ClientHandler>();

	/**
	 * simple handler used to send messages to other peers in server
	 */
	private final ICommandsHandler commandsHandler = new ICommandsHandler() {

		private void sendRawMessage(final ClientHandler client,
				final String message) {
			client.sendRawMessage(message);
		}

		private void sendRawMessageToAll(final String message) {
			for (final ClientHandler client : handlers.values()) {
				sendRawMessage(client, message);
			}
		}

		@Override
		synchronized public void leaveChat(final String nickname) {
			handlers.remove(nickname);
			sendRawMessageToAll(String.format(RESPONSES_LEAVE, nickname));
		}

		@Override
		synchronized public void sendMessageToAll(final String fromNick,
				final String messageBody) {
			sendRawMessageToAll(String.format(RESPONSES_SENDA, fromNick,
					messageBody));
		}

		@Override
		synchronized public boolean register(final ClientHandler client, final String nickname) {
			if(handlers.containsKey(nickname)) {
				//nickname already taken
				return false;
			}
			//remove from guests
			guests.remove(client);
			//and add to registered handlers
			handlers.put(nickname, client);
			//informing other clients
			sendRawMessageToAll(String.format(RESPONSES_HELLO, nickname, MESSAGES_REGISTERED));
			return true;
		}
	};

	/**
	 * stop the server
	 */
	synchronized public void stop() {
		if (serverSocket == null) {
			System.err.println("not started: " + this); //$NON-NLS-1$
			return;
		}

		try {
			serverSocket.close();
		} catch (IOException e) {
			System.err.println("IO exception while closing: " + this); //$NON-NLS-1$
		} finally {
			// serverSocket = null; in run()
			System.out.println("Server closed: " + this); //$NON-NLS-1$
		}

		// close all handlers
		commandsHandler.sendMessageToAll(NICKNAME_SYSTEM, MESSAGES_SHUTDOWN);
		for (final ClientHandler handler : handlers.values()) {
			handler.close();
		}
		// close not registered, too (without sending message!)
		for (final ClientHandler handler : guests) {
			handler.close();
		}
	}

	@Override
	public String toString() {
		return String.format("ChatServer on port %d. %d users connected", PORT, //$NON-NLS-1$
				handlers.size());
	}

	/**
	 * accept next client
	 */
	private void acceptClient() {
		final Socket clientSocket;
		final ClientHandler handler;

		try {
			// a "blocking" call which waits until a connection is requested
			clientSocket = serverSocket.accept();

			// create handler
			handler = new ClientHandler(commandsHandler, clientSocket);
		} catch (IOException e) {
			System.err.format("IO Exception while trying to accept %s (%s)\n", //$NON-NLS-1$
					this, e.getMessage());
			return;
		}

		synchronized (this) {
			// add handler to not registered list...
			guests.add(handler);
		}

		// start handler
		handler.start();
	}

	/**
	 * bind server socket to port
	 * 
	 * @throws IOException
	 *             if an I/O error occurs when opening the socket.
	 */
	synchronized public void bind() throws IOException {
		if (serverSocket != null) {
			final String message = "already bind: " + this; //$NON-NLS-1$
			System.err.println(message);
			throw new IllegalStateException(message);
		}

		// create socket
		serverSocket = new ServerSocket(PORT);
		System.out.println("Binded on port " + PORT); //$NON-NLS-1$
	}

	/*
	 * runs the server and start handling clients
	 */
	@Override
	public void run() {
		if (serverSocket == null) {
			final String message = "not initialized: " + this; //$NON-NLS-1$
			System.err.println(message);
			throw new IllegalStateException(message);
		}

		System.out.println("Server started on port " + PORT); //$NON-NLS-1$

		// repeatedly wait for connections, and process
		while (!serverSocket.isClosed()) {
			acceptClient();
		}
		serverSocket = null;
	}

}
