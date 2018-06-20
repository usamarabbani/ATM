import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Represents an ATM object
 */
public class ATM {
	private String atmNumber; // ATM number
	private Bank bank; // Bank linked with this ATM
	private double cashLimit; // The maximum amount of cash a customer can withdraw per transaction
	
	/**
	 * Constructor
	 * @param atmNumber - ATM number
	 * @param bank - bank linked with this ATM
	 * @param cashLimit - the maximum amount of cash a customer can withdraw per transaction
	 */
	public ATM(String atmNumber, Bank bank, double cashLimit) {
		this.atmNumber = atmNumber;
		this.bank = bank;
		this.cashLimit = cashLimit;
	}

	/**
	 * Checks if card is valid
	 * A card is valid if it is not expired and its bank id is correct for the bank associated with the ATM.
	 * @param card
	 * @return 0 if card is valid,
	 * 		  -1 if date is expired,
	 * 		  -2 if card is not supported by this ATM
	 */
	public int checkCardIsValid(CashCard card) {
		if (card == null)
			return -2;
		if (card.getExpDate().isBefore(LocalDate.now()))
			return -1;
		if (!card.getAccount().getBank().getBankId().equals(bank.getBankId()))
			return -2;
		return 0;
	}

	/**
	 * Checks for cash limit in this ATM
	 * @param amount - value to be compared with cash limit
	 * @return true if limit is not exceeded, false otherwise
	 */
	public boolean checkATMLimit(double amount) {
		return amount <= cashLimit; 
	}

	// Accessors

	public String getAtmNumber() {
		return atmNumber;
	}

	public double getCashLimit() {
		return cashLimit;
	}

	public Bank getBank() {
		return bank;
	}
}
