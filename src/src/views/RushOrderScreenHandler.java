package views;

import controllers.AIMSBaseController;
import entities.medias.Media;

/**
 * 
 * @author Hikaru
 *
 */
public class RushOrderScreenHandler extends AIMSBaseScreenHandler  {

	/***
	 * Initialize the RushOrder screen
	 * @param prevScreen a handler to the screen that called this screen
	 */
	protected RushOrderScreenHandler(AIMSBaseScreenHandler prevScreen) {
		super(prevScreen);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * display the media items in cart that support rush order
	 */
	public void displaySupportedItem() {
		
	}

	/**
	 * add media item that support rush order to the rush order list
	 * @param item media item that support rush order
	 * @param quantity quantity of the selected item
	 */
	public void addItemToRushOrder(Media item, int quantity) {
		
	}
	
	/**
	 * remove a media item from the rush order list
	 * @param item media item to be removed
	 */
	public void deleteItemFromRushOrder(Media item) {
		
	}
	
	/**
	 * adjust quantity of a media item currently in rush order list
	 * @param item media item having its quantity be adjusted
	 * @param quantity new quantity of the media item
	 */
	public void adjustItemInRushOrder(Media item, int quantity) {
		
	}
	
	/**
	 * request to place a rush order
	 */
	public void placeRushOrder() {
		
	}
	
	
}
