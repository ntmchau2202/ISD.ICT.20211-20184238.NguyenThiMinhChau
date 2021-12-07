package controllers;

import boundaries.PlaceOrderBoundary;
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
		this.cart = new Cart();
		placeOrderSubsystem = new PlaceOrderBoundary();
	}
	
	/**
	 * modifies quantity of a media item in rush order
	 * @param item media item having quantity to be updated
	 * @param quantity new quantity of the media item
	 * @throws MediaUpdateException	if the update quantity is not valid, or errors occurs in the update process
	 */
	
	public void updateQuantity(Media item, int quantity) throws MediaUpdateException {
		this.cart.updateQuantity(item, quantity);
	}
	
	/**
	 * 
	 * @param item
	 * @param quantity
	 * @throws MediaUpdateException
	 */
	public void addItemToCart(Media item, int quantity) throws MediaUpdateException {
		this.cart.addItemToCart(item, quantity);
	}
	
	/**
	 * 
	 * @param item
	 * @throws MediaUpdateException
	 */
	public void deleteItemFromCart(Media item) throws MediaUpdateException {
		this.cart.deleteItemFromCart(item);
	}
	
	/**
	 * send information of media items with quantity to be orderd to the place order subsystem for processing
	 * @throws PlaceOrderException an exception from place order subsystem
	 */
	public void placeOrder() throws PlaceOrderException {
		this.placeOrderSubsystem.placeOrder(cart);
	}
}
