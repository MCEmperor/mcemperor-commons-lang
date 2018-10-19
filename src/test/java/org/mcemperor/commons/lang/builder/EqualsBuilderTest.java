package org.mcemperor.commons.lang.builder;

import java.util.Objects;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author jong0316
 */
public class EqualsBuilderTest {

	@Test
	public void testDoubleNullConstructor() {
		boolean result = new EqualsBuilder<>(null, null)
			.isEqual();

		assertTrue(result);
	}

	@Test
	public void testDoubleNullConstructorWithNull() {
		boolean result = new EqualsBuilder<>(null, null)
			.test(t -> t.toString())
			.isEqual();

		assertTrue(result);
	}

	@Test
	public void testTestWithEqualObjects() {
		boolean result = new EqualsBuilder<>("a", "a")
			.test(t -> t.getBytes())
			.isEqual();

		assertTrue(result);
	}

	@Test
	public void testTestIfWithPredicateReturningTrue() {
		boolean result = new EqualsBuilder<>("a", "A")
			.testIf((t, u) -> t.equalsIgnoreCase(u))
			.isEqual();

		assertTrue(result);
	}

	@Test
	public void testTestIfWithPredicateReturningFalse() {
		boolean result = new EqualsBuilder<>("a", "A")
			.testIf(Objects::equals)
			.isEqual();

		assertFalse(result);
	}
}
