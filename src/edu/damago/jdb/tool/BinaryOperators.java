package edu.damago.jdb.tool;

import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleBinaryOperator;


/**
 * Instances of this class represent arithmetic binary operators.
 */
public class BinaryOperators {
	static public final Map<String,DoubleBinaryOperator> POOL = new HashMap<>();

	static {
		POOL.put("+", (l, r) -> l + r);
		POOL.put("-", (l, r) -> l - r);
		POOL.put("*", (l, r) -> l * r);
		POOL.put("/", (l, r) -> l / r);
		POOL.put("%", (l, r) -> l % r);
		POOL.put("**", (l, r) -> Math.pow(l, r));
		POOL.put("//", (l, r) -> ExtendedMath.root(l, r));
	}
}