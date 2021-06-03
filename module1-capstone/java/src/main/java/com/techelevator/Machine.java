package com.techelevator;

import java.util.Map;

public class Machine {
    private Map<String,Item> itemsByLocation;

    public Map<String, Item> getItemsByLocation() {
        return itemsByLocation;
    }

    private void addItem(String itemLocation, Item item)
    {
        this.itemsByLocation.put(itemLocation,item);
    }





}
