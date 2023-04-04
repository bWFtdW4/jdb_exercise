package edu.damago.jdb.calc;

/**
 * Facade for the calculator application, version 2.
 */
public class Temperature2Controller {

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
		System.out.println("- quit: terminates this program");
		System.out.println("- help: displays this help");
		System.out.println("- use [convert] or [cc] <temperature> <symbol> to convert temperature in all variety");
		System.out.println("- use [convert] or [cc] <temperature> <symbol> <symbol> to convert to a specifec temperature");
	}


	/**
	 * Performs the convert command.
	 * @param parameterization the command parameterization, empty for none
	 * @throws NullPointerException if the given parameterization is null
	 */
	public void performConvertCommand (final String parameterization) throws NullPointerException {
		final String[] parts = parameterization.split("\\s+");

		try {
			if (parts.length < 2 || parts.length > 3) throw new IllegalArgumentException("must pass two or three arguments!");

			double temperature;
			try {
				temperature = Double.parseDouble(parts[0].trim());
			} catch (final NumberFormatException e) {
				temperature = Double.NaN;
			}

			final char sourceUnit = Character.toUpperCase(parts[1].charAt(0));
			//System.out.println("debug:55555 " + sourceUnit);
			if (temperature < 0 && sourceUnit == 'K') throw new IllegalArgumentException("nothing can be colder than absolute zero at 0°K!");
			if (temperature < -273.15 && sourceUnit == 'C') throw new IllegalArgumentException("nothing can be colder than absolute zero at -273.15°C!");
			if (temperature < -459.67 && sourceUnit == 'F') throw new IllegalArgumentException("nothing can be colder than absolute zero at -459.67°F!");

			if (parts.length == 3) {
				final char targetUnit = Character.toUpperCase(parts[2].charAt(0));
				final TemperatureConverter2 converter = new TemperatureConverter2(sourceUnit, targetUnit);
				final double result = converter.convert(temperature);

				System.out.println(temperature + "°" + sourceUnit + " = " + result + "°" + targetUnit);
			} else {
				for (final char targetUnit : TemperatureConverter2.UNITS) {	// for-each
					if (targetUnit == sourceUnit) continue;

					final TemperatureConverter2 converter = new TemperatureConverter2(sourceUnit, targetUnit);
					final double result = converter.convert(temperature);

					System.out.println(temperature + "°" + sourceUnit + " = " + result + "°" + targetUnit);
				}
			}
		} catch (final Exception e) {
			System.err.println(e.getMessage() == null ? e.getClass().getSimpleName() : e.getMessage());
		}
	}
}