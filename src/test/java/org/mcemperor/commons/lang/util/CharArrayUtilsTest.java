package org.mcemperor.commons.lang.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Maurits de Jong
 */
public class CharArrayUtilsTest {

	@Test
	public void testEqualsWithDifferentLength() {
		char[] alpha = "alpha".toCharArray();
		char[] alphabet = "alphabet".toCharArray();

		assertFalse(CharArrayUtils.equals(alpha, alphabet));
	}

	@Test
	public void testEqualsWithSameLengthButDifferentContents() {
		char[] one = "one".toCharArray();
		char[] two = "two".toCharArray();

		assertFalse(CharArrayUtils.equals(one, two));
	}

	@Test
	public void testEqualsWithSameArrays() {
		char[] a = new char[] { 'a', 'b', 'c' };
		char[] b = new char[] { 'a', 'b', 'c' };

		assertTrue(CharArrayUtils.equals(a, b));
	}
}
