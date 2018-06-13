package nl.mcemperor.commons.lang.builder;

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
			.test(t -> null)
			.isEqual();
		
		assertTrue(result);
	}
	
	@Test
	public void testDoubleEqualStringConstructor() {
		boolean result = new EqualsBuilder<>("a", "a")
			.test(t -> t.getBytes())
			.isEqual();
		
		assertTrue(result);
	}
}
