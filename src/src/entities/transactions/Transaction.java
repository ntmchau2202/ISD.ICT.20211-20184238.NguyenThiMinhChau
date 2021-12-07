package entities.transactions;

import entities.CreditCard;

/**
 * 
 * @author Hikaru
 *
 */
public class Transaction {
	private int transactionID;
	private CreditCard creditCard;
	private double amount;
//	private TimeStamp transactionTime;
	private String content;
	private String errors;
	
	public Transaction(int transactionID, CreditCard card, double amt, String content) {
		this.transactionID = transactionID;
		this.amount = amt;
		this.content = content;
		this.creditCard = card;
	}

	public int getTransactionID() {
		return transactionID;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public double getAmount() {
		return amount;
	}

	public String getContent() {
		return content;
	}

	public String getErrors() {
		return errors;
	}
	
	
}
