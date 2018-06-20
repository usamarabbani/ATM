import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents ATM network system
 */
public class ATMSystem {
	private final double PRELOADED_AMOUNT = 40; // Initial balance for all accounts
	
	private List<Bank> banks; // List of all banks
	private List<ATM> atms; // List of all ATMs
	private List<CashCard> cashCards; // List of all cash cards

	/**
	 * Constructor
	 */
	public ATMSystem() {
		banks = new ArrayList<>();
		atms = new ArrayList<>();
		cashCards = new ArrayList<>();

		// Initialize data
		initialize();
		// Print initial information
		printInformation();
		// Show and process interaction with user
		doMenu();
	}

	/**
	 * Initializes starting data (banks, ATMs, accounts, cash cards)
	 */
	private void initialize() {
		// Create bank A
		Bank bankofA = new Bank("A");
		banks.add(bankofA);
		
		// Add 2 customers to bank A
		CustomerAccount account11 = new CustomerAccount("11", null, bankofA, "mypassword", PRELOADED_AMOUNT);
		bankofA.addAccount(account11);
		CustomerAccount account12 = new CustomerAccount("12", null, bankofA, "mypassword", PRELOADED_AMOUNT);
		bankofA.addAccount(account12);
		
		// Associate cash cards with accounts
		CashCard card_A11 = new CashCard("A11", LocalDate.of(2015, 1, 1), account11);
		CashCard card_A12 = new CashCard("A12", LocalDate.of(2015, 10, 15), account12);
		account11.setCard(card_A11);
		account12.setCard(card_A12);
		
		// Add cash cards to list
		cashCards.add(card_A11);
		cashCards.add(card_A12);
		
		// Add 2 ATMs (bank A) to list
		ATM atm_A1 = new ATM("ATM_A1", bankofA, 50);
		atms.add(atm_A1);
		ATM atm_A2 = new ATM("ATM_A2", bankofA, 50);
		atms.add(atm_A2);

		// Create bank B
		Bank bankofB = new Bank("B");
		banks.add(bankofB);
		
		// Add 3 customers to bank B
		CustomerAccount account111 = new CustomerAccount("111", null, bankofB, "mypassword", PRELOADED_AMOUNT);
		bankofB.addAccount(account111);
		CustomerAccount account122 = new CustomerAccount("122", null, bankofB, "mypassword", PRELOADED_AMOUNT);
		bankofB.addAccount(account122);
		CustomerAccount account133 = new CustomerAccount("133", null, bankofB, "mypassword", PRELOADED_AMOUNT);
		bankofB.addAccount(account133);
		
		// Associate cash cards with accounts
		CashCard card_B111 = new CashCard("B111", LocalDate.of(2018, 2, 6), account111);
		CashCard card_B122 = new CashCard("B122", LocalDate.of(2013, 4, 19), account122);
		CashCard card_B133 = new CashCard("B133", LocalDate.of(2014, 3, 5), account133);
		account111.setCard(card_B111);
		account122.setCard(card_B122);
		account133.setCard(card_B133);
		
		// Add cash cards to list
		cashCards.add(card_B111);
		cashCards.add(card_B122);
		cashCards.add(card_B133);
		
		// Add 2 ATMs (bank B) to list
		ATM atm_B1 = new ATM("ATM_B1", bankofB, 50);
		atms.add(atm_B1);
		ATM atm_B2 = new ATM("ATM_B2", bankofB, 50);
		atms.add(atm_B2);
	}

	/**
	 * Prints starting information about banks, customer accounts and ATMs
	 */
	private void printInformation() {
		System.out.println("States of two Banks\n\n"
				+ "Assume all accounts have $" + PRELOADED_AMOUNT + " preloaded.");
		for (Bank bank : banks) {
			System.out.println(bank);
		}
		
		System.out.println("States of four ATMs (2 for each Bank)");
		for (Bank bank : banks) {
			int atmCounter = 0;
			for (ATM atm : atms) {
				if (atm.getBank().equals(bank)) {
					System.out.println(atm.getAtmNumber() + ": (ATM" + (++atmCounter) + 
							" from Bankof" + bank.getBankId() + ")");
					System.out.println("\tThe maximum amount of cash a card can widthraw per day: $" + atm.getCashLimit());
				}
			}
		}
		System.out.println("\n------------------------------------------------------------------------------");
		System.out.println("Now, your program is in an interactive mode. % means the prompt on your cmd (shell).\n");
	}
	
	/**
	 * Processing interactive menu
	 */
	private void doMenu() {
		Scanner kb = new Scanner(System.in);

		ATM atm; // Current ATM
		System.out.println("Enter your choice of ATM");
		atm = findATM(kb.nextLine()); 
		if (atm == null) {
			System.out.println("ATM does not exist!");
			System.exit(-1); // Program exits
		}

		CashCard card; // Current cash card
		boolean cardAccepted = false;
		// Ask user to enter card number while the correct number is entered
		do {
			System.out.println("Enter your card");
			card = findCashCard(kb.nextLine());
			switch (atm.checkCardIsValid(card)) {
			case 0:
				System.out.print("The card is accepted.");
				cardAccepted = true;
				break;
			case -1:
				System.out.println("This card is expired and returned to you.");
				break;
			case -2:
				System.out.println("This card is not supported by this ATM");
				break;
			}
		} while(!cardAccepted);

		// Ask user to enter password while correct password is entered
		System.out.println(" Please enter your password.");
		boolean authorized = false;
		do {
			String password = kb.nextLine();
			if (atm.getBank().isPasswordValid(card.getAccount(), password)) {
				System.out.print("Authorization is accepted.");
				authorized = true;
			}
			else
				System.out.println("This is a wrong password. Enter your password.");
		} while (!authorized);

		// Ask user to enter amount or quit command
		System.out.println(" Start your transaction by entering the amount to withdraw.");
		String command;
		do {
			command = kb.nextLine();
			if (command.equalsIgnoreCase("quit"))
				break;
			double amount = Double.parseDouble(command);
			// Check ATM limit
			if (atm.checkATMLimit(amount)) {
				double balance = atm.getBank().withdraw(card.getAccount(), amount);
				if (balance < 0)
					System.out.println("The amount exceeds the current balance. Enter another amount or quit.");
				else
					System.out.println("$" + amount + " is withdrawn from your account."
							+ " The remaining balance of this account is $" + balance
							+ ".  If you have more transactions, enter the amount or quit.");
			} else
				System.out.println("This amount exceeds the maximum amount you can withdraw per transaction."
						+ " Please enter the amount or quit.");
		} while (true);
		kb.close();
	}

	/**
	 * Finds ATM in list by its number
	 * @param atmNumber - the number of ATM
	 * @return ATM object or null if not found
	 */
	private ATM findATM(String atmNumber) {
		for (ATM atm : atms) {
			if (atm.getAtmNumber().equals(atmNumber))
				return atm;
		}
		return null;
	}

	/**
	 * Finds cash card in list by its number
	 * @param cardNumber - the number of cash card
	 * @return CashCard object or null if not found
	 */
	private CashCard findCashCard(String cardNumber) {
		for (CashCard cashCard : cashCards) {
			if (cashCard.getCardNumber().equals(cardNumber))
				return cashCard;
		}
		return null;
	}

	/**
	 * Main method
	 */
	public static void main(String[] args) {
		new ATMSystem();
	}
}
