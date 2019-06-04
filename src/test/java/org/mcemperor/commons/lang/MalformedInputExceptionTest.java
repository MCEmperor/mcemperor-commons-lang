package org.mcemperor.commons.lang;

import org.junit.Test;

/**
 *
 * @author Maurits de Jong
 */
public class MalformedInputExceptionTest {

	@Test(expected = MalformedInputException.class)
	public void testConstructor() throws MalformedInputException {
		throw new MalformedInputException("Hello World!");
	}
}
