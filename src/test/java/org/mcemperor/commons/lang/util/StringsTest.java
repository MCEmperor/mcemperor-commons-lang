package org.mcemperor.commons.lang.util;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Maurits de Jong
 */
public class StringsTest {

	@Test
	public void repeatWithCharArg() {
		assertEquals("", Strings.repeat('a', 0));
		assertEquals("qqqqqqq", Strings.repeat('q', 7));
	}

	@Test
	public void repeatWithStringArg() {
		assertEquals("", Strings.repeat("be", 0));
		assertEquals("bebebe", Strings.repeat("be", 3));
	}

	@Test(expected = IllegalArgumentException.class)
	public void repeatWithCharArgWithNegativeCount() {
		Strings.repeat("be", -3);
	}

	@Test
	public void testSplitRetainingDelimiterWithComma() {
		List<String> actual = Strings.splitRetainingDelimiter("a,b,c,d,e", ",");
		List<String> expected = Arrays.asList("a", ",", "b", ",", "c", ",", "d", ",", "e");
		assertEquals(expected, actual);
	}

	@Test
	public void testSplitRetainingDelimiterWithDollarSign() {
		List<String> actual = Strings.splitRetainingDelimiter("a$b$c$d$e", "\\$");
		List<String> expected = Arrays.asList("a", "$", "b", "$", "c", "$", "d", "$", "e");
		assertEquals(expected, actual);
	}

	@Test
	public void testSplitRetainingDelimiterWithEmptyFirstElement() {
		List<String> actual = Strings.splitRetainingDelimiter(",b,c,d,e", ",");
		List<String> expected = Arrays.asList("", ",", "b", ",", "c", ",", "d", ",", "e");
		assertEquals(expected, actual);
	}

	@Test
	public void testSplitRetainingDelimiterWithEmptyLastElement() {
		List<String> actual = Strings.splitRetainingDelimiter("a,b,c,d,", ",");
		List<String> expected = Arrays.asList("a", ",", "b", ",", "c", ",", "d", ",", "");
		assertEquals(expected, actual);
	}

	@Test
	public void testSplitRetainingDelimiterWithConsecutiveDelimiters() {
		List<String> actual = Strings.splitRetainingDelimiter("a,b,,,c", ",");
		List<String> expected = Arrays.asList("a", ",", "b", ",", "", ",", "", ",", "c");
		assertEquals(expected, actual);
	}

	@Test
	public void testSplitRetainingDelimiterWithEmptyString() {
		List<String> actual = Strings.splitRetainingDelimiter("", ",");
		List<String> expected = Arrays.asList("");
		assertEquals(expected, actual);
	}

	@Test
	public void testSplitRetainingDelimiterWithOnlyConsecutiveDelimiters() {
		List<String> actual = Strings.splitRetainingDelimiter(",,", ",");
		List<String> expected = Arrays.asList("", ",", "", ",", "");
		assertEquals(expected, actual);
	}

	@Test
	public void testSplitRetainingDelimiterWithMulticharacterDelimiter() {
		List<String> actual = Strings.splitRetainingDelimiter("bbqwqwqwcc", "qwqw");
		List<String> expected = Arrays.asList("bb", "qwqw", "qwcc");
		assertEquals(expected, actual);
	}

	@Test
	public void testNonNull() {
		assertEquals("", Strings.nonNull(null));
		assertEquals("hello", Strings.nonNull("hello"));
	}

	@Test
	public void testNonNullWithDefault() {
		String a = Strings.nonNull("alpha", "zulu");
		String b = Strings.nonNull(null, "zulu");

		assertEquals("alpha", a);
		assertEquals("zulu", b);
	}
}
