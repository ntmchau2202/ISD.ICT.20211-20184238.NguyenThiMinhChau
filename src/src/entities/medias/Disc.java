package entities.medias;

import exceptions.aims.InvalidMediaInfoException;

/**
 * 
 * @author Hikaru
 *
 */
public abstract class Disc extends Media {
	protected double length;
	public Disc(int ID, String title, double price, boolean supportRush, String imagePath) throws InvalidMediaInfoException {
		super(ID, title, price, supportRush, imagePath);
	}
	
	public void setLength(double length) {
		this.length = length;
	}
	
	public double getLength() {
		return this.length;
	}

}
