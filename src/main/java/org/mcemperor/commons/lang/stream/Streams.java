package org.mcemperor.commons.lang.stream;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.mcemperor.commons.lang.util.Pair;

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
	public static <T> Stream<T> reversedStreaming(List<T> list) {
		return reversedIndices(list)
			.mapToObj(list::get);
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

	public static <T> Function<T, Pair<T, T>> pairing() {
		return t -> Pair.of(t, t);
	}

	public static <T, U> Function<T, Pair<T, U>> pairing(Function<T, U> function) {
		return t -> Pair.of(t, function.apply(t));
	}

	public static <K, T, R> Function<Pair<K, T>, R> unpairing(BiFunction<K, T, R> function) {
		return t -> function.apply(t.first(), t.second());
	}
}
