package com.techelevator;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Machine {
    public static final String stockFileName ="vendingmachine.csv";
    public static final String dataDirectory = "datafiles";
    private Map<String,Item> itemsByLocation;

    public Map<String, Item> getItemsByLocation () {
        return itemsByLocation;
    }

    private void addItem(String itemLocation, Item item)
    {
        this.itemsByLocation.put(itemLocation,item);
    }

    private int priceToInt(String price) {
        // accepts a price, such as "1.25," and converts to penny
        // math int 125
        // dollarsAndCents[] = {"1","25"}

        String[] dollarsAndCents = price.split("\\.");
        String dollarPortionString = dollarsAndCents[0];
        String centsPortionString = dollarsAndCents[1];

        int dollarPortion = Integer.parseInt(dollarPortionString);
        int centsPortion = Integer.parseInt(centsPortionString);

        int centsTotal = dollarPortion*100 + centsPortion;

        return centsTotal;
    }

    public void loadMachine() throws Exception {

        File stockFile = new File(dataDirectory,stockFileName);
        if (!stockFile.exists()) {
            System.out.println("File does not exist");
        }
        try (Scanner stockFileInput = new Scanner(stockFile)){
            while (stockFileInput.hasNextLine()){
                String stockEntry = stockFileInput.nextLine();
                if (!stockEntry.contains("|")){
                    continue;
                }
                String[] stockEntries = stockEntry.split("\\|");
                if (!stockEntries.length == 4){
                    continue;
                }
                String location = stockEntries[0];
                String itemName = stockEntries[1];
                int price = priceToInt(stockEntries[2]);
                String type = stockEntries[3];



                
            }

        }
        catch (IOException ex) {
            System.out.println("There was a problem and we need to abort loading the machine");
        }
        catch (Exception e){
            System.out.println("There was a problem with the machine");
        }

    }



}
