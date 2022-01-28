package entities.transactions;

import entities.CreditCard;

/**
 * 
 * @author Hikaru
 *
 */
public class Transaction {
	public static enum TransactionType {
		PAY_ORDER,
		REFUND
	}
	private int transactionID;
	private CreditCard creditCard;
	private double amount;
//	private TimeStamp transactionTime;
	private String content;
	private String errors;
	/**
	 * 
	 * @param transactionID
	 * @param card
	 * @param amt
	 * @param content
	 */
	public Transaction(CreditCard card, double amt, String content) {
		this.amount = amt;
		this.content = content;
		this.creditCard = card;
	}
	/**
	 * 
	 * @return
	 */
	public int getTransactionID() {
		return transactionID;
	}
	/**
	 * 
	 * @return
	 */
	public CreditCard getCreditCard() {
		return creditCard;
	}
	/**
	 * 
	 * @return
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * 
	 * @return
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 
	 * @return
	 */
	public String getErrors() {
		return errors;
	}
	
	
}
