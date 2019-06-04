package org.mcemperor.commons.lang.util;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Maurits de Jong
 */
public class ShortsTest {

	@Test
	public void testBoxed() {
		short[] shorts = new short[] { 2, 3, 5, 7, 11, 13, 17, 23, 29 };
		List<Short> expected = Arrays.asList((short) 2, (short) 3, (short) 5, (short) 7, (short) 11, (short) 13, (short) 17, (short) 23, (short) 29);
		List<Short> actual = Shorts.boxed(shorts);

		assertEquals(expected, actual);
	}

	@Test
	public void testUnboxed() {
		List<Short> shorts = Arrays.asList((short) 2, (short) 3, (short) 5, (short) 7, (short) 11, (short) 13, (short) 17, (short) 23, (short) 29, (short) 1337);
		short[] expected = new short[] { 2, 3, 5, 7, 11, 13, 17, 23, 29, 1337 };
		short[] actual = Shorts.unboxed(shorts);

		assertEquals(expected.length, actual.length);
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

}
