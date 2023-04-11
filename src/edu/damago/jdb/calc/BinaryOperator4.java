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


	/**
	 * Calculates the result of applying this operator to the given operands, and returns it.
	 * @param leftOperand the left operand
	 * @param rightOperand the right operand
	 * @return the calculation result
	 */
	double calculate (final double leftOperand, final double rightOperand);


	/**
	 * Initializes the instance POOL.
	 */
	static public void initializePool () {
		POOL.clear();
		POOL.put("+", new SumOperator());
		POOL.put("-", new DifferenceOperator());
		POOL.put("*", new ProductOperator());
		POOL.put("/", new QuotientOperator());
		POOL.put("%", new ModuloOperator());
		POOL.put("**", new ExponentOperator());
		POOL.put("//", new RootOperator());
	}


	static public class SumOperator implements BinaryOperator4 {
		public double calculate (final double leftOperand, final double rightOperand) {
			return leftOperand + rightOperand;
		}
	}



	static public class DifferenceOperator implements BinaryOperator4 {
		public double calculate (final double leftOperand, final double rightOperand) {
			return leftOperand - rightOperand;
		}
	}



	static public class ProductOperator implements BinaryOperator4 {
		public double calculate (final double leftOperand, final double rightOperand) {
			return leftOperand * rightOperand;
		}
	}



	static public class QuotientOperator implements BinaryOperator4 {
		public double calculate (final double leftOperand, final double rightOperand) {
			return leftOperand / rightOperand;
		}
	}



	static public class ModuloOperator implements BinaryOperator4 {
		public double calculate (final double leftOperand, final double rightOperand) {
			return leftOperand % rightOperand;
		}
	}



	static public class ExponentOperator implements BinaryOperator4 {
		public double calculate (final double leftOperand, final double rightOperand) {
			return Math.pow(leftOperand, rightOperand);
		}
	}



	static class RootOperator implements BinaryOperator4 {
		public double calculate (final double leftOperand, final double rightOperand) {
			return ExtendedMath.root(leftOperand, rightOperand);
		}
	}
}