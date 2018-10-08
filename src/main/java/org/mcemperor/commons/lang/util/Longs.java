package org.mcemperor.commons.lang.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Longs class contains a set of static methods which make calculating with and getting properties from longs
 * convenient.
 *
 * @author Maurits de Jong
 * @since 2018-06-16
 */
public class Longs {
	
	/**
	 * Private constructor, for this is a utility class.
	 */
	private Longs() { }
	
	/**
	 * Converts a long[] to a List&lt;Long&gt;.
	 * 
	 * @param longs The array to convert.
	 * @return A List&lt;Long&gt; with the longs.
	 */
	public static List<Long> boxed(long[] longs) {
		return Arrays.stream(longs).boxed().collect(Collectors.toList());
	}
	
	/**
	 * Converts a List&lt;Long&gt; to a long[].
	 * 
	 * @param longs A list with Long objects.
	 * @return A long[] with the longs.
	 */
	public static long[] unboxed(List<Long> longs) {
		return longs.stream()
			.mapToLong(i -> i)
			.toArray();
	}
	
}
