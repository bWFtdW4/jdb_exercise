package edu.damago.jdb.calc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Facade for the template application.
 */
public class Statictics2App {

	/**
	 * Prevents external instantiation.
	 */
	private Statictics2App () {}


	/**
	 * Application entry point.
	 * @param args the runtime arguments
	 */
	static public void main (final String[] args) throws IOException {
		System.out.println("starting...");
		final Statistics2Controller controller = new Statistics2Controller();
		final BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

		controller.performWelcome();
		
		controller.eventSource().execute();


	}
}