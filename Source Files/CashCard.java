import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a cash card object
 */
public class CashCard {
	private String cardNumber; // Card number
	private LocalDate expDate; // Expiration date
	private CustomerAccount account; // Account linked with this card

	/**
	 * Constructor
	 * @param cardNumber - card number
	 * @param expDate - expiration date
	 * @param account - account linked with this card
	 */
	public CashCard(String cardNumber, LocalDate expDate, CustomerAccount account) {
		this.cardNumber = cardNumber;
		this.expDate = expDate;
		this.account = account;
	}

	// Accessors

	public LocalDate getExpDate() {
		return expDate;
	}

	public CustomerAccount getAccount() {
		return account;
	}

	public String getCardNumber() {
		return cardNumber;
	}
}
