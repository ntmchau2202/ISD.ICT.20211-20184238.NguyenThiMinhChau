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
	private Order order;
	/**
	 * 
	 * @param transaction
	 * @param order
	 */
	public Invoice(Transaction transaction, Order order) {
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
	public Order getOrder() {
		return order;
	}

	
}

