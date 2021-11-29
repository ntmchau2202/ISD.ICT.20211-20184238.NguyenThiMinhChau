package entities;

/**
 * 
 * @author Hikaru
 *
 */
public class Order {
	private Cart cart;
	
	public Order(Cart cart) {
		this.cart = cart;
	}
	
	public Cart getCart() {
		return this.cart;
	}
}
