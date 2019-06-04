package org.mcemperor.commons.lang.util;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Maurits de Jong
 */
public class CharactersTest {

	@Test
	public void testBoxed() {
		char[] chars = new char[] { 'A', 'u', 't', 'o', 'b', 'o', 'x', 'i', 'n', 'g' };
		List<Character> expected = Arrays.asList('A', 'u', 't', 'o', 'b', 'o', 'x', 'i', 'n', 'g');
		List<Character> actual = Characters.boxed(chars);

		assertEquals(expected, actual);
	}

	@Test
	public void testUnboxed() {
		List<Character> chars = Arrays.asList('U', 'n', 'b', 'o', 'x', 'i', 'n', 'g');
		char[] expected = new char[] { 'U', 'n', 'b', 'o', 'x', 'i', 'n', 'g' };
		char[] actual = Characters.unboxed(chars);

		assertEquals(expected.length, actual.length);
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}
}
