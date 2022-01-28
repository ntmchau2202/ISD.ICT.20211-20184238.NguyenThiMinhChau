package entities;

import exceptions.aims.InvalidDeliveryException;
import utils.FunctionalUtils;

/**
 * 
 * @author Hikaru
 *
 */
public class Customer {
	/**
	 * 
	 */
	private int customerID;
	private String customerName;
	
	private String address;
	
	private String city;
	/**
	 * 
	 */
	private String phone;

	public Customer(String customerName, String phone, String city, String address) throws InvalidDeliveryException {
		this.setAddress(address);
		this.setCustomerName(customerName);
		this.setPhone(phone);
		this.setCity(city);
	}
	
	public void setCustomerID(int id) {
		this.customerID = id;
	}
	
	public int getCustomerID() {
		return this.customerID;
	}
	
	private void setCity(String city) {
		this.city = city;
	}
	
	public String getCity() {
		return this.city;
	}
	/**
	 * 
	 * @return
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * 
	 * @param customerName
	 * @throws InvalidDeliveryException
	 */
	public void setCustomerName(String customerName) throws InvalidDeliveryException {
		if(!FunctionalUtils.contains(customerName, "^[A-Za-z ]")) {
			throw new InvalidDeliveryException("customer name must only contain letters and spaces");
		}
		this.customerName = customerName;
	}
	/**
	 * 
	 * @return
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 
	 * @param address
	 * @throws InvalidDeliveryException
	 */
	public void setAddress(String address) throws InvalidDeliveryException {
		if(!FunctionalUtils.contains(address, "^[A-Za-z0-9 /,]")) {
			throw new InvalidDeliveryException("address must only contain letters, numbers, spaces, slash and comma");
		}
		this.address = address;
	}
	/**
	 * 
	 * @return
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 
	 * @param phone
	 * @throws InvalidDeliveryException
	 */
	public void setPhone(String phone) throws InvalidDeliveryException {
		if(!FunctionalUtils.contains(phone, "^[0-9]")) {
			throw new InvalidDeliveryException("phone number must be digits");
		}
		this.phone = phone;
	} 
}
