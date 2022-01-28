package entities;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import entities.medias.Media;
import exceptions.aims.PlaceOrderException;

public abstract class Order {
	public abstract boolean isRushed();
	static int orderIDCount = 0;
	int orderID;
	
	/**
	 * 
	 */
	HashMap<Media, Integer> listItemOrdered;
	double amount;
	/**
	 * 
	 */
	Customer customer;
	/**
	 * 
	 * @param cart
	 */
	Date dateIssued;
	String instruction;
	double vat;
	double total;
	double shippingFee;
	
	public double getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(double shippingFee) {
		this.shippingFee = shippingFee;
	}

	public double getVat() {
		return vat;
	}

	public void setVat(double vat) {
		this.vat = vat;
	}

	public double getTotal() {
		total = vat + shippingFee + amount;
		return total;
	}


	
	public void setOrderID(int id) {
		this.orderID = id;
	}
	
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	
	public String getInstruction() {
		return this.instruction;
	}
	
	public void addItemToOrder(Media m, int quan) {
		this.listItemOrdered.put(m, quan);
		this.updateAmount();
	}
	
	public void removeItemFromOrder(Media m) {
		if(this.listItemOrdered.containsKey(m)) {
			this.listItemOrdered.remove(m);
		}
		this.updateAmount();
	}
	/**
	 * 
	 * @return
	 */
	
	public int getOrderID() {
		return this.orderID;
	}
	
	public String getDateIssued() {
		return this.dateIssued.toString();
	}

	/**
	 * 
	 * @param customer
	 * @throws PlaceOrderException
	 */
	public void updateCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
	
	/**
	 * 
	 * @param item
	 * @param newQuantity
	 * @throws PlaceOrderException
	 */
	public void adjustOrder(Media item, int newQuantity) throws PlaceOrderException {
		if (this.listItemOrdered.containsKey(item)) {
			this.listItemOrdered.replace(item, newQuantity);			
		} else {
			this.addItemToOrder(item, newQuantity);
		}
		this.updateAmount();
	}
	
	void updateAmount() {
		this.amount = 0;
		Iterator<Entry<Media, Integer>> it = this.listItemOrdered.entrySet().iterator();
		while(it.hasNext()) {
			Entry<Media,Integer> entry = (Entry<Media, Integer>)it.next();
			this.amount += entry.getKey().getPrice() * entry.getValue();
		}
	}
	
	public double getAmount() {
		return this.amount;
	}
	
	public HashMap<Media, Integer> getListItemOrdered() {
		return this.listItemOrdered;
	}
	
	public Order() {
		this.listItemOrdered = new HashMap<Media, Integer>();
		this.dateIssued = Calendar.getInstance().getTime();
	}
	
	public Order(HashMap<Media,Integer> listItem) {
		this.listItemOrdered = listItem;
		this.dateIssued = Calendar.getInstance().getTime();
		this.updateAmount();
	}

}
