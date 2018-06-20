import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a Bank object
 */
public class Bank {
	private String bankId; // Bank ID
	private List<CustomerAccount> accounts; // List of current bank accounts

	/**
	 * Constructor
	 * @param bankId - Bank ID
	 */
	public Bank(String bankId) {
		this.bankId = bankId;
		this.accounts = new ArrayList<>();
	}
	
	/**
	 * Finds CustomerAccount in list
	 * @param account - CustomerAccount object to be found
	 * @return CustomerAccount object or null if not found
	 */
	private CustomerAccount findAccount(CustomerAccount account) {
		for (CustomerAccount customerAccount : accounts) {
			if (customerAccount.getAccNumber().equals(account.getAccNumber()))
				return customerAccount;
		}
		return null;
	}

	/**
	 * Tries to withdraw cash decreasing balance
	 * @param amount - cash to withdraw
	 * @return 0.00 or positive balance if transaction is success, negative number otherwise
	 */
	public double withdraw(CustomerAccount account, double amount) {
		CustomerAccount acc = findAccount(account); 
		double balance = acc.getBalance() - amount;
		if (balance >= 0)
			acc.setBalance(balance);
		return balance;
	}
	
	/**
	 * Adds account to current bank
	 * @param account - account to be added
	 */
	public void addAccount(CustomerAccount account) {
		accounts.add(account);
	}
	
	/**
	 * Checks if password is valid
	 * @param account - account to be checked
	 * @param password - password to be compared with account password
	 * @return true if password is valid, false otherwise
	 */
	public boolean isPasswordValid(CustomerAccount account, String password) {
		return password.equals(account.getPassword());
	}
	
	@Override
	public String toString() {
		String res = "Bankof" + bankId + " (" + accounts.size() + " customers)\n";
		for (CustomerAccount customerAccount : accounts) {
			res += "Customer with Cash Card (bankid: " + bankId + ", account number #: " +
					customerAccount.getAccNumber() + "), expires on " + customerAccount.getCard().getExpDate() +
					", password: " + customerAccount.getPassword() + "\n";
		}
		return res;
	}

	// Accessors
	
	public String getBankId() {
		return bankId;
	}

	public List<CustomerAccount> getAccounts() {
		return accounts;
	}
}
