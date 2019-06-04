package org.mcemperor.commons.lang.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.mcemperor.commons.lang.stream.Streams;
import static org.mcemperor.commons.lang.stream.Streams.pairing;
import static org.mcemperor.commons.lang.stream.Streams.unpairing;
import org.mcemperor.commons.lang.util.Pair;

/**
 *
 * @author Maurits de Jong
 */
public class StreamsTest {

	@Test
	public void testReverse() {
		List<String> list = Arrays.asList("a", "b", "c", "d", "e");
		List<String> expected = Arrays.asList("e", "d", "c", "b", "a");
		List<String> actual = Streams.reversedStreaming(list).collect(Collectors.toList());

		assertEquals(expected, actual);
	}

	@Test
	public void testPairing() {
		List<Pair<String, String>> actual = Stream.of("alpha", "bravo", "charlie", "delta")
			.map(pairing())
			.collect(Collectors.toList());

		List<Pair<String, String>> expected = Arrays.asList(
			Pair.of("alpha", "alpha"),
			Pair.of("bravo", "bravo"),
			Pair.of("charlie", "charlie"),
			Pair.of("delta", "delta"));

		assertEquals(expected, actual);
	}

	@Test
	public void testPairingWithFunctionArg() {
		List<Pair<String, String>> actual = Stream.of("alpha", "bravo", "charlie", "delta")
			.map(pairing(String::toUpperCase))
			.collect(Collectors.toList());

		List<Pair<String, String>> expected = Arrays.asList(
			Pair.of("alpha", "ALPHA"),
			Pair.of("bravo", "BRAVO"),
			Pair.of("charlie", "CHARLIE"),
			Pair.of("delta", "DELTA"));

		assertEquals(expected, actual);
	}

	@Test
	public void testUnpairing() {
		List<Pair<String, String>> pairs = Arrays.asList(
			Pair.of("alpha", "ALPHA"),
			Pair.of("bravo", "BRAVO"),
			Pair.of("charlie", "CHARLIE"),
			Pair.of("delta", "DELTA"));

		List<String> expected = Arrays.asList(
			"ALPHA-alpha",
			"BRAVO-bravo",
			"CHARLIE-charlie",
			"DELTA-delta");

		List<String> actual = pairs.stream()
			.map(unpairing((t, u) -> u + "-" + t))
			.collect(Collectors.toList());

		assertEquals(expected, actual);
	}
}
