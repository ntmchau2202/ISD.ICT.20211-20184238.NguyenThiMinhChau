package entities.medias;

import exceptions.aims.InvalidMediaInfoException;

/**
 * 
 * @author Hikaru
 *
 */
public abstract class Disc extends Media {

	public Disc(String title, double price, boolean supportRush) throws InvalidMediaInfoException {
		super(title, price, supportRush);
		// TODO Auto-generated constructor stub
	}

}
