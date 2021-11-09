package controllers;

import entities.Customer;
import exceptions.aims.AIMSException;
import exceptions.aims.InvalidDeliveryException;

/**
 * 
 * @author Hikaru
 *
 */
public class DeliveryController extends AIMSBaseController {
	private Customer customerInf;
	
	/**
	 * create a delivery controller for handling delivery information received from customer
	 */
	public DeliveryController() {
		
	}
	
	/**
	 * modify delivery information previously inputed
	 * @param newCustomerInformation the information of the customer
	 * @throws AIMSException indicates and errors inside the AIMS application
	 */
	public void updateDeliveryInformation(Customer newCustomerInformation) throws AIMSException {
		
	}
	
	/**
	 * check validity of input delivery information from customer
	 * @throws InvalidDeliveryException indicate invalid delivery information
	 */
	public void validateDeliveryInformation() throws InvalidDeliveryException {
		
	}
}
