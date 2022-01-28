package boundaries;

import entities.Cart;
import exceptions.aims.PlaceOrderException;
import interfaces.PlaceOrderSubsystemInterface;

/**
 * 
 * PlaceOrderBoundary
 *
 */
public class PlaceOrderBoundary implements PlaceOrderSubsystemInterface {

	@Override
	public void placeOrder(Cart cart) throws PlaceOrderException {
		// TODO: do st here to pass the cart to the placeordersubsystem
		
	}

	@Override
	public void placeRushOrder(Cart cart) throws PlaceOrderException {
		// TODO Auto-generated method stub
		
	}

}
