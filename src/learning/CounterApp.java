package learning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Facade for the company application.
 */
public class CounterApp {

	/**
	 * Prevents external instantiation.
	 */
	private CounterApp () {}


	/**
	 * Application entry point.
	 * @param args the runtime arguments
	 */
	static public void main (final String[] args) throws IOException {
		final CounterController controller = new CounterController();
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
						controller.performExit();
						break;
					case "display-inventory":
					case "display":
					case "show":
						controller.performDisplayInventory(parameterization);
						break;
					case "sell":
					case "sell-item":
						controller.performSellProduct(parameterization);
						break;
					case "add":
						controller.performAddTestData(parameterization);
						break;
					case "add-item":
						controller.performAddItem(parameterization);
						break;
					case "remove-item":
					case "del":
						controller.performRemoveProduct(parameterization);
						break;
					case "load-inventory":
					case "load":
						controller.performLoadInventory(parameterization);
						break;
					case "save-inventory":
					case "save":
						controller.performSaveInventory(parameterization);
						break;
				}
			} catch (final Exception e) {
				System.err.println(e.getMessage() == null ? e.getClass().getSimpleName() : e.getMessage());
			}
		}
	}
}