package org.elsysbg.courses.it.socket.simpleChat;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
	
	/**
	 * message send to clients when server shuts down
	 */
	public static final String MESSAGES_SHUTDOWN = Messages.getString("SimpleChatServer.messages.shutdown");
	
	/**
	 * message send to clients when user is logged in
	 */
	public static final String MESSAGES_REGISTERED = Messages.getString("SimpleChatServer.messages.loggedIn");
	/**
	 * nickname of the system. Used to send system messages
	 */
	public static final String NICKNAME_SYSTEM = Messages.getString("SimpleChatServer.nicknameSystem");
	/**
	 * command passed to clients when somebody sends message to all
	 */
	public static final String RESPONSES_SENDA = Messages.getString("SimpleChatServer.responses.senda");
	/**
	 * command passed to clients when somebody leaves the chat
	 */
	public static final String RESPONSES_LEAVE = Messages.getString("SimpleChatServer.responses.quit");
	
	/**
	 * command passed to clients when somebody logs in
	 */
	public static final String RESPONSES_HELLO = Messages.getString("SimpleChatServer.responses.hello");

	/**
	 * command used by client to leave the chat
	 */
	public static final String COMMNANDS_QUIT = Messages.getString("SimpleChatServer.commands.quit");
	/**
	 * command used by client to send message to all nicknames
	 */
	public static final String COMMANDS_SENDA = Messages.getString("SimpleChatServer.commands.senda");
	/**
	 * command used by client to log in to the chat
	 */
	public static final String COMMANDS_HELLO = Messages.getString("SimpleChatServer.commands.hello");
	/**
	 * response if chosen nickname is already used by other client
	 */
	public static final String MESSAGES_NICKNAME_ERROR_USED = Messages.getString("SimpleChatServer.messages.nickname.error.used");
	/**
	 * response if chosen nickname has spaces in name
	 */
	public static final String MESSAGES_NICKNAME_ERROR_SPACE = Messages.getString("SimpleChatServer.messages.nickname.error.space");
	/**
	 * response if error in SENDA command
	 */
	public static final String MESSAGES_SYNTAX_SENDA = Messages.getString("SimpleChatServer.messages.syntax.senda");
	/**
	 * response if error in HELLO command
	 */
	public static final String MESSAGES_SYNTAX_HELLO = Messages.getString("SimpleChatServer.messages.syntax.hello");
	/**
	 * response if command is not known by the server
	 */
	public static final String MESSAGES_UNKNOWN_COMMAND = Messages.getString("SimpleChatServer.messages.unknownCommand");
	/**
	 * response if guest user try to send message
	 */
	public static final String MESSAGES_NOT_LOGGED = Messages.getString("SimpleChatServer.messages.notLogged");
	/**
	 * response if not guest user tries to log, again
	 */
	public static final String MESSAGES_ALREADY_LOGGED = Messages.getString("SimpleChatServer.messages.alreadyLogged");
	/**
	 * response if specified command could not be executed
	 */
	public static final String MESSAGES_PREFIX_ERROR = Messages.getString("SimpleChatServer.messages.prefix.error");


	private static final String BUNDLE_NAME = "org.elsysbg.courses.it.socket.simpleChat.messages";

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private Messages() {
	}

	private static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
