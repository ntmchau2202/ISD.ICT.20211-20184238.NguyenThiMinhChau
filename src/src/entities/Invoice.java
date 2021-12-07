package entities;

import entities.transactions.Transaction;

/**
 * 
 * @author Hikaru
 *
 */
public class Invoice {
	private Transaction transaction;
	private Order order;
	
	public Invoice(Transaction transaction, Order order) {
		this.order = order;
		this.transaction = transaction;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public Order getOrder() {
		return order;
	}

	
}

