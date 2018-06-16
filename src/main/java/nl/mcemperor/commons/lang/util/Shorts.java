package nl.mcemperor.commons.lang.util;

import java.util.ArrayList;
import java.util.List;

/**
 * The Shorts class contains a set of static methods which make calculating with and getting properties from shorts
 * convenient.
 *
 * @author Maurits de Jong
 * @since 2018-06-16
 */
public class Shorts {
	
	/**
	 * Private constructor, for this is a utility class.
	 */
	private Shorts() { }
	
	/**
	 * Converts a short[] to a List&lt;Short&gt;.
	 * 
	 * @param shorts The array to convert.
	 * @return A List&lt;Short&gt; with the shorts.
	 */
	public static List<Short> boxed(short[] shorts) {
		List<Short> shortList = new ArrayList<>();
		for (int i = 0; i < shorts.length; i++) {
			shortList.add(shorts[i]);
		}
		return shortList;
	}
	
	/**
	 * Converts a List&lt;Short&gt; to a short[].
	 * 
	 * @param shorts A list with Short objects.
	 * @return A short[] with the shorts.
	 */
	public static short[] unboxed(List<Short> shorts) {
		short[] shortArray = new short[shorts.size()];
		for (int i = 0; i < shorts.size(); i++) {
			shortArray[i] = shorts.get(i);
		}
		return shortArray;
	}
	
}
