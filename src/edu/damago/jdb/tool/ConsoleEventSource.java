package edu.damago.jdb.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Instances of this class generate command events during
 * execution within console based text applications.
 * @author Sascha Baumeister
 */
public class ConsoleEventSource {
	@FunctionalInterface
	static public interface EventListener {
		void execute (String parameterization) throws Exception;
	}

	private String prompt = ">";
	private final Map<String,List<EventListener>> eventListeners = new ConcurrentHashMap<>();


	/**
	 * Returns the command prompt.
	 * @return the command prompt
	 */
	public String getPrompt () {
		return this.prompt;
	}


	/**
	 * Sets the command prompt.
	 * @param prompt the command prompt
	 */
	public void setPrompt (final String prompt) {
		this.prompt = prompt;
	}


	/**
	 * Registers the given event listener for the given kind of command events.
	 * @param command the (lower-case) command event type
	 * @param eventListener the event listener
	 * @throws NullPointerException if any of the given arguments is {@code null}
	 */
	public void addEventListener (final String command, final EventListener eventListener) throws NullPointerException {
		if (command == null | eventListener == null) throw new NullPointerException();
		this.eventListeners.putIfAbsent(command, new ArrayList<>());
		this.eventListeners.get(command).add(eventListener);
	}


	/**
	 * Unregisters the given event listener for the given kind of command events.
	 * @param command the (lower-case) command event type
	 * @param eventListener the event listener
	 * @throws NullPointerException if any of the given arguments is {@code null}
	 */
	public void removeEventListener (final String command, final EventListener eventListener) throws NullPointerException {
		if (command == null | eventListener == null) throw new NullPointerException();
		this.eventListeners.getOrDefault(command, Collections.emptyList()).remove(eventListener);
	}


	/**
	 * Generates command events by repeatedly displaying a command prompt,
	 * waiting for a command input, and finally generating a command event
	 * for said command input.
	 * @throws IOException if there is an I/O related problem with the process I/O streams
	 */
	public void execute () throws IOException {
		final BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.print(this.prompt);
			final String consoleLine = consoleReader.readLine().trim();

			final int delimiterPosition = consoleLine.indexOf(' ');
			final String command = (delimiterPosition == -1 ? consoleLine : consoleLine.substring(0, delimiterPosition)).trim().toLowerCase();
			final String parameterization = (delimiterPosition == -1 ? "" : consoleLine.substring(delimiterPosition + 1)).trim();

			for (final EventListener eventListener : this.eventListeners.getOrDefault(command, Collections.emptyList())) {
				try {
					eventListener.execute(parameterization);
				} catch (final Exception e) {
					System.err.println(e.getMessage() == null ? e.getClass().getSimpleName() : e.getMessage());
				}
			}
		}
	}
}