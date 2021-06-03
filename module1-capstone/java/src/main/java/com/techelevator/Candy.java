package com.techelevator;

public class Candy extends Item{
    public Candy(String itemName, int price) {
        super(itemName,price);
    }

    @Override
    public void dispenseMessage() {
        System.out.println("Munch Munch, Yum!");
    }
}
