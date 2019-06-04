package org.mcemperor.commons.lang.reflect;

import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author mauri
 */
public class ClassUtilsTest {

	@Test
	public void testIsPrimitive() {
		assertTrue(ClassUtils.isPrimitive(boolean.class));
		assertTrue(ClassUtils.isPrimitive(byte.class));
		assertTrue(ClassUtils.isPrimitive(short.class));
		assertTrue(ClassUtils.isPrimitive(int.class));
		assertTrue(ClassUtils.isPrimitive(long.class));
		assertTrue(ClassUtils.isPrimitive(char.class));
		assertTrue(ClassUtils.isPrimitive(float.class));
		assertTrue(ClassUtils.isPrimitive(double.class));

		assertFalse(ClassUtils.isPrimitive(void.class));
		assertFalse(ClassUtils.isPrimitive(String.class));
	}

	@Test
	public void testIsPrimitiveWrapper() {
		assertTrue(ClassUtils.isPrimitiveWrapper(Boolean.class));
		assertTrue(ClassUtils.isPrimitiveWrapper(Byte.class));
		assertTrue(ClassUtils.isPrimitiveWrapper(Short.class));
		assertTrue(ClassUtils.isPrimitiveWrapper(Integer.class));
		assertTrue(ClassUtils.isPrimitiveWrapper(Long.class));
		assertTrue(ClassUtils.isPrimitiveWrapper(Character.class));
		assertTrue(ClassUtils.isPrimitiveWrapper(Float.class));
		assertTrue(ClassUtils.isPrimitiveWrapper(Double.class));

		assertFalse(ClassUtils.isPrimitiveWrapper(Void.class));
		assertFalse(ClassUtils.isPrimitiveWrapper(String.class));
		assertFalse(ClassUtils.isPrimitiveWrapper(long.class));
	}

	@Test
	public void testJuggleMatchingClass() {
		String alpha = "Hello World!";
		String resultAlpha = ClassUtils.juggle(alpha);

		assertEquals(resultAlpha, "Hello World!");
	}

	@Test(expected = ClassCastException.class)
	public void testJuggleMismatchingClass() {
		String s = "Hello World!";
		Integer i = ClassUtils.juggle(s);

		// We expect a ClassCastException to be thrown, because a String cannot be cast to an Integer. If we reach this,
		// no ClassCastException is thrown and the test will fail.
		fail();
	}
}
