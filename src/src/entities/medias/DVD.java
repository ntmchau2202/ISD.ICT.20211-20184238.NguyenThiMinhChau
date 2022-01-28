package entities.medias;

import exceptions.aims.InvalidMediaInfoException;

/**
 * @author Hikaru
 *
 */
public class DVD extends Disc {

	public DVD(int ID, String title, double price, boolean supportRush, String imagePath) throws InvalidMediaInfoException {
		super(ID, title, price, supportRush, imagePath);
	}
}
