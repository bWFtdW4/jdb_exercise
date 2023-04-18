package edu.damago.jdb.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Facade for the company application.
 */
public class CompanyApp {

	/**
	 * Prevents external instantiation.
	 */
	private CompanyApp () {}


	/**
	 * Application entry point.
	 * @param args the runtime arguments
	 */
	static public void main (final String[] args) throws IOException {
		final CompanyController controller = new CompanyController();
		final BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

		controller.performWelcome();

		while (true) {
			System.out.print("> ");
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
					case "display-employees":
					case "show":
						controller.performDisplayEmployeesCommand(parameterization);
						break;
					case "add":
						controller.performAddTestData(parameterization);
					case "add-employee":
						controller.performAddEmployeeCommand(parameterization);
						break;
					case "remove-employee":
					case "del":
						controller.performRemoveEmployeeCommand(parameterization);
						break;
					case "load-employees":
					case "load":
						controller.performLoadEmployeesCommand(parameterization);
						break;
					case "save-employees":
					case "save":
						controller.performSaveEmployeesCommand(parameterization);
						break;
				}
			} catch (final Exception e) {
				System.err.println(e.getMessage() == null ? e.getClass().getSimpleName() : e.getMessage());
			}
		}
	}
}