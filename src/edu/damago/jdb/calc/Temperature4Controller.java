package edu.damago.jdb.calc;

/**
 * Controller for the temperature converter application, version 4.
 */

public class Temperature4Controller {

	static {
		TemperatureConverter4.initializePool();
	}


	/**
	 * Performs the welcome command.
	 */
	public void performWelcome () {
		System.out.println("Welcome to the temperature converter v4 !");
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
		System.out.println("- convert:	<temperature> <unit> <target unit> to convert to a specifec temperature (e.g. 30 C F)");
		System.out.println("- convertall:	<temperature> <unit> to convert temperature in all variety (e.g. 30 C)");
		System.out.println("- quit: terminates this program");
		System.out.println("- help: displays this help");
	}


	/**
	 * Performs the convert command for a single target unit.
	 * @param parameterization the command parameterization, empty for none
	 * @throws NullPointerException if the given parameterization is null
	 * @throws IllegalStateException if the source or target unit is illegal
	 */
	public void performSingelConvertCommand (final String parameterization) throws NullPointerException {
		final String[] arguments = parameterization.split("\\s+");
		if (arguments.length != 3) throw new IllegalArgumentException("must pass exactly three arguments!");

		double temperatur;
		try {
			temperatur = Double.parseDouble(arguments[0]);
		} catch (final NumberFormatException e) {
			temperatur = Double.NaN;
		}
		final char sourceUnit = arguments[1].toUpperCase().charAt(0);
		final char targetUnit = arguments[2].toUpperCase().charAt(0);

		performConversionCommand(temperatur, sourceUnit, targetUnit);
	}


	/**
	 * Performs the convert command for all possible target units.
	 * @param parameterization the command parameterization, empty for none
	 * @throws NullPointerException if the given parameterization is null
	 * @throws IllegalStateException if the source or target unit is illegal
	 */
	public void performMultiConvertCommand (final String parameterization) throws NullPointerException {
		double temperatur;
		final String[] arguments = parameterization.split("\\s+");
		if (arguments.length != 2) throw new IllegalArgumentException("must pass two arguments!");

		try {
			temperatur = Double.parseDouble(arguments[0]);
		} catch (final NumberFormatException e) {
			temperatur = Double.NaN;
		}
		final char sourceUnit = arguments[1].toUpperCase().charAt(0);

		final char[] UNITS = { 'K', 'C', 'F' };
		for (final char targetUnit : UNITS) {	// for-each
			if (sourceUnit != targetUnit) {
				performConversionCommand(temperatur, sourceUnit, targetUnit);
			}
		}
	}


	/**
	 * Performs temperature conversion and displays the result.
	 * @param temperature the temperature value
	 * @param sourceUnit the source unit
	 * @param targetUnit the target unit
	 * @throws IllegalStateException if the source or target unit is illegal
	 */
	static protected void performConversionCommand (final double temperature, final char sourceUnit, final char targetUnit) throws IllegalStateException {
		final String operator = (sourceUnit + "" + targetUnit);
		final TemperatureConverter4 converter = TemperatureConverter4.POOL.get(operator);
		final double result = converter.convert(temperature);

		System.out.println("Result: " + temperature + "°" + sourceUnit + " = " + result + "°" + targetUnit);
	}

}