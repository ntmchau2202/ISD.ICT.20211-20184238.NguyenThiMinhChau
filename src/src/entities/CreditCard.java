package entities;

/**
 * 
 * @author Hikaru
 *
 */
public class CreditCard {
	private String cardNumber;
	/**
	 * 
	 */
	private String cardHolderName;
	/**
	 * 
	 */
	private double balance;
	/**
	 * 
	 */
	private String issuingBank;
//	private TimeStamp expirationDate;
	/**
	 * 
	 */
	private String securityCode;
	
	/**
	 * 
	 * @param cardNumber
	 * @param cardholderName
	 * @param balance
	 * @param issuingBank
	 * @param securityCode
	 */
	public CreditCard(String cardNumber, String cardholderName, double balance, String issuingBank, String securityCode) {
		this.cardNumber = cardNumber;
		this.cardHolderName = cardholderName;
		this.balance = balance;
		this.issuingBank = issuingBank;
		this.securityCode = securityCode;
	}
	/**
	 * 
	 * @return
	 */
	public String getCardNumber() {
		return cardNumber;
	}
	/**
	 * 
	 * @return
	 */
	public String getCardHolderName() {
		return cardHolderName;
	}
	/**
	 * 
	 * @return
	 */
	public double getBalance() {
		return balance;
	}
	/**
	 * 
	 * @return
	 */
	public String getIssuingBank() {
		return issuingBank;
	}
	/**
	 * 
	 * @return
	 */
	public String getSecurityCode() {
		return securityCode;
	}
	/**
	 * 
	 * @param amount
	 */
	public void changeBalance(double amount) {
		this.balance += amount; // need a better mechanism for this
	}
	
}
