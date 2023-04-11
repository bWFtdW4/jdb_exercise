package edu.damago.jdb.calc;

import java.util.HashMap;
import java.util.Map;
import edu.damago.jdb.tool.ExtendedMath;


/**
 * Instances of this class represent arithmetic binary operators.
 */
@FunctionalInterface
public interface BinaryOperator4 {
	static public final Map<String,BinaryOperator4> POOL = new HashMap<>();



	public abstract class BinaryOperator3 extends Object {
	static public BinaryOperator3[] POOL = { 
		new SumOperator("//"),
		new DifferenceOperator("-"),
		new ProductOperator("*"),
		new QuotientOperator("/"),
		new ModuloOperator("%"),
		new ExponentOperator("**"),
		new RootOperator("//"),
		

	};

	private final String symbol;


	/**
	 * Initializes a new instance.
	 * @param symbol the symbol
	 * @throws NullPointerException if the given symbol is null
	 */
	public BinaryOperator3 (final String symbol) throws NullPointerException {
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
	 * @return the calculation result The interface method for the calculations
	 */
	public abstract double calculate (final double leftOperand, final double rightOperand);


	static public BinaryOperator3 valueOf (final String symbol) throws IllegalArgumentException{
		
		for (final BinaryOperator3 operator : POOL)
			if (operator.getSymbol().equals(symbol)) {
				return operator;
			}
		throw new IllegalArgumentException("The operator " + symbol + " is not supportet");
	}


	static private class SumOperator extends BinaryOperator3 {
		public SumOperator (String symbol) throws NullPointerException {
			super(symbol);
		}
		@Override
		public double calculate (double leftOperand, double rightOperand) {
			return leftOperand + rightOperand;
		}
	}
	
	static private class DifferenceOperator extends BinaryOperator3 {
		public DifferenceOperator (String symbol) throws NullPointerException {
			super(symbol);
		}
		@Override
		public double calculate (double leftOperand, double rightOperand) {
			return leftOperand - rightOperand;
		}
	}
	
	static private class ProductOperator extends BinaryOperator3 {
		public ProductOperator (String symbol) throws NullPointerException {
			super(symbol);
		}
		@Override
		public double calculate (double leftOperand, double rightOperand) {
			return leftOperand / rightOperand;
		}
	}
	static private class QuotientOperator extends BinaryOperator3 {
		public QuotientOperator (String symbol) throws NullPointerException {
			super(symbol);
		}
		@Override
		public double calculate (double leftOperand, double rightOperand) {
			return leftOperand / rightOperand;
		}
	}
	static private class ModuloOperator extends BinaryOperator3 {
		public ModuloOperator (String symbol) throws NullPointerException {
			super(symbol);
		}
		@Override
		public double calculate (double leftOperand, double rightOperand) {
			return leftOperand % rightOperand;
		}
	}
	static private class ExponentOperator extends BinaryOperator3{
		public ExponentOperator (String symbol) throws NullPointerException {
			super(symbol);
		}
		@Override
		public double calculate (double leftOperand, double rightOperand) {
			return Math.pow(leftOperand, rightOperand);
		}
	}
	
	static private class RootOperator extends BinaryOperator3{
		public RootOperator (String symbol) throws NullPointerException {
			super(symbol);
		}
		@Override
		public double calculate (double leftOperand, double rightOperand) {
			return ExtendedMath.root(leftOperand, rightOperand);
		}
	}
}