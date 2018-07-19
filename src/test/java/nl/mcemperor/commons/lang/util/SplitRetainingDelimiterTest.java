package nl.mcemperor.commons.lang.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author jong0316
 */
public class SplitRetainingDelimiterTest {

	@Test
	public void testComma() {
		List<String> actual = Strings.splitRetainingDelimiter("a,b,c,d,e", ",");
		List<String> expected = Arrays.asList("a", ",", "b", ",", "c", ",", "d", ",", "e");
		assertEquals(expected, actual);
	}

	@Test
	public void testDollarSign() {
		List<String> actual = Strings.splitRetainingDelimiter("a$b$c$d$e", "\\$");
		List<String> expected = Arrays.asList("a", "$", "b", "$", "c", "$", "d", "$", "e");
		assertEquals(expected, actual);
	}

	@Test
	public void testEmptyFirstElement() {
		List<String> actual = Strings.splitRetainingDelimiter(",b,c,d,e", ",");
		List<String> expected = Arrays.asList("", ",", "b", ",", "c", ",", "d", ",", "e");
		assertEquals(expected, actual);
	}

	@Test
	public void testEmptyLastElement() {
		List<String> actual = Strings.splitRetainingDelimiter("a,b,c,d,", ",");
		List<String> expected = Arrays.asList("a", ",", "b", ",", "c", ",", "d", ",", "");
		assertEquals(expected, actual);
	}

	@Test
	public void testConsecutiveDelimiters() {
		List<String> actual = Strings.splitRetainingDelimiter("a,b,,,c", ",");
		List<String> expected = Arrays.asList("a", ",", "b", ",", "", ",", "", ",", "c");
		assertEquals(expected, actual);
	}

	@Test
	public void testEmptyString() {
		List<String> actual = Strings.splitRetainingDelimiter("", ",");
		List<String> expected = Arrays.asList("");
		assertEquals(expected, actual);
	}

	@Test
	public void testOnlyConsecutiveDelimiters() {
		List<String> actual = Strings.splitRetainingDelimiter(",,", ",");
		List<String> expected = Arrays.asList("", ",", "", ",", "");
		assertEquals(expected, actual);
	}

	@Test
	public void testMulticharacterDelimiter() {
		List<String> actual = Strings.splitRetainingDelimiter("bbqwqwqwcc", "qwqw");
		List<String> expected = Arrays.asList("bb", "qwqw", "qwcc");
		assertEquals(expected, actual);
	}
}
