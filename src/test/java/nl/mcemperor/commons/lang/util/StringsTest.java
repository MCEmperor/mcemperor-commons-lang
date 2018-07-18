package nl.mcemperor.commons.lang.util;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author jong0316
 */
public class StringsTest {

	@Test
	public void testSplitRetainingDelimiterWithComma() {
		List<String> actual = Arrays.asList(Strings.splitRetainingDelimiter("a,b,c,d,e", ","));
		List<String> expected = Arrays.asList("a", ",", "b", ",", "c", ",", "d", ",", "e");
		assertEquals(expected, actual);
	}

	@Test
	public void testSplitRetainingDelimiterWithDollarSign() {
		List<String> actual = Arrays.asList(Strings.splitRetainingDelimiter("a$b$c$d$e", "\\$"));
		List<String> expected = Arrays.asList("a", "$", "b", "$", "c", "$", "d", "$", "e");
		assertEquals(expected, actual);
	}

	@Test
	public void testSplitRetainingDelimiterWithEmptyFirstElement() {
		List<String> actual = Arrays.asList(Strings.splitRetainingDelimiter(",b,c,d,e", ","));
		List<String> expected = Arrays.asList(",", "b", ",", "c", ",", "d", ",", "e");
		assertEquals(expected, actual);
	}

	@Test
	public void testSplitRetainingDelimiterWithEmptyLastElement() {
		List<String> actual = Arrays.asList(Strings.splitRetainingDelimiter("a,b,c,d,", ","));
		List<String> expected = Arrays.asList("a", ",", "b", ",", "c", ",", "d", ",");
		assertEquals(expected, actual);
	}

	@Test
	public void testSplitRetainingDelimiterWithConsecutiveDelimiters() {
		List<String> actual = Arrays.asList(Strings.splitRetainingDelimiter("a,b,,,c", ","));
		List<String> expected = Arrays.asList("a", ",", "b", ",", ",", ",", "c");
		assertEquals(expected, actual);
	}
}
