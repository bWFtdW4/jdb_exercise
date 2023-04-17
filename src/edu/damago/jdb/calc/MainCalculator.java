package edu.damago.jdb.calc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainCalculator {

	static public void main (final String[] args) throws IOException {
		final MainController mainController = new MainController();
		final Calculator4Controller calcController = new Calculator4Controller();
		final Temperature4Controller tempController = new Temperature4Controller();
		final Statictics1Controller statController = new Statictics1Controller();

		final BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

		mainController.performWelcome();

		while (true) {

			System.out.print("> ");
			final String consoleLine = consoleReader.readLine().trim();

			final int delimiterPosition = consoleLine.indexOf(' ');
			final String command = (delimiterPosition == -1 ? consoleLine : consoleLine.substring(0, delimiterPosition)).trim().toLowerCase();
			final String parameterization = (delimiterPosition == -1 ? "" : consoleLine.substring(delimiterPosition + 1)).trim();

			try {
				switch (command) {
					default:
						mainController.performHelpCommand(parameterization);
						break;
					case "quit":
					case "exit":
						mainController.performQuitCommand(parameterization);
						break;
					case "calc":
						calcController.performCalcCommand(parameterization);
						break;
					case "calchelp":
						calcController.performHelpCommand(parameterization);
						break;
					case "convert":
						if (parameterization.length() < 6) tempController.performMultiConvertCommand(parameterization);
						else tempController.performSingelConvertCommand(parameterization);
						break;
					case "converthelp":
						tempController.performHelpCommand(parameterization);
						break;
					case "sum":
						statController.performSumCommand(parameterization);
						break;
					case "average":
					case "avr":
						statController.performAverageCommand(parameterization);
						break;
					case "product":
					case "pro":
						statController.performProductCommand(parameterization);
						break;
					case "stat":
					case "stathelp":
						statController.performHelpCommand(parameterization);
						break;
				}
			} catch (final Exception e) {
				System.err.println(e.getMessage() == null ? e.getClass().getSimpleName() : e.getMessage());
			}
		}
	}
}
