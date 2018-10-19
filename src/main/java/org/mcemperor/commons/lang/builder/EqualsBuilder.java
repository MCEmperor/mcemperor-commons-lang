package org.mcemperor.commons.lang.builder;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 * The EqualsBuilder class eases writing {@code equals} methods within classes.<br>
 * The {@code equals} method is often implemented as first casting the object to the desired type, and, if successful,
 * checking for each field if it is "equal to" the corresponding field of the given object, using the {@code equals}
 * method of the field, or using the {@code Objects::equals} method in the {@code java.util} package. However, this
 * yields often boilerplate code, obscuring the real implementation of the {@code equals} method.<br>
 * The EqualsBuilder class addresses this problem by deferring this code to this EqualsBuilder. Upon creating an
 * instance of this class, the types of the two given objects are checked for compatibility. Then, the {@code test}
 * methods can be called to pass fields or methods be included in the equality. At last, calling {@code isEqual()}
 * yields the final result, which can be returned by the implementing {@code equals} method.<br>
 * The code is short-circuiting, meaning that once the equality is determined, all subsequent tests are skipped.
 * <br><br>
 *
 * Example usage:
 * <pre><code>
 *    &#64;Override
 *    public boolean equals(Object o) {
 *        return new EqualsBuilder&lt;&gt;(this, o)
 *            .test(super::equals)
 *            .test(YourClass::anotherField)
 *            .testIf((t, u) -> t.equalsIgnoreCase(u))
 *            .isEqual();
 *    }
 * </code></pre>
 *
 * @author Maurits de Jong
 * @param <T> The type of the objects contained in the EqualsBuilder.
 */
public class EqualsBuilder<T> {

	/**
	 * Whether the two objects are considered "equal". If set to {@code null}, then the result is still undetermined.
	 */
	private Boolean equal;

	/**
	 * The other object to compare to.
	 */
	private T first;

	/**
	 * The other object to compare to.
	 */
	private T second;

	/**
	 * Creates a new EqualsBuilder from the given two objects. The EqualsBuilder first checks if both objects have equal
	 * identity. If not, then it is determined if one of the objects is {@code null} or both objects are not
	 * assignment-compatible. If both objects are not {@code null} and are assignment-compatible, then both objects are
	 * stored within the builder. The user can then call the {@code test} or {@code testIf} methods to check other
	 * properties of both objects.<br>
	 * This class is particularly useful when one wants to implements the {@code equals} method defined by the {@code
	 * Object} class. The first object passed is often the {@code this} reference in whose class the EqualsBuilder is
	 * created.
	 *
	 * @param o The first object, often passed in as {@code this}.
	 * @param o2 The other object to compare the first object to, or {@code null}.
	 */
	@SuppressWarnings("unchecked")
	public EqualsBuilder(T o, Object o2) {
		if (o == o2) {
			this.equal = true;
		}
		else if (o == null || o2 == null || !o2.getClass().isInstance(o)) {
			this.equal = false;
		}
		else {
			this.first = o;
			this.second = (T) o2;
		}
	}

	/**
	 * Returns whether the result has already been determined or not. It is determined if the equality Boolean is not
	 * null. With calling this method, the user is able to short-circuit a test when the result is already determined.
	 *
	 * @return Whether the result is defined.
	 */
	private boolean result() {
		return (this.equal != null);
	}

	/**
	 * Applies the given function to the two objects to be compared, tests their results for equality, using their
	 * {@code equals()} method. If the objects are not considered equal, then the result is set to {@code false} causing
	 * all subsequent tests to return immediately.<br>
	 * The method can be called using static method reference. For instance, if some class {@code Foo} has a method
	 * {@code getBar()} returning the object's {@code bar} property, then a call to {@code test()} could look like this:
	 * {@code test(Foo::getBar)}.
	 *
	 * @param function The function to get properties of the passed objects.
	 * @return This EqualsBuilder to allow method call chaining.
	 */
	public final EqualsBuilder<T> test(Function<T, ?> function) {
		if (!result() && !Objects.equals(function.apply(this.first), function.apply(this.second))) {
			this.equal = false;
		}
		return this;
	}

	/**
	 * Applies the given BiPredicate to the two objects to be compared. If the predicate returns {@code false}, then the
	 * final result is set to {@code false}, causing all subsequent tests to return immediately.
	 *
	 * @param predicate A BiPredicate to be applied to both objects to be compared.
	 * @return This EqualsBuilder to allow method call chaining.
	 */
	public final EqualsBuilder<T> testIf(BiPredicate<T, T> predicate) {
		if (!result() && !predicate.test(this.first, this.second)) {
			this.equal = false;
		}
		return this;
	}

	/**
	 * Returns the final result of whether the two compared objects are equal.
	 *
	 * @return Whether the two objects are equal.
	 */
	public final boolean isEqual() {
		return (this.equal != null ? this.equal : true);
	}
}
