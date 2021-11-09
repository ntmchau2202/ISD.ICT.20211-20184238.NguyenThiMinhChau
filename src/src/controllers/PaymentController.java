package controllers;

import entities.transactions.Transaction;
import exceptions.aims.AIMSException;
import exceptions.aims.PlaceOrderException;
import interfaces.InterbankSubsystemInterface;

/**
 * 
 * @author Hikaru
 *
 */
public class PaymentController extends AIMSBaseController {
	private Transaction transaction;
	private InterbankSubsystemInterface interbankSubsystem;
	
	/**
	 * creates a controller for handling payments
	 */
	public PaymentController() {
		
	}
	
	/**
	 * calls the interbank subsystems for performing transaction
	 * @throws PlaceOrderException indicates an error occurs during the placing order process
	 */
	public void performTransaction() throws PlaceOrderException {
		
	}
	
	/**
	 * save invoice to the database
	 */
	private void saveInvoice() {
		
	}
	
	/**
	 * create a transaction 
	 */
	private void createTransaction() {
		
	}
	
	/**
	 * create an invoice for sending to customers and save to database
	 */
	private void createInvoice() {
		
	}
	
	/**
	 * check payment information before performing transaction
	 * @throws AIMSException
	 */
	
	private void validatePaymentInformation() throws AIMSException {
		
	}
}
