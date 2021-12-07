package controllers;

import java.util.Map;

import entities.Cart;
import entities.RushOrder;
import entities.medias.Media;
import exceptions.aims.AIMSException;
import exceptions.aims.PlaceOrderException;

/**
 * 
 * @author Hikaru
 *
 */
public class RushOrderController extends PlaceOrderController {
	private RushOrder rushOrder;
	
	/**
	 * create a controller for handling rush order request
	 */
	public RushOrderController(Cart cart) throws PlaceOrderException {
		super(cart);
		this.rushOrder = new RushOrder(cart);
	}
	
	/**
	 * get list of items in the cart that supports rush order
	 * @return a map of media items in the cart that support rush order with its current quantity in the cart
	 */
	public Map<Media, Integer> getListSupportedItem() {
		return this.rushOrder.getListRushItem();
	}
	
	/**
	 * add item to rush order
	 * @param item media item to be added to rush order
	 * @param quantity quantity of the selected item
	 */
	public void addItemToRushOrder(Media item, int quantity) throws PlaceOrderException {
		this.rushOrder.addItemToRushOrder(item, Integer.valueOf(quantity));
	}
	
	/**
	 * remove item from rush order
	 * @param item media item to be removed from the rush order
	 */
	public void deleteItemFromRushOrder(Media item) throws PlaceOrderException {
		this.rushOrder.adjustOrder(item, 0);
	}
	
	/**
	 * update quantity of item in rush order
	 * @param item media item having quantity to be updated
	 * @param quantity new quantity of the selected media
	 */
	public void updateItemInRushOrder(Media item, int quantity) throws PlaceOrderException {
		this.rushOrder.adjustOrder(item, quantity);
	}
	
	/**
	 * request to place rush order
	 * @throws PlaceOrderException indicates an error occurs during the placing order process
	 */
	public boolean validateRushOrder() throws AIMSException {
		try {
			for(java.util.Map.Entry<Media, Integer> item : rushOrder.getCart().getListMedia().entrySet()) {
				if(!super.isItemSelectionValid(item.getKey(), item.getValue().intValue())) {
					return false;
				}
			}
			return true;			
		} catch (AIMSException e) {
			throw e;
		}
		
	}
}
