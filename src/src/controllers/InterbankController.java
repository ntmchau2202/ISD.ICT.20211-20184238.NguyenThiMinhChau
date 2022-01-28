package controllers;

import entities.CreditCard;
import entities.transactions.Transaction;
import exceptions.interbank.InterbankException;
import exceptions.interbank.InterbankUndefinedException;
import exceptions.interbank.InternalServerException;
import exceptions.interbank.InvalidCardException;
import exceptions.interbank.NotEnoughBalanceException;
import interfaces.InterbankSubsystemInterface;
import utils.API;
import utils.Configs;

public class InterbankController {

	private String bankName;
	
	public InterbankController(String bankName) {
		this.bankName = bankName;
	}
	
	public void payOrder(Transaction transaction)
			throws InterbankException {
		// do query to reduce balance here
		// API.post();
		handlingResponseCode(0);
	}

	public void refund(Transaction transaction) throws InterbankException {
		// do st to increase balance here
		// API.post();
		handlingResponseCode(0);
		
	}

	public double getBalance(CreditCard creditCard)
			throws InterbankException {
		return creditCard.getBalance();
	}
	
	
	
	private boolean isEnoughBalance(double balance, double transferAmt) {
		return balance >= transferAmt;
	}

	private void handlingResponseCode(int code) throws InterbankException {
		
	}
}
