package edu.damago.jdb.aleTemp;

public class ConvertFahrenheitCelsius {

	private final String symbol;
	private int switcher;


	//konstruktor
	public ConvertFahrenheitCelsius (final String symbol) {
		this.symbol = symbol;
	}


	public String getSymbol () {
		return symbol;
	}


	public double tempConversion (double temp) {
		
		switch (getSymbol()){
			case "c":
				// Celsius to Fahrenheit
				this.switcher = 0;
				return (temp * 1.8 + 32);
				//Fahrenheit to Celsius
			case "f": 
				this.switcher = 1;
				return ((temp - 32) * 0.5556);
			default:
				throw new IllegalArgumentException("Unexpected value: " + temp + getSymbol());
		}
	}
	
	public String symbolPrint(){
		switch (getSymbol()){
			case "c":
				return ("F");
			case "f": 
				return ("F");
			default:
				throw new IllegalArgumentException("Unexpected exception");
		}
	}


}
