package org.elsysbg.courses.it.socket.simpleChat;

import static org.elsysbg.courses.it.socket.simpleChat.Messages.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author NIE 
 * Handles single client connected to server
 */
public class ClientHandler extends Thread {

	/**
	 * handler, that is used to send messages to other peers in server
	 */
	final private ICommandsHandler commandsHandler;

	/**
	 * socket, associated with client
	 */
	final private Socket socket;
	/**
	 * input stream, associated with socket
	 */
	final private BufferedReader in;
	/**
	 * output stream, associated with socket
	 */
	final private PrintWriter out;

	/**
	 * nickname of the client
	 * if null - client is guest (can't send/receive messages)
	 */
	private String nickname = null;

	/**
	 * if the connection is already closed
	 */
	private boolean closed = false;

	public ClientHandler(final ICommandsHandler commandsHandler,
			final Socket socket) throws IOException {
		this.commandsHandler = commandsHandler;
		this.socket = socket;
		System.out.println("Accepted connection from client: " + socket); //$NON-NLS-1$

		// open up IO streams
		final InputStream inStream = socket.getInputStream();
		final OutputStream outStream = socket.getOutputStream();

		in = new BufferedReader(new InputStreamReader(inStream));
		out = new PrintWriter(outStream, true);
	}

	/*
	 * handle client requests
	 */
	@Override
	public void run() {
		if (closed) {
			final String message = "Trying to run closed handler for client: " //$NON-NLS-1$
					+ socket;
			System.err.println(message);
			throw new IllegalStateException(message);
		}

		try {
			String s;

			// for every received line - parse command
			while ((s = in.readLine()) != null && parseCommand(s))
				;

		} catch (IOException e) {
			System.err.format(
					"IO Exception while trying to handle client: %s (%s)\n", //$NON-NLS-1$
					socket, e.getMessage());
		} finally {
			close();
		}
	}

	/**
	 * parse and execute command, received from server
	 * 
	 * @param line
	 *            command, received from server
	 * @return false if QUIT message received
	 */
	private boolean parseCommand(final String line) {
		System.out.format("received <<< %s >>> from %s\n", line, nickname); //$NON-NLS-1$
		final String[] parsedCommand = line.split(" ", 2); //$NON-NLS-1$
		if (parsedCommand.length < 1) {
			// empty line?
			return true;
		}

		final String commandName = parsedCommand[0];

		if (commandName.equals(COMMANDS_HELLO)) {
			// command like: HELLO YOUR_NICK
			if(nickname!=null) {
				sendErrorMessage(MESSAGES_ALREADY_LOGGED);
			} else {
				doCommandHello(parsedCommand);
			}
			return true;
		}
		//command is not HELLO
		if(nickname==null) {
			//guests can't do anything, except HELLO
			sendErrorMessage(MESSAGES_NOT_LOGGED);
			return true;
		}
		
		//user is not guest - can send messages
		
		if (commandName.equals(COMMNANDS_QUIT)) {
			// arguments are not checked
			return false;
		} else if (commandName.equals(COMMANDS_SENDA)) {
			doCommandSendAll(parsedCommand);
		} else {
			sendErrorMessage(String.format(MESSAGES_UNKNOWN_COMMAND, commandName));
		}

		return true;
	}

	/**
	 * execute HELLO command
	 * @param parsedCommand array 0 - HELLO, 1 - new nickname
	 */
	private void doCommandHello(String[] parsedCommand) {
		if (parsedCommand.length != 2) {
			sendErrorMessage(MESSAGES_SYNTAX_HELLO);
			return;
		}
		final String newNickname = parsedCommand[1];
		if (newNickname.indexOf(" ") != -1) { //$NON-NLS-1$
			// there is space in nickname
			sendErrorMessage(MESSAGES_NICKNAME_ERROR_SPACE);
			return;
		}
		if (!commandsHandler.register(this, newNickname)) {
			// error while setting name, probably already exists
			sendErrorMessage(String.format(MESSAGES_NICKNAME_ERROR_USED, newNickname));
			return;
		}

		nickname = newNickname;
	}

	/**
	 * send message to the client informing for not successfully executed command
	 * @param message reason
	 */
	private void sendErrorMessage(final String message) {
		sendRawMessage(MESSAGES_PREFIX_ERROR + message);
	}

	/**
	 * execute SENDA command
	 * @param parsedCommand array 0 - SENDA, 1 - message to send
	 */
	private void doCommandSendAll(String[] parsedCommand) {
		if (parsedCommand.length != 2) {
			sendErrorMessage(MESSAGES_SYNTAX_SENDA);
			return;
		}
		commandsHandler.sendMessageToAll(nickname, parsedCommand[1]);
	}

	/**
	 * close IO streams, then socket
	 */
	public void close() {
		if (closed) {
			return;
		}
		System.out.println("Closing connection with client: " + socket); //$NON-NLS-1$
		closed = true;

		// close out stream
		if (out != null) {
			out.close();
		}

		// close in stream
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
				System.err
						.println("IO Exception while trying close input stream for client: " //$NON-NLS-1$
								+ socket);
			}
		}

		// close socket
		try {
			socket.close();
		} catch (IOException e) {
			System.err
					.println("IO Exception while trying close socket for client: " //$NON-NLS-1$
							+ socket);
		}

		commandsHandler.leaveChat(nickname);
	}

	/**
	 * sends message to client socket
	 * 
	 * @param message
	 *            message to send
	 */
	public void sendRawMessage(final String message) {
		out.println(message);
		System.out.format("send <<< %s >>> to %s\n", message, nickname==null?this:nickname); //$NON-NLS-1$
	}

	public String getNickname() {
		return nickname;
	}

}
