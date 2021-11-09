package views;

import controllers.AIMSBaseController;
import entities.medias.Media;

/**
 * 
 * @author Hikaru
 *
 */
public class CartScreenHandler extends AIMSBaseScreenHandler {

	/***
	 * Initialize the cart screen of AIMS
	 * @param prevScreen a handler to the screen that called this screen
	 */
	protected CartScreenHandler(AIMSBaseScreenHandler prevScreen) {
		super(prevScreen);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Adjust quantity of one selected item in the cart
	 * @param item media item that will have its quantity adjusted
	 * @param quantity the new quantity of the selected media
	 */
	public void adjustItemQuantity(Media item, int quantity) {
		
	}
	
	/**
	 * Remove a media item from the cart
	 * @param item media item to be removed from the cart
	 */
	public void deleteItem(Media item) {
		
	}
	
	/**
	 * Request to create an order with the currently selected items in cart
	 */
	public void placeOrder() {
		
	}

}
