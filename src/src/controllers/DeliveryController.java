package controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	public boolean validatePhoneNumber(String phoneNumber) {
		if(phoneNumber == null) return false;
		
		if(phoneNumber.length() != 10) {
			return false;
		}
		
		if(!phoneNumber.startsWith("0")) {
			return false;
		}
		
		try {
			Integer.parseInt(phoneNumber);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean validateName(String name) {
		if(name == null) return false;
		if(name.isEmpty()) return false;
		if(name.isBlank()) return false;
		
		Pattern p = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(name);
		if(m.find()) {
			return false;
		}
		return true;
	}
	
	public boolean validateAddress(String address) {
		if(address == null) return false;
		if(address.isEmpty()) return false;
		if(address.isBlank()) return false;
		Pattern p = Pattern.compile("[^a-z0-9 /]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(address);
		if(m.find()) {
			return false;
		}
		return true;
	}
}
