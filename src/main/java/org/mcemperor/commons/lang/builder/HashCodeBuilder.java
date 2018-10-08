package org.mcemperor.commons.lang.builder;

import java.util.Objects;
import java.util.function.Function;

/**
 * The HashCodeBuilder class eases writing {@code hashCode()} methods within classes. Upon creating an instance of this
 * class, the object to create the hash code from must be specified. Then, the {@code add} method can be called to pass
 * fields or methods to be included in the hash code calculation. At last, calling {@code get()} yields the final
 * result, which can be returned by the implementing {@code hashCode()} method.<br><br>
 *
 * Example usage:
 *
 * <pre>{@code  @Override
 * public int hashCode() {
 *     return new HashCodeBuilder<>(this)
 *         .add(t -> t.foo)
 *         .add(t -> t.baz)
 *         .get();
 * }}</pre>
 *
 * @author Maurits de Jong
 * @param <T> The type of the object contained in the HashCodeBuilder.
 */
public class HashCodeBuilder<T> {

	/**
	 * The instance subject to the hash code calculation.
	 */
	private T instance;

	/**
	 * The eventual hash code.
	 */
	private int hashCode;

	/**
	 * Constructs a new HashCodeBuilder instance with the given object.
	 *
	 * @param o The object to check.
	 */
	public HashCodeBuilder(T o) {
		this.instance = o;
		this.hashCode = 3;
	}

	/**
	 * Applies the given function to the object to inspect and get its add get using the {@code hashCode()} method.<br>
	 * For instance, if some class {@code Foo} has a field {@code bar}, then a call to {@code add} could look like this:
	 * {@code add(t -> t.bar)}.
	 *
	 * @param function The function to get properties of the passed objects.
	 * @return This EqualsBuilder, to allow method call chaining.
	 */
	public HashCodeBuilder<T> add(Function<T, ?> function) {
		if (this.instance != null) {
			this.hashCode += 31 * Objects.hashCode(function.apply(this.instance));
		}
		return this;
	}

	/**
	 * Gets the eventual hash code.
	 *
	 * @return The hash code as an int.
	 */
	public int get() {
		return this.hashCode;
	}
}
