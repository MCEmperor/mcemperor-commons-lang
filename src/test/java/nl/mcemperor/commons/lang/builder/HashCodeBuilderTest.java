package nl.mcemperor.commons.lang.builder;

import org.junit.Test;

/**
 *
 * @author jong0316
 */
public class HashCodeBuilderTest {

	@Test
	public void testNullConstructor() {
		int hashCode = new HashCodeBuilder<>(null)
			.code();
	}
	
	@Test
	public void testNullConstructorWithNullHashCode() {
		int hashCode = new HashCodeBuilder<>(null)
			.hash(t -> null)
			.code();
	}
	
	@Test
	public void test() {
		int hashCode = new HashCodeBuilder<>("a")
			.hash(t -> t.hashCode())
			.code();
	}
}
