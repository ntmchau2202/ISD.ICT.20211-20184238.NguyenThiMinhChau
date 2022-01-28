package exceptions.interbank;

/**
 * 
 * @author Hikaru
 *
 */
public class NotEnoughBalanceException extends InterbankException {

	public NotEnoughBalanceException(String string) {
		super(string);
	}

}
