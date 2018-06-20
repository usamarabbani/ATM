import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Represents CustomerAccount object 
 */
public class CustomerAccount {
	private String accNumber; // Account number
	private CashCard card; // Cash card reference
	private Bank bank; // Bank reference
	private String password; // Password of account
	private double balance; // Current balance

	/**
	 * Constructor
	 * @param accNumber - Account number
	 * @param accNumber - Cash card reference
	 * @param bank - Bank reference
	 * @param password - password of account
	 * @param balance - starting balance
	 */
	public CustomerAccount(String accNumber, CashCard card, Bank bank, String password, double balance) {
		this.accNumber = accNumber;
		this.card = card;
		this.bank = bank;
		this.password = password;
		this.balance = balance;
	}

	// Accessors & mutators
	
	public String getPassword() {
		return password;
	}

	public double getBalance() {
		return balance;
	}

	public String getAccNumber() {
		return accNumber;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public CashCard getCard() {
		return card;
	}

	public void setCard(CashCard card) {
		this.card = card;
	}
}
