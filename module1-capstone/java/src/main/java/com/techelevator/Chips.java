package com.techelevator;

public class Chips extends Item{
    public Chips(String itemName, int price) {
        super(itemName,price);
    }

    @Override
    public void dispenseMessage() {
        System.out.println("Crunch Crunch, Yum!");
    }
}
