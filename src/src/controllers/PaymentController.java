package controllers;

import boundaries.InterBankBoundary;
import entities.CreditCard;
import entities.Invoice;
import entities.Order;
import entities.transactions.Transaction;
import exceptions.aims.AIMSException;
import exceptions.aims.PlaceOrderException;
import exceptions.interbank.InterbankUndefinedException;
import exceptions.interbank.InternalServerException;
import exceptions.interbank.NotEnoughBalanceException;
import interfaces.InterbankSubsystemInterface;
import utils.Configs;
import utils.DBUtils;

/**
 * 
 * @author Hikaru
 *
 */
public class PaymentController extends AIMSBaseController {
	private Transaction transaction;
	private Order order;
	private Invoice invoice;
	private InterbankSubsystemInterface interbankSubsystem;
	
	/**
	 * creates a controller for handling payments
	 */
	public PaymentController(Order order, CreditCard card, double amount, String content) {
		this.createTransaction(card, amount, content);
		this.order = order;
		this.interbankSubsystem = new InterBankBoundary();
	}
	
	/**
	 * calls the interbank subsystems for performing transaction
	 * @throws PlaceOrderException indicates an error occurs during the placing order process
	 * @throws InterbankUndefinedException 
	 * @throws NotEnoughBalanceException 
	 * @throws InternalServerException 
	 */
	public void performTransaction() throws InternalServerException, NotEnoughBalanceException, InterbankUndefinedException {
		// call interface to perform transaction
		if(this.validatePaymentInformation()) {
			this.interbankSubsystem.payOrder(transaction);			
		}
		this.createInvoice();
		this.saveInvoice();
	}
	
	/**
	 * save invoice to the database
	 */
	private void saveInvoice() {
		// save invoice into database
		DBUtils.saveInvoice(this.invoice);
	}
	
	/**
	 * create a transaction 
	 */
	private void createTransaction(CreditCard card, double amt, String content) {
		this.transaction = new Transaction(DBUtils.getNewTransactionID(), card, amt, content );
	}
	
	/**
	 * create an invoice for sending to customers and save to database
	 */
	private void createInvoice() {
		this.invoice = new Invoice(this.transaction, this.order);
	}
	
	/**
	 * check payment information before performing transaction
	 * @throws AIMSException
	 */
	
	private boolean validatePaymentInformation() {
		if(this.transaction.getCreditCard().getCardNumber().compareTo(Configs.CREDIT_CARD_CODE) != 0) {
			return false;
		}
		
		if(this.transaction.getCreditCard().getSecurityCode().compareTo(Configs.SECURITY_CODE) != 0) {
			return false;
		}
		
		return true;
	}
}
