package org.elsysbg.courses.it.socket.simpleChat;

/**
 * @author NIE
 * handler, that is used to send messages to other peers in server
 */
public interface ICommandsHandler {
	/**
	 * send message to all peers
	 * 
	 * @param fromNick
	 *            originator nickname
	 * @param messageBody
	 *            message text
	 */
	public void sendMessageToAll(String fromNick, String messageBody);

	/**
	 * disconnect from the chat
	 * 
	 * @param nickname
	 *            nickname to disconnect
	 */
	public void leaveChat(String nickname);
	
	/**
	 * Register client to the server. This should be the first thing client do.
	 * 
	 * @param client
	 *            client to be registered
	 * @param nickname
	 *            unique nickname of the newly registered client
	 * @return true if nick is not used and client is successfully registered.
	 *         false if nick is already used in the server
	 */
	public boolean register(ClientHandler client, String nickname);
}
