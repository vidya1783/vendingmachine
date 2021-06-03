package com.techelevator;

public class Beverage extends Item{
    public Beverage(String itemName, int price) {
        super(itemName,price);
    }

    @Override
    public void dispenseMessage() {
        System.out.println("Glug Glug, Yum!");
    }
}
