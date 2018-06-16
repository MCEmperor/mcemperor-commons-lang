package nl.mcemperor.commons.lang.util;

/**
 * The IntegerUtils class contains a set of static methods which make calculating with and getting properties from
 * integers convenient.
 * @author Maurits de Jong
 * @version 1.2
 * @since 2015-03-16
 */
public class IntegerUtils {
	
	private IntegerUtils() { }
	
	/**
	 * Converts an int array to a byte array, typecasting each int to a byte.
	 * @param array The int array to convert.
	 * @return The resulting byte array.
	 */
	public static final byte[] toByteArray(int[] array) {
		byte[] newArray = new byte[array.length];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = (byte) array[i];
		}
		return newArray;
	}
}