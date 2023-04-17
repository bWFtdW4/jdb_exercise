package edu.damago.jdb.calc;

/**
 * Controller for the template application.
 */
public class MainController {

	/**
	 * Performs the welcome command.
	 */
	public void performWelcome () {
		System.out.println("Welcome to the calculator !");
		System.out.println("Use [help] for operators and information.");
	}


	/**
	 * Performs the quit command.
	 * @param parameterization the command parameterization, empty for none
	 * @throws NullPointerException if the given parameterization is null
	 */
	public void performQuitCommand (final String parameterization) throws NullPointerException {
		System.out.println("Bye Bye!");
		System.exit(0);
	}


	/**
	 * Performs the help command.
	 * @param parameterization the command parameterization, empty for none
	 * @throws NullPointerException if the given parameterization is null
	 */
	public void performHelpCommand (final String parameterization) throws NullPointerException {
		System.out.println("Available commands:");
		System.out.println("- [calc]: to start calulator");
		System.out.println("- [calchelp]: to get calulator help");
		System.out.println("- [convert]: to start converter");
		System.out.println("- [converthelp]: to get converter help");
		System.out.println("- [stat]: to start statistics calulator");
		System.out.println("- [stathelp]: to get statistics help");
		System.out.println("- [quit]: terminates this program");
		System.out.println("- [help]: displays this help");
	}

}
