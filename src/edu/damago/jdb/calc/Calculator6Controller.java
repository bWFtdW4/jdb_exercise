package edu.damago.jdb.calc;

import java.util.function.DoubleBinaryOperator;
import edu.damago.jdb.tool.BinaryOperators;
import edu.damago.jdb.tool.ConsoleEventSource;


/**
 * Controller for the calculator application, version 6.
 */
public class Calculator6Controller {

	static {

	}

	private final ConsoleEventSource eventSource;


	/**
	 * Initializes a new instance.
	 */
	public Calculator6Controller () {
		this.eventSource = new ConsoleEventSource();

		// register event listeners
		this.eventSource.addEventListener("exit", parameterization -> this.performQuitCommand(parameterization));
		this.eventSource.addEventListener("quit", parameterization -> this.performQuitCommand(parameterization));
		this.eventSource.addEventListener("help", parameterization -> this.performHelpCommand(parameterization));
		this.eventSource.addEventListener("calc", parameterization -> this.performCalcCommand(parameterization));
	}


	/**
	 * Returns the root view component
	 * @return the view component
	 */
	public ConsoleEventSource eventSource () {
		return this.eventSource;
	}


	/**
	 * Performs the welcome command.
	 */
	public void performWelcome () {
		System.out.println("Welcome to the calculator v6 !");
		System.out.println("Use [help] for operators and information.");
	}


	/**
	 * Performs the quit command.
	 * @param parameterization the command parameterization, empty for none
	 * @throws NullPointerException if the given parameterization is null
	 */
	public void performQuitCommand (final String parameterization) throws NullPointerException {
		System.out.println("Bye Bye!");
		System.exit(0);
	}


	/**
	 * Performs the help command.
	 * @param parameterization the command parameterization, empty for none
	 * @throws NullPointerException if the given parameterization is null
	 */
	public void performHelpCommand (final String parameterization) throws NullPointerException {
		System.out.println("Available commands:");
		System.out.println("- calc: use [calc] <left-value> <operator-symbol> <right-value> to start calculation.");
		System.out.println("- quit: terminates this program");
		System.out.println("- help: displays this help");
		System.out.println("Available operators:");
		System.out.println("addition: +	| subbtraction: -	| multiplication: *	| division: /");
		System.out.println("modulo: % 	| power of: **	  	| root: //		| ");
	}


	/**
	 * Performs the calc command.
	 * @param parameterization the command parameterization, empty for none
	 * @throws NullPointerException if the given parameterization is null
	 */
	public void performCalcCommand (final String parameterization) throws NullPointerException {
		final String[] arguments = parameterization.split("\\s+");
		if (arguments.length != 3) throw new IllegalArgumentException("must pass exactly 3 arguments: <left-operand> <operator-symbol> <right-operand>!");

		// input - eingabe
		final String operatorSymbol = arguments[1];

		double leftOperand, rightOperand;
		try {
			leftOperand = Double.parseDouble(arguments[0]);
		} catch (final NumberFormatException e) {
			leftOperand = Double.NaN;
		}
		try {
			rightOperand = Double.parseDouble(arguments[2]);
		} catch (final NumberFormatException e) {
			rightOperand = Double.NaN;
		}

		// processing - verarbeitung
		final DoubleBinaryOperator operator = BinaryOperators.POOL.get(operatorSymbol);
		final double result = operator.applyAsDouble(leftOperand, rightOperand);

		// output - ausgabe
		System.out.println(leftOperand + " " + operatorSymbol + " " + rightOperand + " = " + result);
	}
}