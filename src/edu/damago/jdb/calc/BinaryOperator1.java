package edu.damago.jdb.calc;


public class BinaryOperator1 {
	
	private final String symbol;
	
	/**
	 * Constructor for BinaryOperator1
	 * @param symbol the symbol
	 * @throws NullPointerException if the given symbol is null
	 */
	public BinaryOperator1 (final String symbol) {
		if (symbol == null) throw new NullPointerException("cannot be null");
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
	 * Calculates the result of applying this operator to the given operands,
	 * and returns it.
	 * @param leftOperand the left operand
	 * @param rightOperand the right operand
	 * @return the calculation result
	 */
	public double calculate(double leftOperand, double rightOperand) {
		//System.out.println("symb:"+getSymbol());
		switch (this.symbol) {
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
			default:
				throw new IllegalArgumentException("Unexpected value: " + leftOperand + " " + symbol + " " + rightOperand);
		}
	}
}
