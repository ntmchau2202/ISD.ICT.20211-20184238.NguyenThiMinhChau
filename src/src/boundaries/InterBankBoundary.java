package boundaries;

import controllers.InterbankController;
import entities.CreditCard;
import entities.transactions.Transaction;
import exceptions.interbank.InterbankException;
import exceptions.interbank.InterbankUndefinedException;
import exceptions.interbank.InternalServerException;
import exceptions.interbank.InvalidCardException;
import exceptions.interbank.NotEnoughBalanceException;
import interfaces.InterbankSubsystemInterface;
import utils.FunctionalUtils;

/**
 * InterBankBoundary
 */
public class InterBankBoundary implements InterbankSubsystemInterface {
	
	private InterbankController interbankController;
	
	public InterBankBoundary(String bankName) {
		// factory pattern here to create interbankcontroller;
		interbankController = new InterbankController(bankName);
	}

	@Override
	public void payOrder(Transaction transaction)
			throws InterbankException {
		validateTransaction(transaction);
		this.interbankController.payOrder(transaction);
		
	}

	@Override
	public void refund(Transaction transaction) throws InterbankException {
		validateTransaction(transaction);
		this.interbankController.refund(transaction);
	}

	@Override
	public double getBalance(CreditCard creditCard) throws InterbankException {
		return this.interbankController.getBalance(creditCard);
	}
	
	private void validateTransaction(Transaction trans) throws InterbankException {
		validateCardHolderName(trans.getCreditCard().getCardHolderName());
		validateCardNumber(trans.getCreditCard().getCardNumber());
		validateSecurityCode(trans.getCreditCard().getSecurityCode());
		validateExpirationDate(trans.getCreditCard().getExpirationDate());
	}
	
	private void validateCardHolderName(String name) throws InvalidCardException{
		if(!FunctionalUtils.contains(name, "^[a-zA-Z ]")) {
			throw new InvalidCardException("Invalid card holder name");
		}
	}
	
	private void validateCardNumber(String number) throws InvalidCardException {
		if(!FunctionalUtils.contains(number, "^[a-zA-Z -_]")) {
			throw new InvalidCardException("Invald credit card number");
		}
	}
	
	private void validateSecurityCode(String code) throws InvalidCardException {
		if(code.length() != 3) {
			System.out.println("Interbank found err:"+code);
			throw new InvalidCardException("Invalid security code");
		}
	}
	
	private void validateExpirationDate(String date) throws InvalidCardException {
		// do st here
	}

}
