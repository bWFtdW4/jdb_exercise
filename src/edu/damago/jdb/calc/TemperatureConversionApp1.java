package edu.damago.jdb.calc;

public class TemperatureConversionApp1 {

	public static void main (String[] args) {

		if (args.length != 2) throw new IllegalArgumentException("input must be [temperatur] and [from unit]");

		double temperatur = Double.parseDouble(args[0].trim());
		final String sourceUnit = args[1];
		
		TemperatureConverter1 converter = new TemperatureConverter1(sourceUnit);
		converter.convert(temperatur);
	}

}
