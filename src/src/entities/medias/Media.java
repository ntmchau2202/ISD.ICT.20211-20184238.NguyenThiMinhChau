package entities.medias;

import exceptions.aims.InvalidMediaInfoException;
import utils.FunctionalUtils;

/**
 * 
 * @author Hikaru
 *
 */
public abstract class Media {
	private String title;
	private double price;
	private boolean supportRush;
	/**
	 * 
	 * @param title
	 * @param price
	 * @param supportRush
	 * @throws InvalidMediaInfoException
	 */
	public Media(String title, double price, boolean supportRush) throws InvalidMediaInfoException {
		this.setPrice(price);
		this.setTitle(title);
		this.setRushSupport(supportRush);
	}
	/**
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 
	 * @param title
	 * @throws InvalidMediaInfoException
	 */
	public void setTitle(String title) throws InvalidMediaInfoException {
		if(FunctionalUtils.contains(title, "[^A-Za-z0-9 /,-_?]")) {
			throw new InvalidMediaInfoException("Invalid character in media title");
		}
		this.title = title;
	}
	/**
	 * 
	 * @return
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * 
	 * @param price
	 * @throws InvalidMediaInfoException
	 */
	public void setPrice(double price) throws InvalidMediaInfoException {
		if(price <= 0) {
			throw new InvalidMediaInfoException("Invalid value for media price");
		}
		this.price = price;
	}
	/**
	 * 
	 * @param isSupported
	 */
	public void setRushSupport(boolean isSupported) {
		this.supportRush = isSupported;
	}
	/**
	 * 
	 * @return
	 */
	public boolean isRushSupported() {
		return this.supportRush;
	}
}
