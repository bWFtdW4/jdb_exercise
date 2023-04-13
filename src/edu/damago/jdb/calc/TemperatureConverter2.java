package edu.damago.jdb.calc;

/**
 * Instances of this class converts temperature to 'K', 'C', 'F'
 */

public class TemperatureConverter2 {
	static public final char[] UNITS = { 'K', 'C', 'F' };

	private final char sourceUnit;
	private final char targetUnit;


	/**
	 * Initializes a new instance.
	 * @param symbol the symbol
	 * @throws NullPointerException if the given symbol is null
	 */
	public TemperatureConverter2 (final char sourceUnit, final char targetUnit) {
		this.sourceUnit = sourceUnit;
		this.targetUnit = targetUnit;
	}


	/**
	 * Returns the source unit.
	 * @return the source unit
	 */
	public char getSourceUnit () {
		return this.sourceUnit;
	}


	/**
	 * Returns the target unit.
	 * @return the target unit
	 */
	public char getTargetUnit () {
		return this.targetUnit;
	}


	/**
	 * Converts the given temperature value.
	 * @param temperature the temperature in source units
	 * @return the temperature in target units
	 * @throws IllegalStateException if the source or target unit is illegal
	 */
	public double convert (final double temperature) throws IllegalStateException {
		switch (this.sourceUnit + "" + this.targetUnit) {
			default:
				throw new IllegalStateException("Illegal source and/or target unit!");
			case "KF":
				return temperature * 1.8 - 459.67;
			case "KC":
				return temperature - 273.15;
			case "CF":
				return temperature * 1.8 + 32;
			case "FC":
				return (temperature - 32) / 1.8;
			case "CK":
				return temperature + 273.15;
			case "FK":
				return (temperature + 459.67) / 1.8;
		}
	}
}