package nl.mcemperor.commons.lang.util;

import java.util.ArrayList;
import java.util.List;

/**
 * The Characters class contains a set of static methods which make calculating with and getting properties from chars
 * convenient.
 *
 * @author Maurits de Jong
 * @since 2018-06-16
 */
public class Characters {
	
	/**
	 * Private constructor, for this is a utility class.
	 */
	private Characters() { }
	
	/**
	 * Converts a char[] to a List&lt;Character&gt;.
	 * 
	 * @param chars The array to convert.
	 * @return A List&lt;Character&gt; with the chars.
	 */
	public static List<Character> boxed(char[] chars) {
		List<Character> charList = new ArrayList<>();
		for (int i = 0; i < chars.length; i++) {
			charList.add(chars[i]);
		}
		return charList;
	}
	
	/**
	 * Converts a List&lt;Character&gt; to a char[].
	 * 
	 * @param chars A list with Character objects.
	 * @return A char[] with the chars.
	 */
	public static char[] unboxed(List<Character> chars) {
		char[] charArray = new char[chars.size()];
		for (int i = 0; i < chars.size(); i++) {
			charArray[i] = chars.get(i);
		}
		return charArray;
	}
	
}
