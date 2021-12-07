package entities;

/**
 * 
 * @author Hikaru
 *
 */
public class CreditCard {
	private String cardNumber;
	private String cardHolderName;
	private double balance;
	private String issuingBank;
//	private TimeStamp expirationDate;
	private String securityCode;
	
	public CreditCard(String cardNumber, String cardholderName, double balance, String issuingBank, String securityCode) {
		this.cardNumber = cardNumber;
		this.cardHolderName = cardholderName;
		this.balance = balance;
		this.issuingBank = issuingBank;
		this.securityCode = securityCode;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public double getBalance() {
		return balance;
	}

	public String getIssuingBank() {
		return issuingBank;
	}

	public String getSecurityCode() {
		return securityCode;
	}
	
	public void changeBalance(double amount) {
		this.balance += amount; // need a better mechanism for this
	}
	
}
