package edu.damago.jdb.calc;


/**
 * Instances of this class represent arithmetic binary operators.
 */
public class BinaryOperator2 {
	private final String symbol;


	/**
	 * Initializes a new instance.
	 * @param symbol the symbol
	 * @throws NullPointerException if the given symbol is null
	 */
	public BinaryOperator2 (final String symbol) throws NullPointerException {
		if (symbol == null) throw new NullPointerException();
		this.symbol = symbol;
	}


	/**
	 * Returns the symbol.
	 * @return the symbol
	 */
	public String getSymbol () {
		return this.symbol;
	}


	/**
	 * Calculates the result of applying this operator to the given operands, and returns it.
	 * @param leftOperand the left operand
	 * @param rightOperand the right operand
	 * @return the calculation result
	 */
	public double calculate (final double leftOperand, final double rightOperand) {
		switch (this.symbol) {
			default:
				throw new IllegalStateException("unknown operator symbol!");
			case "+":
				return leftOperand + rightOperand;
			case "-":
				return leftOperand - rightOperand;
			case "*":
				return leftOperand * rightOperand;
			case "/":
				return leftOperand / rightOperand;
			case "%":
				return leftOperand % rightOperand;
			case "**":
				return Math.pow(leftOperand, rightOperand);
		}
	}
}