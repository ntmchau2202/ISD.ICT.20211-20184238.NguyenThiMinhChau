package entities;

import entities.medias.Media;
import exceptions.aims.PlaceOrderException;

/**
 * 
 * @author Hikaru
 *
 */
public class Order {
	private Cart cart;
	protected Customer customer;
	
	public Order(Cart cart) {
		this.cart = cart;
	}
	
	public Cart getCart() {
		return this.cart;
	}
	
	public void updateCustomer(Customer customer) throws PlaceOrderException {
		this.customer = customer;
	}
	
	public void adjustOrder(Media item, int newQuantity) throws PlaceOrderException {
		try {
			if(newQuantity <= 0) {
				this.cart.deleteItemFromCart(item);
			} else {
				this.cart.updateQuantity(item, newQuantity);
			}
		} catch (Exception e) {
			throw new PlaceOrderException(e.getMessage());
		}
	}
}
