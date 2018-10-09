package org.mcemperor.commons.lang.util;

/**
 *
 * @author Maurits de Jong
 */
public class Bytes {

	/**
	 * Private constructor, because this class is a utility class.
	 */
	private Bytes() { }

	/**
	 * Converts the given byte array to a short array. Each pair of two bytes is taken and converted to a short, so the
	 * bits of the short are, in order, equal to the first byte concatenated with the second byte. The resulting short
	 * array is at least half the size of the provided byte array.
	 *
	 * @param bytes The byte array to convert.
	 * @return A short array consisting of the bytes of the provided byte array.
	 */
	public static short[] toShortArray(byte[] bytes) {
		int iterateTo = bytes.length / 2;
		short[] shorts = new short[bytes.length / 2 + (bytes.length % 2)];
		for (int i = 0; i < iterateTo; i++) {
			shorts[i] = (short) ((bytes[2 * i] << 8) + (bytes[2 * i + 1] & 0xff));
		}

		if (iterateTo != shorts.length) {
			shorts[shorts.length - 1] = (short) (bytes[bytes.length - 1] << 8);
		}

		return shorts;
	}
}
