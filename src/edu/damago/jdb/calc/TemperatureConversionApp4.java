package edu.damago.jdb.calc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Facade for the calculator application, version 4.
 */
public class TemperatureConversionApp4 {

	/**
	 * Prevents external instantiation.
	 */
	//private TemperatureConversionApp4 () {}

	/**
	 * Application entry point.
	 * @param args the runtime arguments
	 */
	static public void main (final String[] args) throws IOException {
		final Temperature4Controller controller = new Temperature4Controller();
		final BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

		controller.performWelcome();

		while (true) {
			System.out.print("[Temp]> ");
			final String consoleLine = consoleReader.readLine().trim();

			final int delimiterPosition = consoleLine.indexOf(' ');
			final String command = (delimiterPosition == -1 ? consoleLine : consoleLine.substring(0, delimiterPosition)).trim().toLowerCase();
			final String parameterization = (delimiterPosition == -1 ? "" : consoleLine.substring(delimiterPosition + 1)).trim();

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
					case "ct":
						//System.out.println(parameterization.length());
						if (parameterization.length() < 6) controller.performMultiConvertCommand(parameterization);
						else controller.performSingelConvertCommand(parameterization);
						break;
					case "convertall":
					case "cta":
						controller.performMultiConvertCommand(parameterization);
						break;
				}
			} catch (final Exception e) {
				System.err.println(e.getMessage() == null ? e.getClass().getSimpleName() : e.getMessage());
			}
		}
	}
}