package nl.mcemperor.commons.lang;

/**
 * A MalformedInputException should be thrown if the input to a method parsing some stream is invalid.
 *
 * @author Maurits de Jong
 * @since 2018-06-16
 */
public class MalformedInputException extends Exception {
	
	/**
	 * Constructs a MalformedInputException with the given message.
	 * 
	 * @param message The message associated to this exception.
	 */
	public MalformedInputException(String message) {
		super(message);
	}
}
