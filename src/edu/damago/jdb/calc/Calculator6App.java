package edu.damago.jdb.calc;

import java.io.IOException;


/**
 * Facade for the calculator application, version 5.
 */
public class Calculator6App {

	/**
	 * Prevents external instantiation.
	 */
	private Calculator6App () {}


	/**
	 * Application entry point.
	 * @param args the runtime arguments
	 */
	static public void main (final String[] args) throws IOException {
		final Calculator6Controller controller = new Calculator6Controller();

		controller.performWelcome();
		
		controller.eventSource().execute();
		
	}
}