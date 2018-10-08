package org.mcemperor.commons.lang.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Integers class contains a set of static methods which make calculating with and getting properties from
 * integers convenient.
 * 
 * @author Maurits de Jong
 * @since 2015-03-16
 */
public class Integers {
	
	/**
	 * Private constructor, for this is a utility class.
	 */
	private Integers() { }
	
	/**
	 * Converts an int[] to a List&lt;Integer&gt;.
	 * 
	 * @param ints The array to convert.
	 * @return A List&lt;Integer&gt; with the integers.
	 */
	public static List<Integer> boxed(int[] ints) {
		return Arrays.stream(ints).boxed().collect(Collectors.toList());
	}
	
	/**
	 * Converts a List&lt;Integer&gt; to an int[].
	 * 
	 * @param ints A list with Integer objects.
	 * @return An int[] with the integers.
	 */
	public static int[] unboxed(List<Integer> ints) {
		return ints.stream()
			.mapToInt(i -> i)
			.toArray();
	}
	
	/**
	 * Gets the width of the given long. The width is the number of digits of the given number. The additive inversion
	 * sign (minus sign), if applicable, increases the width by 1.
	 * 
	 * @param value An long.
	 * @return The width of the given long.
	 */
	public static int getWidth(long value) {
		int i = (value < 0 ? 1 : 0);
		while (value != 0) {
			value /= 10;
			i++;
		}
		return i;
	}
	
	/**
	 * Gets all digits of a number as an {@code List&lt;Integer&gt;}.
	 * 
	 * @param number The number to get the digits from.
	 * @return An array with the digits.
	 */
	public static List<Integer> getDigits(int number) {
		int width = Integers.getWidth(number);
		List<Integer> digits = new ArrayList<>();
		for (int i = 0; i < width; i++) {
			int div = (int) Math.pow(10, width - (i + 1));
			int digit = number / div;
			digits.add(digit);
			number -= (digit * div);
		}
		return digits;
	}
	
	/**
	 * Try to parse the given str as an integer. If it fails, then return the default value.
	 * 
	 * @param str The string to parse.
	 * @param defaultValue The default value.
	 * @return The new value of the integer.
	 */
	public static int getOrDefault(String str, int defaultValue) {
		try {
			return Integer.valueOf(str);
		}
		catch (NumberFormatException exc) {
			return defaultValue;
		}
	}
	
	/**
	 * Converts an int array to a byte array, typecasting each int to a byte.
	 * 
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
