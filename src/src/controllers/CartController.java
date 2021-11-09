package controllers;

import entities.Cart;
import entities.medias.Media;
import exceptions.aims.AIMSException;
import exceptions.aims.MediaNotAvailableException;
import exceptions.aims.MediaUpdateException;
import exceptions.aims.PlaceOrderException;
import interfaces.PlaceOrderSubsystemInterface;

/**
 * 
 * @author Hikaru
 *
 */
public class CartController extends AIMSBaseController {
	private Cart cart;
	private PlaceOrderSubsystemInterface placeOrderSubsystem;
	
	/**
	 * creates a cart controller for handling manipulations on cart
	 */
	
	public CartController() {
		
	}
	
	/**
	 * modifies quantity of a media item in rush order
	 * @param item media item having quantity to be updated
	 * @param quantity new quantity of the media item
	 * @throws MediaUpdateException	if the update quantity is not valid, or errors occurs in the update process
	 */
	
	public void updateQuantity(Media item, int quantity) throws MediaUpdateException {
		
	}
	
	/**
	 * add an item to cart with quantity 
	 * @param item media item to be added to the cart 
	 * @param quantity quantity of the media item to be added to the cart
	 * @throws MediaNotAvailableException if the media item is not available or its quantity is not satisfied
	 */
	public void addItemToCart(Media item, int quantity) throws MediaNotAvailableException {
		
	}
	
	/**
	 * remove a media item from cart	
	 * @param item media item to be removed from cart
	 * @throws AIMSException indicates and errors inside the AIMS application
	 */
	public void deleteItemFromCart(Media item) throws AIMSException {
		
	}
	
	/**
	 * send information of media items with quantity to be orderd to the place order subsystem for processing
	 * @throws PlaceOrderException an exception from place order subsystem
	 */
	public void placeOrder() throws PlaceOrderException {
		
	}
}
