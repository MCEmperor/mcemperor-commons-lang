package org.mcemperor.commons.lang.stream;

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
		return reversedIndices(list)
			.mapToObj(i -> list.get(i));
	}
	
	/**
	 * Returns a Stream with all valid indices of {@code list}, in reversed order.
	 * 
	 * @param <T> The type of elements of the list.
	 * @param list The list to stream from.
	 * @return A Stream consisting of the indices.
	 */
	public static <T> IntStream reversedIndices(List<T> list) {
		return IntStream.range(0, list.size())
			.map(i -> list.size() - 1 - i);
	}
}
