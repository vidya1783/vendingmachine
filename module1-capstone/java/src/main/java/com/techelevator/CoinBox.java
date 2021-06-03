package com.techelevator;

import java.util.HashMap;
import java.util.Map;

public class CoinBox {
    private int balance=0;

    public int getBalance() {
        return balance;
    }

    public void feedMoney(int moneyToAdd){
        if(moneyToAdd<0){
            return ;
        }
        balance += moneyToAdd;
    }

    public Map<String,Integer> makeChange(int costOfItem) {
        Map<String,Integer> changeMap = new HashMap<>();
        int change = balance - costOfItem;
        if (change < 0) {
            return null;
        }

        Integer quarters;
        Integer dimes;
        Integer nickels;

        quarters = change / 25;
        change -= quarters*25;
        changeMap.put("quarters",quarters);
        dimes = change / 10;
        change -= dimes*10;
        changeMap.put("dimes",dimes);
        nickels = change / 5;
        change -= nickels*5;
        changeMap.put("nickels",nickels);

        balance = 0;

        return changeMap;

    }

}
