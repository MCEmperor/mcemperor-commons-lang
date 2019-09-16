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
	public void testConstructorWithSingleNull() {
		assertFalse(new EqualsBuilder<>(null, "a").isEqual());
		assertFalse(new EqualsBuilder<>("q", null).isEqual());
	}

	@Test
	public void testConstructorWithDoubleNull() {
		assertTrue(new EqualsBuilder<>(null, null).isEqual());
	}

	@Test
	public void testConstructorWithDoubleNullAndThenToString() {
		boolean result = new EqualsBuilder<>(null, null)
			.test(t -> t.toString())
			.isEqual();

		assertTrue(result);
	}

	@Test
	public void testConstructorWithNotInstanceOf() {
		assertFalse(new EqualsBuilder<>("z", 23).isEqual());
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
	public void testTestIfMappingWithListOfSameElements() {
		List<String> list1 = Arrays.asList("alpha", "bravo", "charlie", "delta", "echo", "foxtrot");
		List<String> list2 = Arrays.asList("foxtrot", "charlie", "alpha", "echo", "bravo", "delta");

		boolean result = EqualsBuilder.of(list1, list2)
			.testIfMapping(t -> t.stream().sorted().collect(Collectors.toList()), Objects::equals)
			.isEqual();

		assertTrue(result);
	}

	@Test
	public void testTestIfMappingWithListOfDifferentElements() {
		List<String> list1 = Arrays.asList("alpha", "bravo", "charlie", "delta", "echo", "foxtrot");
		List<String> list2 = Arrays.asList("quebec", "tango", "romeo", "papa", "uniform", "sierra");

		boolean result = EqualsBuilder.of(list1, list2)
			.testIfMapping(t -> t.stream().sorted().collect(Collectors.toList()), Objects::equals)
			.isEqual();

		assertFalse(result);
	}
}
