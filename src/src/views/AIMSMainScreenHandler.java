package views;

import controllers.AIMSBaseController;
import entities.medias.Media;

/**
 * 
 * @author Hikaru
 *
 */
public class AIMSMainScreenHandler extends AIMSBaseScreenHandler {

	/***
	 * Initialize the main screen of AIMS
	 * @param prevScreen a handler to the screen that called this screen
	 */
	protected AIMSMainScreenHandler(AIMSBaseScreenHandler prevScreen) {
		super(prevScreen);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	/***
	 * Refresh the current medias in store with their corresponding available quantity
	 */
	public void refreshMediaStore() {
		
	}
	
	/***
	 * Search for a media item that have searchKey in its attributes and display result
	 * @param searchKey the key pharse used to search
	 */
	public void search(String searchKey) {
		
	}
	
	/***
	 * Add an item with selected quantity to cart
	 * @param item a media item to be added to cart
	 * @param quantity quantity of the media item to be added to cart
	 */

	public void addItemToCart(Media item, int quantity) {
		
	}
}
