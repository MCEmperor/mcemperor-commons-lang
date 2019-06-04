package org.mcemperor.commons.lang.util;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Maurits de Jong
 */
public class BooleansTest {

	@Test
	public void testToInt() {
		assertEquals(0, Booleans.toInt(false));
		assertEquals(1, Booleans.toInt(true));
	}
}
