package controllers;
import java.util.Map;

import entities.medias.Media;
import exceptions.aims.AIMSException;
import exceptions.aims.MediaNotAvailableException;

/**
 * 
 * @author Hikaru
 *
 */
public class MainScreenController extends AIMSBaseController {
	private Map<Media, Integer> listAllMediaInStore;
	private CartController cartController;
	
	/**
	 * create a controller for the main screen
	 */
	public MainScreenController() {
		
	}
	
	/**
	 * search for items that have its attribute containing the key and return to the display
	 * @param searchKey the key used to search for items in the store
	 * @return a map containing media items that match the criteria and its quantity in the store
	 */
	public Map<Media, Integer> search(String searchKey) {
		return null;
	}
	
	/**
	 * fetch the information about medias in the store for displaying on the screen
	 * @return a map containing media items currently available in the store and their corresponding quantities
	 * @throws AIMSException exception when refreshing the list
	 */
	public Map<Media, Integer> refreshMediaStore() throws AIMSException{
		return null;
	}
	
	/**
	 * add one media item with the selected quantity to the cart
	 * @param item media item to be added to the cart
	 * @param quantity quantity of the media item to be added
	 * @throws MediaNotAvailableException exception when the selected media is not currently available in the store, or the selected quantity exceeds the current available amount
	 */
	public void addItemToCart(Media item, int quantity) throws MediaNotAvailableException {
		
	}
}
