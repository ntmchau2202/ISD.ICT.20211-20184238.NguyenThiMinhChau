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
	private String customerName;
	
	/**
	 * 
	 */
	private String city;
	
	/**
	 * 
	 */
	private String district;
	
	/**
	 * 
	 */
	private String address;
	
	/**
	 * 
	 */
	private String phone;
	/**
	 * 
	 * @param customerName
	 * @param city
	 * @param district
	 * @param address
	 * @param phone
	 * @throws InvalidDeliveryException
	 */
	public Customer(String customerName, String city, String district, String address, String phone) throws InvalidDeliveryException {
		this.setAddress(address);
		this.setCity(city);
		this.setDistrict(district);
		this.setAddress(address);
		this.setCustomerName(customerName);
		this.setPhone(phone);
	}
	/**
	 * 
	 * @param customerName
	 * @param phone
	 * @param address
	 * @throws InvalidDeliveryException
	 */
	public Customer(String customerName, String phone, String address) throws InvalidDeliveryException {
		this.setAddress(address);
		this.setCustomerName(customerName);
		this.setPhone(phone);
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
		if(FunctionalUtils.contains(customerName, "[^A-Za-z ]")) {
			throw new InvalidDeliveryException("customer name must only contain letters and spaces");
		}
		this.customerName = customerName;
	}
	/**
	 * 
	 * @return
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 
	 * @param city
	 * @throws InvalidDeliveryException
	 */
	public void setCity(String city) throws InvalidDeliveryException {
		if(FunctionalUtils.contains(city, "[^A-Za-z ]")) {
			throw new InvalidDeliveryException("city name must only contain letters and spaces");
		}
		this.city = city;
	}
	/**
	 * 
	 * @return
	 */
	public String getDistrict() {
		return district;
	}
	/**
	 * 
	 * @param district
	 * @throws InvalidDeliveryException
	 */
	public void setDistrict(String district) throws InvalidDeliveryException {
		if(FunctionalUtils.contains(city, "[^A-Za-z0-9 ]")) {
			throw new InvalidDeliveryException("district name must only contain letters, spaces and numbers");
		}
		this.district = district;
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
		if(FunctionalUtils.contains(address, "[^A-Za-z0-9 /,]")) {
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
		if(FunctionalUtils.contains(phone, "[0-9]")) {
			throw new InvalidDeliveryException("phone number digits");
		}
		this.phone = phone;
	} 
}
