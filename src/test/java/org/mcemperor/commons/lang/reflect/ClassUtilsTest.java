package org.mcemperor.commons.lang.reflect;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
}
