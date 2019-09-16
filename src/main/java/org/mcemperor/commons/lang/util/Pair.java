package org.mcemperor.commons.lang.util;

import org.mcemperor.commons.lang.builder.EqualsBuilder;
import org.mcemperor.commons.lang.builder.HashCodeBuilder;

/**
 * The Pair class represents an unchangable pair of objects.
 *
 * @author Maurits de Jong
 * @since 2017-10-23
 * @param <T> The type of the first element.
 * @param <U> The type of the second element.
 */
public class Pair<T, U> implements Pairable<T, U> {

	/**
	 * The first element.
	 */
	private final T first;

	/**
	 * The second element.
	 */
	private final U second;

	/**
	 * Creates a new Pair instance from the given pair of objects.
	 *
	 * @param first The first object.
	 * @param second The second object.
	 */
	private Pair(T first, U second) {
		this.first = first;
		this.second = second;
	}

	/**
	 * Static factory method to create a Pair instance.
	 *
	 * @param <T> The type of the first element.
	 * @param <U> The type of the second element.
	 * @param first The first element.
	 * @param second The second element.
	 * @return A new ImmuablePair instance.
	 */
	public static <T, U> Pair<T, U> of(T first, U second) {
		return new Pair<>(first, second);
	}

	/**
	 * Gets the first element.
	 *
	 * @return The first element
	 */
	@Override
	public T first() {
		return this.first;
	}

	/**
	 * Gets the second element.
	 *
	 * @return The second element.
	 */
	@Override
	public U second() {
		return this.second;
	}

	/**
	 * Whether this pair is equal to the specified one.
	 *
	 * @param obj The other object.
	 * @return Whether this pair is considered equal to the specified object.
	 */
	@Override
	public boolean equals(Object obj) {
		return new EqualsBuilder<>(this, obj)
			.test(t -> t.first)
			.test(t -> t.second)
			.isEqual();
	}

	/**
	 * Returns a add get for this object.
	 *
	 * @return The add get.
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder<>(this)
			.add(t -> t.first)
			.add(t -> t.second)
			.get();
	}

	/**
	 * Returns a textual representation of this Pair instance.
	 *
	 * @return This instance as a string.
	 */
	@Override
	public String toString() {
		return String.format("ImmutablePair(%s, %s)", this.first, this.second);
	}
}
