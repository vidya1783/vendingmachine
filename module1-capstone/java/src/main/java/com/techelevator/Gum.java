package com.techelevator;

public class Gum extends Item{
    public Gum(String itemName, int price) {
        super(itemName,price);
    }

    @Override
    public void dispenseMessage() {
        System.out.println("Chew Chew, Yum!");
    }
}
