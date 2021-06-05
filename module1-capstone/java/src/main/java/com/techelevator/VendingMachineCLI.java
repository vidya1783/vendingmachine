package com.techelevator;
import com.techelevator.view.Menu;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class VendingMachineCLI {
	//MAIN MENU CONFIG
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };
	//PURCHASE MENU CONFIG
	private static final String PURCHASE_MENU_OPTION_ADD_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_BUY_ITEM = "Select Item";
	private static final String PURCHASE_MENU_OPTION_CASH_OUT = "Finish Transaction";
	private static final String PURCHASE_MENU_OPTION_PREVIOUS_MENU = "Previous Menu";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_ADD_MONEY, PURCHASE_MENU_OPTION_BUY_ITEM, PURCHASE_MENU_OPTION_CASH_OUT, PURCHASE_MENU_OPTION_PREVIOUS_MENU };
	//LOOP CONFIG
	private static final String MAIN_LOOP = "Main";
	private static final String PURCHASE_LOOP = "Purchase";
	// DELCARE AND INSTANTIATE PRIVATE MEMBERS
	private Menu menu;
	private Machine machine = new Machine();
	private Log logger = new Log();
	Map<String,Item> itemsByLocation = machine.getItemsByLocation();
	Scanner systemInput = new Scanner(System.in);
	//CONSTRUCTOR
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	private void displayItemsForPurchase()
	{
		System.out.println("Order an item by its location:");
		System.out.println("********************************");

		Set<String> hashSetToTreeSet = new TreeSet<>(itemsByLocation.keySet());
		for (String location : hashSetToTreeSet)
		{
			Item particularItem = itemsByLocation.get(location);
			String itemName = particularItem.getItemName();
			int quantityRemaining = particularItem.getQuantityRemaining();
			int itemPrice = particularItem.getPrice();
			String itemPriceString = (quantityRemaining != 0) ? PennyMath.intToPriceWithDollarSign(itemPrice)
					: "SOLD OUT";
			String quantityRemainingString = (quantityRemaining != 0) ? Integer.toString(quantityRemaining) + " left"
					: "";
			System.out.println(location + "\t" + itemName + "\t" + itemPriceString + "\t" + quantityRemainingString);
		}
		System.out.println("\n Come on, buy something. Current balance: " + machine.currentBalanceAsDollars());
	}

	// Purchase item method
	private void feedMoneyRoutine() {
		int startingBalancePennies = machine.coinBox.getBalance();
		System.out.println("Current balance: " + machine.currentBalanceAsDollars());
		System.out.println("How many dollars are you adding?");
		int dollarsMentioned = 0;
		try { dollarsMentioned = Integer.parseInt(systemInput.nextLine()); }
		catch (Exception ex) {
			System.out.println("Nice try. Bad input. I'll count that as nothing.");
			dollarsMentioned = 0 ;
		}

		int penniesToAdd = (dollarsMentioned>=0) ? dollarsMentioned*100 : 0;
		machine.coinBox.feedMoney(penniesToAdd);
		logger.writeLog(startingBalancePennies,machine.coinBox.getBalance());
		System.out.println("Current balance: " + machine.currentBalanceAsDollars());
	}

	private void purchaseRoutine() {
		System.out.println("Oh Yum! What delicious Umbrella Corp Snack would you like");
		int availableFunds = machine.coinBox.getBalance();
		String itemSelected = systemInput.nextLine().toUpperCase();
		if (!itemsByLocation.containsKey(itemSelected))
		{
			System.out.println("Invalid item number.");
			return;
		}
		Item stockItem = itemsByLocation.get(itemSelected);
		int amountLeft = stockItem.getQuantityRemaining();
		if (amountLeft==0)
		{
			System.out.println("Sorry, friend, I'm all out of it.");
			return;
		}
		int price = stockItem.getPrice();
		if (price > availableFunds)
		{
			System.out.println("Insufficient funds.");
			return;
		}


		// and now we dispense
		stockItem.setQuantityRemaining(amountLeft - 1);
		stockItem.dispenseMessage();
		String itemName = stockItem.getItemName();
		int moneyRemaining = availableFunds - price;
		System.out.println("Here you go, friend.\n" + "Item name: " + itemName + "\n"
		+ "Item cost: " + PennyMath.intToPriceWithDollarSign(price) + "\n"
		+ "Money remaining: " + PennyMath.intToPriceWithDollarSign(moneyRemaining) + "\n");

		machine.coinBox.setBalance(moneyRemaining);
		logger.writeLog(itemName,itemSelected,availableFunds,moneyRemaining);

		displayItemsForPurchase();

		return;


	}


	void cashOut() {
		int currentBalance = machine.coinBox.getBalance();
		System.out.println("Current balance: " + machine.currentBalanceAsDollars());
		System.out.println("Now returning to you as nickels, dimes, and quarters.");
		Map<String, Integer> change = machine.coinBox.makeChange();
		for (Map.Entry<String,Integer> changePair : change.entrySet())
		{
			String coin = changePair.getKey();
			Integer value = changePair.getValue();
			System.out.println(coin + ": " + value);
		}
		logger.writeLog(currentBalance);

	}

	// RUN METHOD
	public void run() {
		//vm.announceStart();
		String menuLoop = MAIN_LOOP;
		//IN THIS APPROACH, THE MAIN LOOP PROVIDES TOP LEVEL NAVIGATION
		// AND SETS THE menuLoop VARIABLE TO TRIGGER CHILD LOOPS
		// EACH CHILD LOOP, IN TURN, MAY ALSO TRIGGER ITS OWN CHILD LOOPS
		//MAIN MENU LOOP - LOOP LEVEL 0-0-0
		while (true) {
			menuLoop = MAIN_LOOP;
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) { // choice 1
				// display vending machine items
				System.out.println("Don't you like surprises?");
				displayItemsForPurchase();
				menuLoop = PURCHASE_LOOP;
			}else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase menu things
				System.out.println("Purchase loop...");

				menuLoop = PURCHASE_LOOP; // triggered below
			}else if (choice.equals(MAIN_MENU_OPTION_EXIT)){
				System.out.println("No one likes a quitter - Love, Umbrella Corp.");
				break;
			}
			// PURCHASE SUB MENU LOOP - LOOP LEVEL 0-1-0
			while(menuLoop.equals(PURCHASE_LOOP)) {
				String currentMoney = "Current money provided (still available): " + machine.currentBalanceAsDollars();
				choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS,currentMoney);
				if (choice.equals(PURCHASE_MENU_OPTION_ADD_MONEY)) {
					System.out.println("FEED ME!!!!");
					feedMoneyRoutine();
				}
				if (choice.equals(PURCHASE_MENU_OPTION_BUY_ITEM)) {
					purchaseRoutine();
				}
				if (choice.equals(PURCHASE_MENU_OPTION_CASH_OUT)) {
					System.out.println("Sorry, Umbrella Corp keeps your change");
					System.out.println("Just kidding. Here you go.");

					cashOut();

				}
				if (choice.equals(PURCHASE_MENU_OPTION_PREVIOUS_MENU)) {
					menuLoop = MAIN_LOOP;
				}
			}
		}
	}
	// PSVM PROGRAM EXECUTION ENTRY POINT
	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}