package edu.damago.jdb.calc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Controller for the template application.
 */
public class Statictics1Controller {

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


	public void performSumCommand (final String parameterization) throws NullPointerException {
		final String[] arguments = parameterization.split("\\s+");

		final List<Double> values = new ArrayList<>();
		for (final String argument : arguments) {
			if (!argument.isEmpty()) values.add(Double.parseDouble(argument));
		}

		double sum = sumOf(values);
		System.out.println("The sum of: " + values + " is: " + sum);
	}


	public void performAverageCommand (final String parameterization) throws NullPointerException {
		final String[] arguments = parameterization.split("\\s+");

		final List<Double> values = new ArrayList<>();
		for (final String argument : arguments) {
			if (!argument.isEmpty()) values.add(Double.parseDouble(argument));
		}

		double average = sumOf(values) / values.size();
		System.out.println("The average of: " + values + " is: " + average);
	}


	public void performProductCommand (final String parameterization) throws NullPointerException {
		final String[] arguments = parameterization.split("\\s+");

		final List<Double> values = new ArrayList<>();
		for (final String argument : arguments) {
			if (!argument.isEmpty()) values.add(Double.parseDouble(argument));
		}

		double product = 1.0;
		for (final double value : values) {
			product *= value;
		}

		System.out.println("The product of: " + values + " is: " + product);
	}


	double sumOf (Collection<Double> values) {
		double sum = 0.0;
		for (double value : values) {
			sum += value;
		}
		return sum;

	}

}