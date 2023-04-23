package edu.damago.jdb.calc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import edu.damago.jdb.tool.ConsoleEventSource;


/**
 * Controller for the statistics application, version 2.
 */
public class Statistics2Controller {

	private final ConsoleEventSource eventSource;


	/**
	 * Initializes a new instance.
	 */
	public Statistics2Controller () {
		this.eventSource = new ConsoleEventSource();

		// register event listeners
		this.eventSource.addEventListener("exit", parameterization -> this.performQuitCommand(parameterization));
		this.eventSource.addEventListener("quit", parameterization -> this.performQuitCommand(parameterization));
		this.eventSource.addEventListener("help", parameterization -> this.performHelpCommand(parameterization));
		this.eventSource.addEventListener("sum", parameterization -> this.performSumCommand(parameterization));
		this.eventSource.addEventListener("average", parameterization -> this.performAverageCommand(parameterization));
		this.eventSource.addEventListener("multiply", parameterization -> this.performMultiplyCommand(parameterization));
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
		System.out.println("Welcome to the Statistic calculator v1 !");
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
		System.out.println("- [sum]: gives back sum");
		System.out.println("- [average]: gives back average");
		System.out.println("- [product]: gives back product");
		System.out.println("- [quit]: terminates this program");
		System.out.println("- [help]: displays this help");
	}


	/**
	 * performs the sum command uses sumOf() method
	 * @param parameterization the command parameterization, empty for none
	 * @throws NullPointerException if the given parameterization is null
	 */
	public void performSumCommand (final String parameterization) throws NullPointerException {
		final String[] arguments = parameterization.split("\\s+");

		final List<Double> values = new ArrayList<>();
		for (final String argument : arguments) {
			try {
				if (!argument.isEmpty()) values.add(Double.parseDouble(argument));
			} catch (final NumberFormatException e) {
				values.add(Double.NaN);
			}
		}

		final double sum = sumOf(values);
		System.out.println("The sum of: " + values + " is: " + sum);
	}


	/**
	 * performs the average command uses sumOf() method
	 * @param parameterization the command parameterization, empty for none
	 * @throws NullPointerException if the given parameterization is null
	 */
	public void performAverageCommand (final String parameterization) throws NullPointerException {
		final String[] arguments = parameterization.split("\\s+");

		final List<Double> values = new ArrayList<>();
		for (final String argument : arguments) {
			try {
				if (!argument.isEmpty()) values.add(Double.parseDouble(argument));
			} catch (final NumberFormatException e) {
				values.add(Double.NaN);
			}
		}

		final double average = sumOf(values) / values.size();
		System.out.println("The average of: " + values + " is: " + average);
	}


	/**
	 * performs the product command
	 * @param parameterization the command parameterization, empty for none
	 * @throws NullPointerException if the given parameterization is null
	 */
	public void performMultiplyCommand (final String parameterization) throws NullPointerException {
		final String[] arguments = parameterization.split("\\s+");

		final List<Double> values = new ArrayList<>();
		for (final String argument : arguments) {
			try {
				if (!argument.isEmpty()) values.add(Double.parseDouble(argument));
			} catch (final NumberFormatException e) {
				values.add(Double.NaN);
			}
		}

		double product = 1.0;
		for (final double value : values) {
			product *= value;
		}

		System.out.println("The product of: " + values + " is: " + product);
	}


	/**
	 * sums the given values
	 * @param values that are given to the method
	 * @return the result of sum of values
	 */
	double sumOf (Collection<Double> values) throws NullPointerException {
		double sum = 0.0;
		for (double value : values) {
			sum += value;
		}
		return sum;

	}

}