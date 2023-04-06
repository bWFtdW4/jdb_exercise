package edu.damago.jdb.calc;

/**
 * @author Mamun
 */
public class Calculator1App {
	/**
	 * Application entry point.
	 * @param args the runtime arguments
	 */
	static public void main (String[] args) {
		//System.out.println(Arrays.toString(args));

		final double leftOperand = Double.parseDouble(args[0]);
		final String symbol = args[1].replace("x", "*");
		final double rightOperand = Double.parseDouble(args[2]);

		final BinaryOperator1 claculation = new BinaryOperator1(symbol);
		System.out.println(leftOperand + " " + symbol + " " + rightOperand + " = " +claculation.calculate(leftOperand, rightOperand));

	}
}
