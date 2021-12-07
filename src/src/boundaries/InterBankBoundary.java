package boundaries;

import controllers.InterbankSubsystemController;
import entities.CreditCard;
import entities.transactions.Transaction;
import exceptions.interbank.InterbankUndefinedException;
import exceptions.interbank.InternalServerException;
import exceptions.interbank.InvalidCardException;
import exceptions.interbank.NotEnoughBalanceException;
import interfaces.InterbankSubsystemInterface;

/**
 * InterBankBoundary
 */
public class InterBankBoundary implements InterbankSubsystemInterface {
	
	private InterbankSubsystemController interbankController;
	
	public InterBankBoundary() {
		
	}

	@Override
	public void payOrder(Transaction transaction)
			throws InternalServerException, NotEnoughBalanceException, InterbankUndefinedException {
		this.interbankController.perform(transaction);
		
	}

	@Override
	public void refund(Transaction transaction) throws InternalServerException, InterbankUndefinedException {
		this.interbankController.perform(transaction);
	}

	@Override
	public double getBalance(CreditCard creditCard) throws InternalServerException, InterbankUndefinedException, InvalidCardException {
		return this.interbankController.queryBalance(creditCard);
	}

}
