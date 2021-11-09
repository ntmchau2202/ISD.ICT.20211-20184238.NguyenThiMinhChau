package interfaces;

import entities.CreditCard;
import entities.transactions.Transaction;
import exceptions.interbank.InterbankUndefinedException;
import exceptions.interbank.InternalServerException;
import exceptions.interbank.NotEnoughBalanceException;

/**
 * This interface is used to connect with different interbank system <br>
 * It separate the main of AIMS, the place order logics with the behaviors of the interbank for flexibility in changing the system
 * @author chauntm
 *
 */
public interface InterbankSubsystemInterface {
	/**
	 * perform paying order with given transaction details
	 * @param transaction details about the pay order transaction
	 * @throws InternalServerException if there is an error from the bank
	 * @throws NotEnoughBalanceException if there is not enough balance to perform the transaction
	 * @throws InterbankUndefinedException if there is any undefined errors during the transaction
	 */
	public void payOrder(Transaction transaction) throws InternalServerException, NotEnoughBalanceException, InterbankUndefinedException;
	
	/**
	 * perform refund with given transaction details
	 * @param transaction details about the refund transaction
	 * @throws InternalServerException if there is an error from the bank
	 * @throws InterbankUndefinedException if there is any undefined errors during the transaction
	 */
	public void refund(Transaction transaction) throws InternalServerException, InterbankUndefinedException;
	
	/**
	 * get current balance in the given credit card
	 * @param creditCard information of the credit card need to query balance
	 * @return current balance in the given credit card
	 * @throws InternalServerException if there is an error from the bank
	 * @throws InterbankUndefinedException if there is any undefined errors during the transaction
	 */
	public double getBalance(CreditCard creditCard) throws InternalServerException, InterbankUndefinedException;
}
