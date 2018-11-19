package org.mcemperor.commons.lang.builder;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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

	@Test
	public void testTestIfMapping() {
		List<String> list1 = Arrays.asList("alpha", "bravo", "charlie", "delta", "echo", "foxtrot");
		List<String> list2 = Arrays.asList("foxtrot", "charlie", "alpha", "echo", "bravo", "delta");

		boolean result = EqualsBuilder.of(list1, list2)
			.testIfMapping(t -> t.stream().sorted().collect(Collectors.toList()), Objects::equals)
			.isEqual();
		
		assertTrue(result);
	}
}
