package edu.damago.jdb.calc;

import java.util.HashMap;
import java.util.Map;


/**
 * Instances of this class represents the temperature variant.
 */
@FunctionalInterface
public interface TemperatureConverter4 {
	static public final Map<String,TemperatureConverter4> POOL = new HashMap<>();


	/**
	 * Calculates the temperature and returns it.
	 * @param temperature the temperature in source units
	 * @return the temperature in target units
	 */
	double convert (final double temperature);


	/**
	 * Initializes the instance POOL.
	 */
	static public void initializePool () {
		POOL.clear();
		POOL.put("KF", new convertK2F());
		POOL.put("KC", new convertK2C());
		POOL.put("CF", new convertC2F());
		POOL.put("FC", new convertF2C());
		POOL.put("CK", new convertC2K());
		POOL.put("FK", new convertF2K());

	}


	static public class convertK2F implements TemperatureConverter4 {
		public double convert (final double temperature) {
			return temperature * 1.8 - 459.67;
		}
	}



	static public class convertK2C implements TemperatureConverter4 {
		public double convert (final double temperature) {
			return temperature - 273.15;
		}
	}



	static public class convertC2F implements TemperatureConverter4 {
		public double convert (final double temperature) {
			return temperature * 1.8 + 32;
		}
	}



	static public class convertF2C implements TemperatureConverter4 {
		public double convert (final double temperature) {
			return (temperature - 32) / 1.8;
		}
	}



	static public class convertC2K implements TemperatureConverter4 {
		public double convert (final double temperature) {
			return temperature + 273.15;
		}
	}



	static public class convertF2K implements TemperatureConverter4 {
		public double convert (final double temperature) {
			return (temperature + 459.67) / 1.8;
		}
	}

}