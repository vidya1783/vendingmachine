package com.techelevator;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Machine {
    private static final String stockFileName ="vendingmachine.csv";
    // private static final String dataDirectory = "datafiles";
    private Map<String,Item> itemsByLocation = new HashMap<>();

    public Machine(){
        try {
            loadMachine();
        }
        catch (IOException ex){
            System.out.println("Problem reading file");
        }
        catch (Exception e){
            System.out.println("Something bad happened and the machine won't work");
        }
    }
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

        File stockFile = new File(stockFileName);
        // String absolutePath = stockFile.getAbsolutePath();
        // String canonicalPath = stockFile.getCanonicalPath();
        // System.out.println(absolutePath);
        // System.out.println(canonicalPath);
        if (!stockFile.exists()) {
            System.out.println("File does not exist");
            throw new IOException();
        }
        try (Scanner stockFileInput = new Scanner(stockFile)){
            while (stockFileInput.hasNextLine()){
                String stockEntry = stockFileInput.nextLine();
                if (!stockEntry.contains("|")){
                    continue;
                }
                String[] stockEntries = stockEntry.split("\\|");
                if (!(stockEntries.length == 4)){
                    continue;
                }
                String location = stockEntries[0];
                String itemName = stockEntries[1];
                int price = priceToInt(stockEntries[2]);
                String type = stockEntries[3];

                Item newItem = null;
                if (type.equals("Chip")){
                    newItem = new Chips(itemName,price);
                }
                if (type.equals("Candy")){
                    newItem = new Candy(itemName,price);
                }
                if (type.equals("Gum")){
                    newItem = new Gum(itemName,price);
                }
                if (type.equals("Drink")){
                    newItem = new Beverage(itemName,price);
                }
                if (type != null){
                    addItem(location,newItem);
                }

            }

        }
        catch (IOException ex) {
           throw ex;
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            throw ex;
        }

    }



}
