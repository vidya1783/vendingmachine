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
