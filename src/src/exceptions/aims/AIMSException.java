package exceptions.aims;

/**
 * This class and its derivatives are exceptions can be met when the AIMS main functions
 * @author chauntm
 *
 */
public abstract class AIMSException extends Exception {

	public AIMSException(String message) {
		super(message);
	}

}
