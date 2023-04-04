package edu.damago.jdb.calc;

public class TemperatureConverter1 {
	private final String sourceUnit;
	//private final String targetUnit;
	
	
	public TemperatureConverter1(final String sourceUnit){
		this.sourceUnit = sourceUnit;	}
	
	
	public String getSourceUnit () {
		return this.sourceUnit;
	}
	
	
	public void convert(double temperature){
		double resultF, resultC, resultK;
		switch (this.sourceUnit) {
			case "c": {
				resultF = (temperature * 1.8 + 32);
				resultK = (2);
				System.out.println(temperature + " C" + " = " + resultF + " F" + " / " + resultK + " K");
				break;
			}
			case "f": {
				resultC = ((temperature - 32) * 0.5556);
				resultK = (2);
				System.out.println(temperature + " F" + " = " + resultC + " C" + " / " + resultK + " K");
				break;
			}
			case "k": {
				resultC = ((temperature - 32) * 0.5556);
				resultF = (temperature * 1.8 + 32);
				System.out.println(temperature + " K " + " = " + resultC + " C" + " / " + resultF + " F");
				break;
			}
			default:
				System.out.println("kapput");
				throw new IllegalArgumentException("Unexpected value" + this.sourceUnit);
		}
		
	}
}
