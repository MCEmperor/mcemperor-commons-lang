package org.mcemperor.commons.lang.util;

/**
 * A Pairable represents a pair of objects grouped together.
 *
 * @author Maurits de Jong
 * @since 2017-10-23
 * @param <T> The type of the first element.
 * @param <U> The type of the second element.
 */
public interface Pairable<T, U> {

	/**
	 * Returns the first element of the pair.
	 *
	 * @return The first element.
	 */
	public T first();

	/**
	 * Returns the second element of the pair.
	 *
	 * @return The second element.
	 */
	public U second();

}
