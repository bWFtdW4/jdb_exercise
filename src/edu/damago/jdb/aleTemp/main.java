package edu.damago.jdb.aleTemp;

public class main {

	public static void main (String[] args) {

		String[] testStrings = {"34.0 c"};
		
		
		final String[] parts = testStrings[0].split(" ");
		final double temp = Double.parseDouble(parts[0]);
		//System.out.println("eingabewert "+temp);
		final String symbol = parts[1];
		//System.out.println("symbol "+symbol);
		
		ConvertFahrenheitCelsius convert = new ConvertFahrenheitCelsius(symbol);
		
		System.out.print(convert.tempConversion(temp) + convert.symbolPrint() );
	}

}
