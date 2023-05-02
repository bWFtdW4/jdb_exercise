package learning;

import java.io.IOException;


/**
 * Facade for the company application.
 */
public class CompanyApp2 {

	/**
	 * Prevents external instantiation.
	 */
	private CompanyApp2 () {}


	/**
	 * Application entry point.
	 * @param args the runtime arguments
	 */
	static public void main (final String[] args) throws IOException {
		final CompanyController2 controller = new CompanyController2();

		controller.performWelcome();
		controller.eventSource().execute();

	}
}