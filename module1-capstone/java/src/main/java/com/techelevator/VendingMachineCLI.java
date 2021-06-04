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
	String itemSelectedLocation = null;
	Item itemSelectedObject = null;
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
			int itemPrice = particularItem.getPrice();
			String itemPriceString = PennyMath.intToPriceWithDollarSign(itemPrice);
			int quantityRemaining = particularItem.getQuantityRemaining();
			String quantityRemainingString = Integer.toString(quantityRemaining) + " left";
			System.out.println(location + "\t" + itemName + "\t" + itemPriceString + "\t" + quantityRemainingString);
		}
	}

	// Purchase item method
	private void feedMoneyRoutine() {
		int startingBalancePennies = machine.coinBox.getBalance();
		System.out.println("Current balance: " + machine.currentBalanceAsDollars());
		System.out.println("How many dollars are you adding?");
		int dollarsMentioned = Integer.parseInt(systemInput.nextLine());
		int penniesToAdd = (dollarsMentioned>=0) ? dollarsMentioned*100 : 0;
		machine.coinBox.feedMoney(penniesToAdd);
		logger.writeLog(startingBalancePennies,machine.coinBox.getBalance());
		System.out.println("Current balance: " + machine.currentBalanceAsDollars());
	}

	private String purchaseRoutine() {
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

		itemSelectedLocation = itemSelected;
		itemSelectedObject = stockItem;

		return;


	}

	void buyAndCashOut() {
		int amountLeft = itemSelectedObject.getQuantityRemaining();
		int price = itemSelectedObject.getPrice();
		itemSelectedObject.setQuantityRemaining(amountLeft - 1);
		itemSelectedObject.dispenseMessage();
		int availableFunds = machine.coinBox.getBalance();

		String itemName = itemSelectedObject.getItemName();
		machine.coinBox.setBalance(availableFunds - price);
		logger.writeLog(itemName,itemSelectedLocation,availableFunds,availableFunds-price);
		System.out.println("Current balance: " + machine.currentBalanceAsDollars());
		System.out.println("Now returning to you as nickels dimes and quarters.");
		//
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
				choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
				if (choice.equals(PURCHASE_MENU_OPTION_ADD_MONEY)) {
					System.out.println("FEED ME!!!!");
					feedMoneyRoutine();
				}
				if (choice.equals(PURCHASE_MENU_OPTION_BUY_ITEM)) {
					purchaseRoutine();
				}
				if (choice.equals(PURCHASE_MENU_OPTION_CASH_OUT)) {
					System.out.println("Sorry, Umbrella Corp keeps your change");
					System.out.println("<<< J/K Call Change Routine(s) >>>");
					if (itemSelectedObject==null||itemSelectedLocation==null) // need to make sure these are reset
					{
						System.out.println("Invalid item selected.");
						continue;
					}
					buyAndCashOut();

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