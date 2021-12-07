package utils;

import entities.Invoice;
import exceptions.aims.MediaNotAvailableException;

public class DBUtils {
	/**
	 * Get current number of a media in store
	 * @param mediaName Media name to be check available quantity
	 * @return number of available media in store
	 * @throws MediaNotAvailableException if the name does not match any media in database
	 */
	public static int getCurrentAvailableQuatity(String mediaName) throws MediaNotAvailableException {
		// do something to connect to DB and get information here
		return 0;
	}
	
	/**
	 * Get new transaction ID for performing actions
	 * @return a new transaction ID which is unique, according to the database
	 */
	public static int getNewTransactionID() {
		// do st to get transaction id here
		return 0;
	}
	
	/**
	 * save invoice to database
	 * @param invoice invoice to be save
	 */
	public static void saveInvoice(Invoice invoice) {
		// do st to save invoice here
	}
}
