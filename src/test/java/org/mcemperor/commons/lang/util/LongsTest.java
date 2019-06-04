package org.mcemperor.commons.lang.util;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Maurits de Jong
 */
public class LongsTest {

	@Test
	public void testBoxed() {
		long[] longs = new long[] { 2, 3, 5, 7, 11, 13, 17, 23, 29 };
		List<Long> expected = Arrays.asList(2L, 3L, 5L, 7L, 11L, 13L, 17L, 23L, 29L);
		List<Long> actual = Longs.boxed(longs);

		assertEquals(expected, actual);
	}

	@Test
	public void testUnboxed() {
		List<Long> longs = Arrays.asList(2L, 3L, 5L, 7L, 11L, 13L, 17L, 23L, 29L, 1337L);
		long[] expected = new long[] { 2, 3, 5, 7, 11, 13, 17, 23, 29, 1337 };
		long[] actual = Longs.unboxed(longs);

		assertEquals(expected.length, actual.length);
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}
}
