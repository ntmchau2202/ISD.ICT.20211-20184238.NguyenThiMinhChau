package views;

import controllers.AIMSBaseController;

/**
 * 
 * @author Hikaru
 *
 */
public class PaymentScreenHandler extends AIMSBaseScreenHandler  {

	/***
	 * Initialize the Payment method screen 
	 * @param prevScreen a handler to the screen that called this screen
	 */
	protected PaymentScreenHandler(AIMSBaseScreenHandler prevScreen) {
		super(prevScreen);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Allow customer to update the payment information
	 */
	public void updatePaymentMethod() {
		
	}
	
	/**
	 * Submit the customer payment information to the controller
	 */
	public void submitPaymentForm() {
		
	}
	
	/**
	 * Sends the payment information of customers to controller or reject the payment information 
	 * @param confirmation confirmation of customer <br>
	 * if confirmation = true, send the payment information to controller <br>
	 * if confirmation = false, reject the payment information
	 */
	public void confirmPayment(boolean confirmation) {
		
	}
	
	/**
	 * Clear the payment information form
	 */
	private void clearPaymentForm() {
		
	}
	
	/**
	 * check the payment form for invalid input before sending to the controller
	 */
	private void validatePaymentForm() {
		
	}

}
