package controllers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import boundaries.InterBankBoundary;
import entities.CreditCard;
import entities.Customer;
import entities.NormalOrder;
import entities.Order;
import entities.medias.Media;
import entities.strategies.OrderFeeCalculatorFactory;
import entities.transactions.Transaction;
import exceptions.aims.InvalidDeliveryException;
import exceptions.aims.InvalidMediaInfoException;
import exceptions.aims.MediaUpdateException;
import exceptions.aims.PlaceOrderException;
import exceptions.interbank.InterbankException;
import interfaces.InterbankSubsystemInterface;
import interfaces.OrderCalculatorInterface;
import utils.DBUtils;
import utils.FunctionalUtils;

public abstract class PlaceOrderController {
	Order order;
	private Transaction transaction;
	static PlaceOrderController placeOrderController;
	PlaceOrderController() throws PlaceOrderException {
		
	}
	
	public static PlaceOrderController getPlaceOrderController() throws PlaceOrderException {
		if(placeOrderController == null) {
			placeOrderController = new PlaceNormalOrderController(null);
		}
		return placeOrderController;
	}
	
	
	public void validateOrderInformation(String name, String phone, String address) throws InvalidDeliveryException {
		validateName(name);
		validatePhone(phone);
		validateAddress(address);		
	}
	
	private void validateName(String name) throws InvalidDeliveryException {
		if (!FunctionalUtils.contains(name, "^[a-zA-Z ]")) {
			throw new InvalidDeliveryException("Invalid name");
		}
	}
	
	private void validatePhone(String phone) throws InvalidDeliveryException {
		if (phone.length() < 9 || phone.length() > 11) {
			throw new InvalidDeliveryException("Phone number must be 9-11 digits in length");
		}
		
		if (!FunctionalUtils.contains(phone, "^[0-9]")) {
			throw new InvalidDeliveryException("Invalid phone number");
		}
	}
	
	private void validateAddress(String address) throws InvalidDeliveryException {
		if (!FunctionalUtils.contains(address, "^[a-zA-z0-9 ,/]")) {
			throw new InvalidDeliveryException("Invalid address");
		}
	}
	
	
	public void validateCard(String cardholderName, String cardNumber, String cvv, String expirationDate) throws PlaceOrderException {
		validateCardHolderName(cardholderName);
		validateCardNumber(cardNumber);
		validateCardSecurityCode(cvv);
		validateExpirationDate(expirationDate);
		this.createTransaction(cardholderName, cardNumber, cvv, expirationDate);
	}
	
	private void validateCardHolderName(String name) throws PlaceOrderException {
		if (!FunctionalUtils.contains(name, "^[a-zA-Z ]")) {
			throw new PlaceOrderException("Invalid cardholder name");
		}
	}
	
	// Invalid security code
	
	private void validateCardNumber(String cardNumber) throws PlaceOrderException {
		if (!FunctionalUtils.contains(cardNumber, "^[a-zA-Z0-9 _-]")) {
			throw new PlaceOrderException("Invalid card number");
		}
	}
	
	private void validateCardSecurityCode(String cvv) throws PlaceOrderException {
		if (cvv.length() != 3) {
			System.out.println("Length cvv not valid:" + cvv);
			throw new PlaceOrderException("Invalid security code");
		}
		
		if (!FunctionalUtils.contains(cvv, "^[0-9]")) {
			System.out.println("invalid cvv:"+cvv);
			throw new PlaceOrderException("Invalid security code");
		}
	}
	
	private void validateExpirationDate(String expirationDate) throws PlaceOrderException {
		// TODO: AIMS: need to handle the dates here
	}
	
	public void addCustomer(String name, String city, String address, String phone) throws InvalidDeliveryException {
		Customer customer = new Customer(name, phone, city, address);
		this.order.updateCustomer(customer);
		OrderFeeCalculatorFactory.getDeliverFeeCalculator(order);
		OrderCalculatorInterface calculator = OrderFeeCalculatorFactory.getDeliverFeeCalculator(order);
		order.setVat(calculator.calculateVAT(order));
		order.setShippingFee(calculator.calculateShippingFee(order));
	}
	
	public void addDeliveryInstruction(String instruction) {
		this.order.setInstruction(instruction);
	}
	
	private Transaction createTransaction(String cardholderName, String cardNumber, String cvv, String expirationDate) {
		CreditCard card = new CreditCard(cardNumber, cardholderName, cvv, expirationDate);
		this.transaction = new Transaction(card, order.getAmount(), Transaction.TransactionType.PAY_ORDER.toString());
		return this.transaction;
	}
	
	public void placeOrder() throws PlaceOrderException, InterbankException, InvalidMediaInfoException, SQLException, MediaUpdateException {
		InterbankSubsystemInterface bank = new InterBankBoundary("ACB");
		bank.payOrder(transaction);
		updateStore();
		updateCart();
		renewOrder();
	}
	
	private void updateStore() throws InvalidMediaInfoException, SQLException {
		// TODO: AIMS: Update database here
		DBUtils.addOrder(order);		
		
		// pick from store -> pass to information controller
		Iterator<Entry<Media, Integer>> it = order.getListItemOrdered().entrySet().iterator();
		while(it.hasNext()) {
			Entry<Media,Integer> entry = (Entry<Media, Integer>)it.next();
			InformationController.getInformationController().pickItemFromStore(entry.getKey(), entry.getValue());
		}
	}
	
	private void updateCart() throws MediaUpdateException {
		Iterator<Entry<Media, Integer>> it = order.getListItemOrdered().entrySet().iterator();
		while(it.hasNext()) {
			Entry<Media,Integer> entry = (Entry<Media, Integer>)it.next();
			CartController.getCartController().removeItemFromCart(entry.getKey());
		}
	}
	
	void renewOrder() {
		this.order = new NormalOrder();
	}
	
	public Order getOrder() {
		return this.order;
	}
}
