package controllers;

import entities.CreditCard;
import entities.transactions.PaymentTransaction;
import entities.transactions.RefundTransaction;
import exceptions.interbank.InterbankException;
import exceptions.interbank.InterbankUndefinedException;
import exceptions.interbank.InternalServerException;
import exceptions.interbank.InvalidCardException;
import exceptions.interbank.NotEnoughBalanceException;

/**
 * 
 * @author Hikaru
 *
 */
public class InterbankSubsystemController {
	
	/**
	 * create a controller for interbank subsystem
	 */
	public InterbankSubsystemController() {
		
	}
	
	/**
	 * handle response received from the bank
	 * @throws InterbankException indicates and error occurs on the bank side
	 */
	public void handleResponse() throws InterbankException {
		
	}
	
	/**
	 * perform payment transaction
	 * @param payOrderTransaction information about the payment transaction
	 * @throws InternalServerException if the bank returns error or there is errors from from the interbank subsystem
	 * @throws NotEnoughBalanceException if there is not enough balance for paying for transaction
	 * @throws InterbankUndefinedException if there is an undefined error occurs during the transaction
	 */
	public void perform(PaymentTransaction payOrderTransaction) throws InternalServerException, NotEnoughBalanceException, InterbankUndefinedException {
		
	}

	/**
	 * perform refund transaction
	 * @param refundTransaction information about the refund transaction
	 * @throws InternalServerException if the bank returns error or there is errors from from the interbank subsystem
	 * @throws InterbankUndefinedException if there is an undefined error occurs during the transaction
	 */
	public void perform(RefundTransaction refundTransaction) throws InternalServerException, InterbankUndefinedException {
		
	}
	
	/**
	 * query information about the account associated to the credit card
	 * @param creditCard information about the credit card
	 * @throws InvalidCardException if there is no information associated to the credit card, or the information is not valid
	 * @throws InterbankUndefinedException if there is an undefined error occurs during the transaction
	 */
	public void query(CreditCard creditCard) throws InvalidCardException, InterbankUndefinedException {
		
	}
	
}
