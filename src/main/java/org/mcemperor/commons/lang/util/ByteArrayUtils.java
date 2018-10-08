package org.mcemperor.commons.lang.util;

import java.util.Arrays;

/**
 * Byte array utilities.
 *
 * @author Maurits de Jong
 * @since 2012-07-01
 */
public class ByteArrayUtils {
	
	/**
	 * Private constructor, for this is a utility class.
	 */
	private ByteArrayUtils() { }

	/**
	 * Returns an array with {@code count} elemens, each element having the specified value.
	 * 
	 * @param count The length of the array.
	 * @param value The value of each array element.
	 * @return A new byte array.
	 */
	public static byte[] repeat(int count, byte value) {
		byte[] array = new byte[count];
		for (int i = 0; i < count; i++) {
			Arrays.fill(array, value);
		}
		return array;
	}
	
	/**
	 * Converts the given byte array into a hexadecimal represention.
	 * 
	 * @param bytes The bytes to display as a hex-string.
	 * @return A hexadecimal string representing the byte array.
	 */
	public static String toHexString(byte[] bytes) {
		String result = "";
		for (int i = 0; i < bytes.length; i++) {
			result += Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}

	/**
	 * Returns a binary string representation of the given byte.
	 * 
	 * @param b The byte to display.
	 * @return The byte as a binary string.
	 */
	public static String toByteString(byte b) {
		int r = b; if (r < 0) { r += 256; }
		StringBuilder str = new StringBuilder();
		int div = 1;
		for (int i = 0; i < 8; i++) {
			str.append((r & div) > 0 ? '1' : '0');
			div <<= 1;
		}
		return Strings.reverse(str.toString());
	}
}
