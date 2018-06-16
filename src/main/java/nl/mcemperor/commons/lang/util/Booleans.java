package nl.mcemperor.commons.lang.util;

/**
 * The Booleans class contains a set of static methods which make calculating with and getting properties from booleans
 * convenient.
 *
 * @author mauri
 * @version 0.0.0
 * @since 2018-06-16
 */
public class Booleans {
	
	/**
	 * Private constructor, for this is a utility class.
	 */
	private Booleans() { }
	
	/**
	 * Returns {@code 1} if the given boolean is {@code true}, {@code 0} otherwise.
	 * 
	 * @param b The boolean to convert.
	 * @return The boolean as an int.
	 */
	public static int toInt(boolean b) {
		return b ? 1 : 0;
	}
}
