package entities;

import exceptions.aims.InvalidDeliveryException;
import utils.FunctionalUtils;

/**
 * 
 * @author Hikaru
 *
 */
public class Customer {
	private String customerName, city, district, address, phone;
	
	public Customer(String customerName, String city, String district, String address, String phone) throws InvalidDeliveryException {
		this.setAddress(address);
		this.setCity(city);
		this.setDistrict(district);
		this.setAddress(address);
		this.setCustomerName(customerName);
		this.setPhone(phone);
	}
	
	public Customer(String customerName, String phone, String address) throws InvalidDeliveryException {
		this.setAddress(address);
		this.setCustomerName(customerName);
		this.setPhone(phone);
	}
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) throws InvalidDeliveryException {
		if(FunctionalUtils.contains(customerName, "[^A-Za-z ]")) {
			throw new InvalidDeliveryException("customer name must only contain letters and spaces");
		}
		this.customerName = customerName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) throws InvalidDeliveryException {
		if(FunctionalUtils.contains(city, "[^A-Za-z ]")) {
			throw new InvalidDeliveryException("city name must only contain letters and spaces");
		}
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) throws InvalidDeliveryException {
		if(FunctionalUtils.contains(city, "[^A-Za-z0-9 ]")) {
			throw new InvalidDeliveryException("district name must only contain letters, spaces and numbers");
		}
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) throws InvalidDeliveryException {
		if(FunctionalUtils.contains(address, "[^A-Za-z0-9 /,]")) {
			throw new InvalidDeliveryException("address must only contain letters, numbers, spaces, slash and comma");
		}
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) throws InvalidDeliveryException {
		if(FunctionalUtils.contains(phone, "[0-9]")) {
			throw new InvalidDeliveryException("phone number digits");
		}
		this.phone = phone;
	} 
}
