package entities;

import entities.transactions.Transaction;

/**
 * 
 * @author Hikaru
 *
 */
public class Invoice {
	/**
	 * 
	 */
	private Transaction transaction;
	/**
	 * 
	 */
	private NormalOrder order;
	/**
	 * 
	 * @param transaction
	 * @param order
	 */
	public Invoice(Transaction transaction, NormalOrder order) {
		this.order = order;
		this.transaction = transaction;
	}
	/**
	 * 
	 * @return
	 */
	public Transaction getTransaction() {
		return transaction;
	}
	/**
	 * 
	 * @return
	 */
	public NormalOrder getOrder() {
		return order;
	}

	
}

