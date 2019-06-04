package org.mcemperor.commons.lang.util;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Maurits de Jong
 */
public class IntegersTest {

	@Test
	public void testBoxed() {
		int[] integers = new int[] { 2, 3, 5, 7, 11, 13, 17, 23, 29 };
		List<Integer> expected = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 23, 29);
		List<Integer> actual = Integers.boxed(integers);

		assertEquals(expected, actual);
	}

	@Test
	public void testUnboxed() {
		List<Integer> integers = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 23, 29, 1337);
		int[] expected = new int[] { 2, 3, 5, 7, 11, 13, 17, 23, 29, 1337 };
		int[] actual = Integers.unboxed(integers);

		assertEquals(expected.length, actual.length);
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	@Test
	public void testGetOrDefaultWithParsableString() {
		int a = Integers.getOrDefault("23", 41);
		int b = Integers.getOrDefault("-29", 41);
		int c = Integers.getOrDefault("--17", 41);

		assertEquals(23, a);
		assertEquals(-29, b);
		assertEquals(41, c);
	}
}
