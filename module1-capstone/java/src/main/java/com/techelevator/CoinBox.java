package com.techelevator;

import java.util.Map;

public class CoinBox {
    private int balance=0;
    private void feedMoney(int moneyToAdd){
        if(moneyToAdd<0){
            return ;
        }
        balance += moneyToAdd;
    }
    public Map<String,Integer> makeChange(int costOfItem) {
        int newBalance = balance - costOfItem;
        if (newBalance < 0) {
            return null;
        }

    }

}
