package nl.mcemperor.commons.lang.builder;

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
	public void test() {
		new HashCodeBuilder<>("a")
			.add(String::hashCode)
			.get();
	}
}
