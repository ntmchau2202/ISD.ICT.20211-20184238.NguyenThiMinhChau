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
	
	public Media(String title, double price, boolean supportRush) throws InvalidMediaInfoException {
		this.setPrice(price);
		this.setTitle(title);
		this.setRushSupport(supportRush);
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) throws InvalidMediaInfoException {
		if(FunctionalUtils.contains(title, "[^A-Za-z0-9 /,-_?]")) {
			throw new InvalidMediaInfoException("Invalid character in media title");
		}
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) throws InvalidMediaInfoException {
		if(price <= 0) {
			throw new InvalidMediaInfoException("Invalid value for media price");
		}
		this.price = price;
	}

	public void setRushSupport(boolean isSupported) {
		this.supportRush = isSupported;
	}
	
	public boolean isRushSupported() {
		return this.supportRush;
	}
}
