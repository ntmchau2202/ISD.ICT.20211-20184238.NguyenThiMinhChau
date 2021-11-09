package controllers;

import java.util.Map;

import entities.RushOrder;
import entities.medias.Media;
import exceptions.aims.PlaceOrderException;

/**
 * 
 * @author Hikaru
 *
 */
public class RushOrderController extends AIMSBaseController {
	private RushOrder rushOrder;
	
	/**
	 * create a controller for handling rush order request
	 */
	public RushOrderController() {
		
	}
	
	/**
	 * get list of items in the cart that supports rush order
	 * @return a map of media items in the cart that support rush order with its current quantity in the cart
	 */
	public Map<Media, Integer> getListSupportedItem() {
		return null;
	}
	
	/**
	 * add item to rush order
	 * @param item media item to be added to rush order
	 * @param quantity quantity of the selected item
	 */
	public void addItemToRushOrder(Media item, int quantity) {
		
	}
	
	/**
	 * remove item from rush order
	 * @param item media item to be removed from the rush order
	 */
	public void deleteItemToRushOrder(Media item) {
		
	}
	
	/**
	 * update quantity of item in rush order
	 * @param item media item having quantity to be updated
	 * @param quantity new quantity of the selected media
	 */
	public void updateItemInRushOrder(Media item, int quantity) {
		
	}
	
	/**
	 * request to place rush order
	 * @throws PlaceOrderException indicates an error occurs during the placing order process
	 */
	public void placeRushOrder() throws PlaceOrderException {
		
	}
}
