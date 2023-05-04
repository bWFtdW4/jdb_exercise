package edu.damago.jdb.world;

import java.io.IOException;
import java.sql.SQLException;


/**
 * Facade for the world application.
 */
public class WorldApp {

	/**
	 * Prevents external instantiation.
	 */
	private WorldApp () {}


	/**
	 * Application entry point.
	 * @param args the runtime arguments
	 * @throws IOException if there is an I/O related problem reading from the shell
	 * @throws SQLException if there is an SQL related problem
	 */
	static public void main (final String[] args) throws IOException, SQLException {
		final WorldController controller = new WorldController();
		controller.rootView().display();
	}
}