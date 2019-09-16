package org.mcemperor.commons.lang.builder;

import org.junit.Assert;
import org.mcemperor.commons.lang.builder.HashCodeBuilder;
import org.junit.Test;

/**
 *
 * @author jong0316
 */
public class HashCodeBuilderTest {

	@Test
	public void testNullConstructor() {
		new HashCodeBuilder<>(null)
			.get();
	}

	@Test
	public void testNullConstructorWithNullHashCode() {
		new HashCodeBuilder<>(null)
			.add(t -> null)
			.get();
	}

	@Test
	public void testAddWithFunctionArg() {
		new HashCodeBuilder<>("a")
			.add(String::hashCode)
			.get();
	}

	@Test
	public void testOf() {
		HashCodeBuilder<String> withConstructor = new HashCodeBuilder<>("alpha");
		HashCodeBuilder<String> withOf = HashCodeBuilder.of("alpha");

		Assert.assertEquals(withConstructor.get(), withOf.get());
	}
}
