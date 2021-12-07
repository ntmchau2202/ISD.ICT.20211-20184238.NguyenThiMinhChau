package entities.medias;

import exceptions.aims.InvalidMediaInfoException;

/**
 * 
 * @author Hikaru
 *
 */

public class Book extends Media {
	/**
	 * 
	 * @param title
	 * @param price
	 * @param supportRush
	 * @throws InvalidMediaInfoException
	 */
	public Book(String title, double price, boolean supportRush) throws InvalidMediaInfoException {
		super(title, price, supportRush);
		// TODO Auto-generated constructor stub
	}
}
