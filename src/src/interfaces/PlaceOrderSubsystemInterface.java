package interfaces;

import entities.Cart;
import exceptions.aims.PlaceOrderException;

/**
 * This interface separate the logicals of placing an order with the main stage that allows customers to surf and select items to buy <br>
 * By this, the logic of placing order can easily be updated without affecting the main stage
 * @author chauntm
 *
 */
public interface PlaceOrderSubsystemInterface {
	/**
	 * perform place order process with items in the cart
	 * @param cart a cart with items for ordering
	 * @throws PlaceOrderException indicates an error occurs during the placing order process
	 */
	public void placeOrder(Cart cart) throws PlaceOrderException;
	
	public void placeRushOrder(Cart cart) throws PlaceOrderException;
}
