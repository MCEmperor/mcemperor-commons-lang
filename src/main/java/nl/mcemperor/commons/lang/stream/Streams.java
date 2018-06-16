package nl.mcemperor.commons.lang.stream;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Streams utilities.
 *
 * @author Maurits de Jong
 * @since 2018-06-16
 */
public class Streams {
	
	/**
	 * Private constructor, for this is a utility class.
	 */
	private Streams() { }
	
	/**
	 * Returns a stream of the elements in the given list in reversed order.
	 * 
	 * @param <T> The type of elements in the list.
	 * @param list The list to get the elements from.
	 * @return A Stream with the elements in reversed order.
	 */
	public static <T> Stream<T> reverse(List<T> list) {
		return IntStream.rangeClosed(list.size() - 1, 0)
			.mapToObj(i -> list.get(i));
	}
}
