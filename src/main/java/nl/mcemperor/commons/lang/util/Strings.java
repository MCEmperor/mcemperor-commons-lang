package nl.mcemperor.commons.lang.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import nl.mcemperor.commons.lang.MalformedInputException;

/**
 * The Strings class is a utility class containing various string processing methods.
 *
 * @author Maurits de Jong
 * @version 3.3.1.2
 * @since 2012-01-01
 */
public class Strings {

	public interface TextWrapper<T> {

		String toString(T element);

	}

	public static final int NORMALIZE_NEWLINES = 2;

	public static final int NORMALIZE_SPACE_TO_UNDERSCORE = 4;

	public static final int NORMALIZE_PATH = 8;

	private Strings() { }

	/**
	 * Counts the number of occurences of {@code needle} in {@code haystack}.
	 * @param haystack The haystack to search in.
	 * @param needle The needle to search for.
	 * @return The number of occurences of {@code needle} in {@code haystack}.
	 */
	public static int countSubstring(String haystack, String needle) {
		return countSubstring(haystack, needle, false);
	}

	/**
	 * Counts the number of occurences of {@code needle} in {@code haystack} and also includes doubles. Doubles occur if
	 * {@code needle} has a sequence with two equal characters.<br>
	 * For example, if the string "blaaaaahblah" is being searched for the occurence of "aa", then it will return 4.
	 * @param haystack The haystack to search in.
	 * @param needle The needle to search for.
	 * @return The number of occurences of {@code needle} in {@code haystack}.
	 */
	public static int countSubstringWithOverlap(String haystack, String needle) {
		return countSubstring(haystack, needle, true);
	}

	/**
	 * Counts the number of occurences of {@code needle} in {@code haystack}. The parameter {@code overlap} indicates
	 * whenever to search for doubles. Doubles occur if {@code needle} has a sequence with two equal characters. With
	 * {@code overlap} set to {@code false}, the whole occurence of {@code needle} is consumed.<br>
	 * For example, if the string "blaaaaahblah" is being searched for the occurence of {@code aa}, then with {@code
	 * overlap} set to {@code false} it will return 2, while if {@code overlap} set to {@code true} it will return 4.
	 * @param haystack The haystack to search in.
	 * @param needle The needle to search for.
	 * @param doubles Whenever to search for doubles.
	 * @return The number of occurences of {@code needle} in {@code haystack}.
	 */
	private static int countSubstring(String haystack, String needle, boolean overlap) {
		int lastIndex = 0;
		int count = 0;
		while (true) {
			lastIndex = haystack.indexOf(needle, lastIndex);
			if (lastIndex == -1) {
				break;
			}
			lastIndex += (overlap ? 1 : needle.length());
			count++;
		}
		return count;
	}

	/**
	 * Counts the number of subsequent characters equal to the character denoted in {@code c} at the start of the
	 * string.
	 * @param str The string to check.
	 * @param c The character to search for at the end of the string.
	 * @return The number of occurrences.
	 */
	public static int countCharsAtStart(String str, char c) {
		return countCharsAtBoundary(str, c, true);
	}

	/**
	 * Counts the number of subsequent characters equal to the character denoted in {@code c} at the end of the string.
	 * @param str The string to check.
	 * @param c The character to search for at the end of the string.
	 * @return The number of occurrences.
	 */
	public static int countCharsAtEnd(String str, char c) {
		return countCharsAtBoundary(str, c, false);
	}

	/**
	 * Counts the number of subsequent characters equal to the character denoted in {@code c} at the start or end of the
	 * string.
	 * @param str The string to check.
	 * @param c The character to search for at the end of the string.
	 * @param start Whether to search at the start (if {@code true}) or end (if {@code false}) of the string.
	 * @return The number of occurrences.
	 */
	private static int countCharsAtBoundary(String str, char c, boolean start) {
		String f = "(?s)";
		String pattern = f + (start ? "^(" + makeCompatibleToRegex(c) + "+).*?" : ".*?(" + makeCompatibleToRegex(c) + "+)$");
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		return (m.matches() ? m.group(1).length() : 0);
	}

	/**
	 * Checks whether the given String {@code str} contains the given character.
	 * @param str The string to search in.
	 * @param c The char to search for.
	 * @return Whether the given char is found.
	 */
	public static boolean containsChar(String str, char c) {
		for (int i = 0; i < str.length(); i++) {
			if (c == str.charAt(i)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Inserts a character into string.
	 * @param str The source string to insert a string to.
	 * @param position The position at which the string is being inserted.
	 * @param insert The character to insert.
	 * @return The new string.
	 */
	public static String insertCharAt(String str, int position, char insert) {
		return (str.substring(0, position) + insert + str.substring(position));
	}

	/**
	 * Inserts a string into another string.
	 * @param str The source string to insert a string to.
	 * @param position The position at which the string is being inserted.
	 * @param insert The string to insert.
	 * @return The new string.
	 */
	public static String insertCharsAt(String str, int position, String insert) {
		return (str.substring(0, position) + insert + str.substring(position, str.length()));
	}

	/**
	 * Removes a character from a string.
	 * @param str The string to remove a character from.
	 * @param offset The position to remove the character from.
	 * @return The remaining string from which a character is taken.
	 */
	public static String removeCharAt(String str, int offset) {
		return removeCharsAt(str, offset, 1);
	}

	/**
	 * Removes a sequence of characters from a string.
	 * @param str The string to remove characters from.
	 * @param position The position to start removing characters from.
	 * @param length The length of the string to remove.
	 * @return The remaining string from which one or more characters are taken.
	 */
	public static String removeCharsAt(String str, int position, int length) {
		return (str.substring(0, position) + str.substring(position + length));
	}

	/**
	 * Checks whether a string is numeric or not.
	 * @param string The input string.
	 * @return Whether this string is numeric.
	 */
	public static boolean isNumeric(String string) {
		return string.chars().allMatch(t -> Character.isDigit((char) t));
	}

	/**
	 * Concatenates all elements of an array with {@code glue} between them.
	 * @param <T> The type of CharSequences.
	 * @param array The array to use.
	 * @param glue The glue to insert among the elements.
	 * @return The CharSequence with the concatenated array elements.
	 */
	public static <T> String join(T[] array, String glue) {
		return join(Arrays.asList(array), glue);
	}

	/**
	 * Concatenates all elements of an array with {@code glue} between them.
	 * @param <T> The type of CharSequences.
	 * @param array The array to use.
	 * @param glue The glue to insert among the elements.
	 * @param textWrapper The text wrapper to use.
	 * @return A string with the concatenated array elements.
	 */
	public static <T> String join(T[] array, String glue, TextWrapper<T> textWrapper) {
		return join(Arrays.asList(array), glue, textWrapper);
	}

	/**
	 * Concatenates all elements of an array with {@code glue} between them.
	 * @param <T> The type of objects inside the array.
	 * @param array The array to use.
	 * @param glue The glue to insert among the elements.
	 * @return A string with the concatenated array elements.
	 */
	public static <T> String join(List<T> array, String glue) {
		return Strings.join(array, glue, "", "");
	}

	/**
	 * Concatenates all elements of an array with {@code glue} between them.
	 * @param <T> The type of objects inside the array.
	 * @param array The array to use.
	 * @param glue The glue to insert among the elements.
	 * @param textWrapper The text wrapper to use.
	 * @return A string with the concatenated array elements.
	 */
	public static <T> String join(List<T> array, String glue, TextWrapper<T> textWrapper) {
		return Strings.join(array, glue, "", "", textWrapper);
	}

	/**
	 * Concatenates all elements of an array with {@code glue} between them, first concatenating the {@code
	 * startDelimiter} and {@code endDelimiter} to the element.
	 * @param <T> The type of CharSequences.
	 * @param array The array to use.
	 * @param glue The glue to insert among the elements.
	 * @param startDelimiter The starting delimiter which is prepended to each element before it's concatenated to the
	 * string.
	 * @param endDelimiter The ending delimiter which is appended to each element before it's concatenated to the
	 * string.
	 * @return A string with the concatenated array elements.
	 */
	public static <T> String join(T[] array, String glue, String startDelimiter, String endDelimiter) {
		return join(Arrays.asList(array), glue, startDelimiter, endDelimiter);
	}

	/**
	 * Concatenates all elements of an array with {@code glue} between them, first concatenating the
	 * {@code startDelimiter} and {@code endDelimiter} to the element.
	 * @param <T> The type of CharSequences.
	 * @param array The array to use.
	 * @param glue The glue to insert among the elements.
	 * @param startDelimiter The starting delimiter which is prepended to each element before it's concatenated to the
	 * string.
	 * @param endDelimiter The ending delimiter which is appended to each element before it's concatenated to the
	 * string.
	 * @return A string with the concatenated array elements.
	 */
	public static <T> String join(List<T> array, String glue, String startDelimiter, String endDelimiter) {
		return join(array, glue, startDelimiter, endDelimiter, null);
	}

	/**
	 * Concatenates all elements of an array with {@code glue} between them, first concatenating the {@code
	 * startDelimiter} and {@code endDelimiter} to the element.
	 * @param <T> The type of CharSequences.
	 * @param array The array to use.
	 * @param glue The glue to insert among the elements.
	 * @param startDelimiter The starting delimiter which is prepended to each element before it's concatenated to the
	 * string.
	 * @param endDelimiter The ending delimiter which is appended to each element before it's concatenated to the
	 * string.
	 * @param textWrapper The text wrapper to use.
	 * @return A string with the concatenated array elements.
	 */
	public static <T> String join(List<T> array, String glue, String startDelimiter, String endDelimiter, TextWrapper<T> textWrapper) {
		switch (array.size()) {
			case 0:
				return (startDelimiter + endDelimiter);
			case 1:
				return (startDelimiter + (textWrapper != null ? textWrapper.toString(array.get(0)) : array.get(0).toString()) + endDelimiter);
			default:
				StringBuilder buffer = new StringBuilder();
				buffer.append(startDelimiter);
				buffer.append(textWrapper != null ? textWrapper.toString(array.get(0)) : array.get(0).toString());
				buffer.append(endDelimiter);
				for (int i = 1; i < array.size(); i++) {
					buffer.append(glue);
					buffer.append(startDelimiter);
					buffer.append(textWrapper != null ? textWrapper.toString(array.get(i)) : array.get(i).toString());
					buffer.append(endDelimiter);
				}
				return buffer.toString();
		}
	}

	public static String join(char... chars) {
		return new String(chars);
	}

	/**
	 * Gets pieces from a delimited string. This method acts almost in the same manner as {@code String.split(String)},
	 * but this method supports different starting and ending delimiters.
	 * @param haystack The haystack to search for.
	 * @param startDelimiter The starting delimiter of each part.
	 * @param endDelimiter The ending delimiter of each part.
	 * @param escapeChar The escape character to escape {@code startDelimiter} and {@code endDelimiter} used as literal
	 * character.
	 * @return A String[] of pieces.
	 * @throws MalformedInputException If the delimited string is malformed, i.e. delimiters are missing.
	 */
	public static List<String> splitCombined(String haystack, String startDelimiter, String endDelimiter, String escapeChar) throws MalformedInputException {
		List<String> strs = new ArrayList<>();
		if (startDelimiter.equals(endDelimiter)) {
			List<Integer> positions = getDelimitingPositions(haystack, startDelimiter, escapeChar);
			if (positions.size() % 2 == 1) {
				throw new MalformedInputException("Malformed data format");
			}
			for (int i = 0; i < positions.size(); i += 2) {
				strs.add(haystack.substring(positions.get(i) + 1, positions.get(i + 1)).replace(escapeChar, ""));
			}
		}
		else {
			List<Integer> sPositions = getDelimitingPositions(haystack, startDelimiter, escapeChar);
			List<Integer> ePositions = getDelimitingPositions(haystack, endDelimiter, escapeChar);
			if (sPositions.size() != ePositions.size()) {
				throw new MalformedInputException("Malformed data format");
			}
			int pointer = -1;
			for (int i = 0; i < sPositions.size(); i++) {
				int sPos = sPositions.get(i);
				int ePos = ePositions.get(i);
				if (sPos >= ePos || sPos < pointer) {
					throw new MalformedInputException("Malformed data format.");
				}
				strs.add(haystack.substring(sPos + startDelimiter.length(), ePos).replace(escapeChar, ""));
				pointer = ePos;
			}
		}
		return strs;
	}

	/**
	 * Gets all positions of the given {@code needle} inside {@code haystack}.
	 * @param haystack The haystack to search in.
	 * @param needle The needle to search for.
	 * @param escapeChar The escape character.
	 * @return An int array with all occuring positions.
	 */
	private static List<Integer> getDelimitingPositions(String haystack, String needle, String escapeChar) {
		Stack<Integer> stack = new Stack<>();
		int pointer = 0;
		while (true) {
			int pos = haystack.indexOf(needle, pointer);
			if (pos == -1) {
				break;
			}
			boolean add = ((pos == 0) ? true : !(haystack.substring(pos - escapeChar.length()).startsWith(escapeChar)));
			if (add) {
				stack.add(pos);
			}
			pointer = pos + 1;
		}
		int size = stack.size();
		List<Integer> positions = new ArrayList<>(size);
		for (int i = size - 1; i >= 0; i--) {
			positions.set(i, stack.pop());
		}
		return positions;
	}

	/**
	 * Splits the given {@code string} by the delimiters denoted in {@code delimiters}, leaving the delimiters as part
	 * of the preceding substring.
	 * @param string The string to split.
	 * @param delimiters The delimiters.
	 * @return The splitted string.
	 */
	public static List<String> splitInclusive(String string, List<String> delimiters) {
		return splitInclusive(string, delimiters, "\\");
	}

	/**
	 * Splits the given {@code string} by the delimiters denoted in {@code delimiters}, leaving the delimiters as part
	 * of the preceding substring.
	 * @param str The string to split.
	 * @param delimiters The delimiters.
	 * @param escapeSequence The sequence where the delimiters can be escaped.
	 * @return The splitted string.
	 */
	public static List<String> splitInclusive(String str, List<String> delimiters, String escapeSequence) {
		List<Integer> positions = new ArrayList<>();
		// Adds all delimiter positions to the array.
		positions.add(-1);
		delimiters.forEach(t -> positions.addAll(getDelimitingPositions(str, t, escapeSequence)));
		positions.add(str.length() - 1);

		List<Integer> array = positions.stream()
			.sorted()
			.collect(Collectors.toList());

		// Save all substrings into a list.
		List<String> substrings = new ArrayList<>();
		for (int i = 1; i < array.size(); i++) {
			substrings.add(str.substring(array.get(i - 1) + 1, array.get(i) + 1));
		}
		return substrings;
	}

	/**
	 * Splits a string {@code str} by the given separator, preserving the separator as match in the returning array.
	 * @author Cletus
	 * @param str The string to split.
	 * @param separator The separator to use.
	 * @return The splitted parts as a string array.
	 */
	public static List<String> chop(String str, String separator) {
		return chop(str, Pattern.compile(separator));
	}

	/**
	 * Splits a string {@code str} by the given separator, preserving the separator as match in the returning array.
	 * @author Cletus
	 * @param str The string to split.
	 * @param separator The separator to use.
	 * @return The splitted parts as a string array.
	 */
	public static List<String> chop(String str, Pattern separator) {
		Matcher m = separator.matcher(str);
		List<String> chops = new ArrayList<>();
		int start = 0;
		while (m.find()) {
			chops.add(str.substring(start, m.start()));
			chops.add(m.group());
			start = m.end();
		}
		chops.add(start >= str.length() ? "" : str.substring(start));
		return chops;
	}

	/**
	 * Pads an integer.
	 * @param i The integer to pad.
	 * @param minimum The minimal number of characters.
	 * @return A string with the given number padded with zeros.
	 */
	public static String pad(int i, int minimum) {
		return pad((long) i, minimum);
	}

	/**
	 * Pads a long.
	 * @param l The long to pad.
	 * @param minimum The minimal number of characters.
	 * @return A string with the given number padded with zeros.
	 */
	public static String pad(long l, int minimum) {
		return pad("" + l, minimum, '0');
	}

	/**
	 * Pads a char.
	 * @param c The character to pad.
	 * @param minimum The minimal number of characters.
	 * @return A string with the given number padded with zeros.
	 */
	public static String pad(char c, int minimum) {
		return pad(String.valueOf(c), minimum);
	}

	/**
	 * Pads a string.
	 * @param str The string to pad.
	 * @param minimum The minimal number of characters.
	 * @return A string with the given string padded with spaces.
	 */
	public static String pad(String str, int minimum) {
		return pad(str, minimum, ' ', false);
	}

	/**
	 * Pads the given string until the string has a minimum length of {@code minimum}, using the given character.
	 * @param str The string to pad.
	 * @param minimum The minimum number of characters the string will contain.
	 * @param ch The padding character.
	 * @return The padded string.
	 */
	public static String pad(String str, int minimum, char ch) {
		return pad(str, minimum, ch, false);
	}

	/**
	 * Pads the given string until the string has a minimum length of {@code minimum}, using the given character.
	 * @param str The string to pad.
	 * @param minimum The minimum number of characters the string will contain.
	 * @param ch The padding character.
	 * @param trailing Whether the padding string should be appended to the end of the string
	 * instead of the beginning.
	 * @return The padded string.
	 */
	public static String pad(String str, int minimum, char ch, boolean trailing) {
		if (str.length() >= minimum) {
			return str;
		}
		int fill = minimum - str.length();
		for (int i = 0; i < fill; i++) {
			str = (trailing ? str + ch : ch + str);
		}
		return str;
	}

	/**
	 * Pads the given string on the left side, until the string has a minimum length of {@code minimum}, using the
	 * given character.<br>
	 * This is an alias of {@code pad(String str, int minimum, char ch)}.
	 * @param str The string to pad.
	 * @param minimum The minimum number of characters the string will contain.
	 * @param ch The padding character.
	 * @return The padded string.
	 */
	public static String leftpad(String str, int minimum, char ch) {
		return pad(str, minimum, ch, false);
	}

	/**
	 * Pads the given string on the right side, until the string has a minimum length of {@code minimum}, using the
	 * given {@code ch}.
	 * @param str The string to pad.
	 * @param minimum The minimum number of characters the string will contain.
	 * @param ch The padding character.
	 * @return The padded string.
	 */
	public static String rightpad(String str, int minimum, char ch) {
		return pad(str, minimum, ch, true);
	}

	/**
	 * Repeats the specified character a number of times.
	 * @param ch The character to repeat.
	 * @param count The number of times the character should be repeated.
	 * @return A string containing the result.
	 */
	public static String repeat(char ch, int count) {
		return repeat(Character.toString(ch), count);
	}

	/**
	 * Repeats a string a number of times.
	 * @param str The string to repeat.
	 * @param count The number of times the string should be repeated.
	 * @return The resulting repeated string.
	 */
	public static String repeat(String str, int count) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			sb.append(str);
		}
		return sb.toString();
	}

	/**
	 * Reverses the specified string. 'Maurits' becomes 'stiruaM'.
	 * @param string The string to reverse.
	 * @return The reversed string.
	 */
	public static String reverse(String string) {
		return new StringBuilder(string)
			.reverse()
			.toString();
	}

	/**
	 * Trims all consecutive characters specified with {@code character} on the left side of {@code string}
	 * and returns the trimmed string.
	 * @param string The string to trim.
	 * @param character The character to cut off.
	 * @return A trimmed string.
	 */
	public static String trimLeading(String string, char character) {
		return trimLeading(string, new String(new char[] { character }));
	}

	/**
	 * Trims all consecutive characters, as long as it is one of the characters specified in {@code chars}, on the
	 * left side of {@code string} and returns the trimmed string.
	 * @param string The string to trim.
	 * @param chars A string containing all characters to cut off.
	 * @return A trimmed string.
	 */
	public static String trimLeading(String string, String chars) {
		StringBuilder regex = new StringBuilder();
		for (int i = 0; i < chars.length(); i++) {
			regex.append(makeCompatibleToRegex(chars.charAt(i)));
		}
		return string.replaceAll("^[" + regex.toString() + "]+", "");
	}

	public static String[] trimLeadingWithAlignment(String[] strings) {
		return trimLeadingWithAlignment(strings, ' ');
	}

	public static String[] trimLeadingWithAlignment(String[] strings, char character) {
		// Count leading characters
		int[] leading = new int[strings.length];
		for (int i = 0; i < leading.length; i++) {
			leading[i] = getNumberOfLeadingChars(strings[i], character);
		}
		int min = IntStream.of(leading).min().orElse(0);
		for (int i = 0; i < strings.length; i++) {
			strings[i] = strings[i].substring(min);
		}
		return strings;
	}

	/**
	 * Trims all consecutive characters specified with {@code character} on the right side of {@code string}
	 * and returns the trimmed string.
	 * @param string The string to trim.
	 * @param character The character to cut off.
	 * @return A trimmed string.
	 */
	public static String trimTrailing(String string, char character) {
		return trimTrailing(string, new String(new char[] { character }));
	}

	/**
	 * Trims all consecutive characters, as long as it is one of the characters specified in {@code chars}, on the
	 * right side of {@code string} and returns the trimmed string.
	 * @param string The string to trim.
	 * @param chars A string containing all characters to cut off.
	 * @return A trimmed string.
	 */
	public static String trimTrailing(String string, String chars) {
		StringBuilder regex = new StringBuilder();
		for (int i = 0; i < chars.length(); i++) {
			regex.append(makeCompatibleToRegex(chars.charAt(i)));
		}
		return string.replaceAll("[" + regex.toString() + "]+$", "");
	}

	/**
	 * Trims all consecutive characters specified with {@code character} on both sides of {@code string} and
	 * returns the trimmed string.
	 * @param string The string to trim.
	 * @param character The character to cut off.
	 * @return A trimmed string.
	 */
	public static String trim(String string, char character) {
		return trimLeading(trimTrailing(string, character), character);
	}

	/**
	 * Trims all consecutive characters, as long as it is one of the characters specified in {@code chars}, on both
	 * sides of {@code string} and returns the trimmed string.
	 * @param string The string to trim.
	 * @param chars A string containing all characters to cut off.
	 * @return A trimmed string.
	 */
	public static String trim(String string, String chars) {
		return trimLeading(trimTrailing(string, chars), chars);
	}

	/**
	 * Makes a character compatible for use in a regular expression. That means that certain characters will be escaped.
	 * @param c The character to transform.
	 * @return The character, escaped if needed.
	 */
	private static String makeCompatibleToRegex(char c) {
		char[] chars = { '?', '*', '+', '^', '$', '(', ')', '[', ']', '{', '}', '/', '\\', '|', '.' };
		for (char cc : chars) {
			if (cc == c) {
				return "\\" + c;
			}
		}
		return "" + c;
	}

	/**
	 * Trims all elements of the array and returns the array.
	 * @param array The array to trim each element from.
	 * @return The input array, trimmed and ready.
	 */
	public static String[] trimArray(String[] array) {
		for (int i = 0; i < array.length; i++) {
			array[i] = array[i].trim();
		}
		return array;
	}

	/**
	 * Takes {@code string} and splits it into pieces with a size of exactly {@code chunkSize}. The last chunk
	 * might be smaller if there are no more characters to consume.
	 * @param string The string to chunk.
	 * @param chunkSize The chunk size.
	 * @return A string array with the pieces.
	 */
	public static List<String> chunk(String string, int chunkSize) {
		// Chunk size must be greater than 0.
		if (chunkSize <= 0) {
			throw new IllegalArgumentException("The chunk size must be greater than 0");
		}
		List<String> chunks = new ArrayList<>();
		// Calculate the number of full chunks and the remains.
		int count = string.length() / chunkSize;
		int remains = string.length() % chunkSize;
		for (int i = 0; i < count; i++) {
			chunks.add(string.substring(i * chunkSize, (i + 1) * chunkSize));
		}
		if (remains > 0) {
			chunks.add(string.substring(string.length() - remains, string.length()));
		}
		return chunks;
	}

	public static String chunkToString(String string, int chunkSize) {
		return chunkToString(string, chunkSize, "\n");
	}

	public static String chunkToString(String string, int chunkSize, String glue) {
		return join(chunk(string, chunkSize), glue);
	}

	public static String firstToUppercase(String str) {
		String[] words = str.split(" ");
		StringBuilder sb = new StringBuilder();
		for (String word : words) {
			if (word.length() > 0) {
				sb.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1));
			}
		}
		return sb.toString();
	}

	/**
	 * Gets a character from the specified string, or the default character if the given position is out of bounds.
	 * @param string The string to return the character from.
	 * @param position The string index at which to extract the character from.
	 * @param defaultChar The char to return if the given position is invalid for the given string.
	 * @return The character at the specified position, if valid, the default character otherwise.
	 */
	public static char getCharFromString(String string, int position, char defaultChar) {
		return (position >= 0 && position < string.length() ? string.charAt(position) : defaultChar);
	}

	/**
	 * Returns whether the given {@code string} contains only lowercase characters.
	 * @param string The input string.
	 * @return Whether the input string contains only lowercase characters.
	 */
	public static boolean isLowercase(String string) {
		return isLowercase(string, Locale.getDefault());
	}

	/**
	 * Returns whether the given {@code string} contains only lowercase characters, using the rules of the given
	 * locale.
	 * @param string The input string.
	 * @param locale The locale to use as reference.
	 * @return Whether the input string contains only lowercase characters.
	 */
	public static boolean isLowercase(String string, Locale locale) {
		return string.equals(string.toLowerCase(locale));
	}

	/**
	 * Returns whether the given {@code string} contains only uppercase characters.
	 * @param string The input string.
	 * @return Whether the input string contains only uppercase characters.
	 */
	public static boolean isUppercase(String string) {
		return isUppercase(string, Locale.getDefault());
	}

	/**
	 * Returns whether the given {@code string} contains only uppercase characters, using the rules of the given
	 * locale.
	 * @param string The input string.
	 * @param locale The locale to use as reference.
	 * @return Whether the input string contains only uppercase characters.
	 */
	public static boolean isUppercase(String string, Locale locale) {
		return string.equals(string.toUpperCase(locale));
	}

	/**
	 * Calculates whether the given word is a palindrome.
	 * @param word The word to check.
	 * @return Whether the given word is a palindrome or not.
	 */
	public static boolean isPalindrome(String word) {
		if (word.isEmpty()) {
			return true;
		}
		word = word.toLowerCase().replaceAll("[^a-z]", "");
		int it = (word.length() / 2) + 1;
		for (int i = 0; i < it; i++) {
			if (word.charAt(i) != word.charAt(word.length() - i - 1)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Normalizes the string to a universal format. This method is often used for uniforming newlines.
	 * @param string The input string to be normalized.
	 * @param scope The scope of what strength the normalization should have.
	 * @return A new string containing the normalized text.
	 */
	public static String normalize(String string, int scope) {
		int i = Integer.highestOneBit(scope) * 2;
		while (i > 0) {
			i >>>= 1;
			if (i <= scope) {
				switch (i) {
					case 0:
					default:
						break;
					case NORMALIZE_NEWLINES:
						string = string.replace("\r\n", "\n").replace("\r", "\n");
						break;
					case NORMALIZE_SPACE_TO_UNDERSCORE:
						string = string.replace(" ", "_");
						break;
					case NORMALIZE_PATH:
						string = string.replace("\\", "/");
						break;
				}
				scope -= i;
			}
		}
		return string;
	}

	public static int shortestLength(Object[] strings) {
		int[] lengths = new int[strings.length];
		for (int i = 0; i < strings.length; i++) {
			lengths[i] = strings[i].toString().length();
		}
		return IntStream.of(lengths).min().orElse(0);
	}

	public static int longestLength(Object[] strings) {
		int[] lengths = new int[strings.length];
		for (int i = 0; i < strings.length; i++) {
			lengths[i] = strings[i].toString().length();
		}
		return IntStream.of(lengths).max().orElse(0);
	}

	public static int getNumberOfLeadingChars(String string, char c) {
		int count = 0;
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) != c) {
				return count;
			}
			count++;
		}
		return count;
	}

	public static int getNumberOfTrailingChars(String string, char c) {
		int count = 0;
		for (int i = string.length() - 1; i >= 0; i--) {
			if (string.charAt(i) != c) {
				return count;
			}
			count++;
		}
		return count;
	}

	/**
	 * Returns a new string that is a substring of this string. The substring begins with the character at the specified
	 * index and extends to the end of this string. If the index lays beyond the end of the string, an empty string is
	 * returned.
	 * @param string The string to get the substring from.
	 * @param beginIndex The beginning index of the substring, inclusive.
	 * @return The specified substring.
	 */
	public static String substring(String string, int beginIndex) {
		return substring(string, beginIndex, string.length());
	}

	/**
	 * Returns a new string that is a substring of this string. The substring begins at the specified
	 * {@code beginIndex} and extends to the character at index {@code endIndex - 1}. Thus the length of the
	 * substring is {@code endIndex - beginIndex}.<br>
	 * This method is based on {@code String.substring(int, int)}, but doesn't throw a
	 * {@code StringIndexOutOfBoundsException}. Instead, it accepts positions beyond the last character of the
	 * string. Parts beyond the last string index are considered empty.<br><br>
	 *
	 * Examples:
	 * <ul>
	 * <li>{@code substring("Hello World!", 6, 10)} returns {@code Worl}</li>
	 * <li>{@code substring("Hello World!", 6, 42)} returns {@code World!}</li>
	 * <li>{@code substring("Hello World!", 18, 25)} returns an empty string</li>
	 * <li>{@code substring("Hello World!", 52, 37)} returns an empty string</li>
	 * </ul>
	 *
	 * @param str The string to get the substring from.
	 * @param beginIndex The beginning index of the substring, inclusive.
	 * @param endIndex The ending index, exclusive.
	 * @return The specified substring.
	 */
	public static String substring(String str, int beginIndex, int endIndex) {
		return str.substring(Math.min(beginIndex, str.length()), Math.min(endIndex, str.length()));
	}

	/**
	 * Splits the given {@code string} by the given {@code delimiter}, keeping the delimiters as part of the result. If
	 * the input string starts or ends with the given delimiter, then the first or last element of the resulting array
	 * contains the delimiter. If the source string contains multiple consecutive delimiters, then these delimiters are
	 * all present in the resulting array. Note that, unlike {@code String.split}, <em>no empty strings are placed into
	 * the resulting array</em>.
	 *
	 * @param string The string to split.
	 * @param delimiter The delimiting regular expression.
	 * @return A list with strings yielded by splitting the input string around matches of the given regular expression.
	 */
	public static List<String> splitRetainingDelimiter(String string, String delimiter) {
		Matcher m = Pattern.compile(delimiter).matcher(string);
		Stream.Builder<String> b = Stream.builder();
		int lastPos = 0;
		while (m.find()) {
			b.add(string.substring(lastPos, m.start())).add(m.group());
			lastPos = m.end();
		}
		b.add(string.substring(lastPos));
		return b.build().collect(Collectors.toList());
	}
}
