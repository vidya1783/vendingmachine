package com.techelevator;

public abstract class Item {

    private String itemName="";
    private int price=0;
    private int quantityRemaining=5;


    public Item(String itemName, int price){
        this.itemName=itemName;
        this.price=price;
        this.quantityRemaining=5;
    }

    public abstract void dispenseMessage();

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantityRemaining() {
        return quantityRemaining;
    }

    public void setQuantityRemaining(int quantityRemaining) {
        this.quantityRemaining = quantityRemaining;
    }


}
