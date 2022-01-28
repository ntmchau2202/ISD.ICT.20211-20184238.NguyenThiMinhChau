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
	private int mediaID;
	private String imagePath;
	/**
	 * 
	 * @param title
	 * @param price
	 * @param supportRush
	 * @throws InvalidMediaInfoException
	 */
	public Media(int ID, String title, double price, boolean supportRush, String imagePath) throws InvalidMediaInfoException {
		this.setMediaID(ID);
		this.setPrice(price);
		this.setTitle(title);
		this.setRushSupport(supportRush);
		this.setImagePath(imagePath);
		
	}
	
	public String getImagePath() {
		return this.imagePath;
	}
	
	public void setImagePath(String imageName) {
		this.imagePath = imageName;
	}
	
	public int getMediaID() {
		return mediaID;
	}

	public void setMediaID(int mediaID) {
		this.mediaID = mediaID;
	}

	public Media(int ID, String title, double price, boolean supportRush, int currentQuantity) throws InvalidMediaInfoException {
		this.setMediaID(ID);
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
