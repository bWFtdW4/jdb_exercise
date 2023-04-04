package edu.damago.jdb.calc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Facade for the temperature converter application, version 2.
 */

public class TemperatureConversionApp2 {

	/**
	 * Prevents external instantiation.
	 */
	private TemperatureConversionApp2 () {}


	/**
	 * Application entry point.
	 * @param args the runtime arguments
	 */
	static public void main (final String[] args) throws IOException {
		final BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
		final Temperature2Controller controller = new Temperature2Controller();

		System.out.print("> ");
		while (true) {
			final String consoleLine = consoleReader.readLine().trim();
			final int delimiterPosition = consoleLine.indexOf(' ');
			final String command = (delimiterPosition == -1 ? consoleLine : consoleLine.substring(0, delimiterPosition)).trim().toLowerCase();
			final String parameterization = (delimiterPosition == -1 ? "" : consoleLine.substring(delimiterPosition + 1)).trim();
			//System.out.println("debug:11111 " + parameterization);
			try {
				switch (command) {
					default:
						controller.performHelpCommand(parameterization);
						break;
					case "quit":
					case "exit":
						controller.performQuitCommand(parameterization);
						break;
					case "convert":
					case "cc":
						controller.performConvertCommand(parameterization);
						break;
				}
			} catch (final Exception e) {
				System.err.println(e.getMessage() == null ? e.getClass().getSimpleName() : e.getMessage());
			}

			System.out.print("> ");
		}
	}
}