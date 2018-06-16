package nl.mcemperor.commons.lang.util;

/**
 * CharArrays class, containing methods to ease char array operations.
 *
 * @author Maurits de Jong
 * @version 1.0.0
 * @since 2014-02-09
 */
public class CharArrayUtils {
	
	/**
	 * Private constructor, for this is a utility class.
	 */
	private CharArrayUtils() { }
	
	/**
	 * Checks whether the two character arrays are equal.
	 * @param array1 The first array.
	 * @param array2 The second array.
	 * @return Whether these arrays are equal.
	 */
	public static boolean equals(char[] array1, char[] array2) {
		if (array1.length != array2.length) {
			return false;
		}
		for (int i = 0; i < array1.length; i++) {
			if (array1[i] != array2[i]) {
				return false;
			}
		}
		return true;
	}
}
