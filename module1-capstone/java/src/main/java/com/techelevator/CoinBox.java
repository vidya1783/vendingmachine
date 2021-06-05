package com.techelevator;

import java.util.HashMap;
import java.util.Map;

public class CoinBox {
    private int balance=0;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int newBalance) {this.balance = newBalance;}

    public void feedMoney(int moneyToAdd){
        if(moneyToAdd<0){
            return ;
        }
        balance += moneyToAdd;
    }

    public Map<String,Integer> makeChange() {
        Map<String,Integer> changeMap = new HashMap<>();

        Integer quarters;
        Integer dimes;
        Integer nickels;

        quarters = balance / 25;
        balance -= quarters*25;
        changeMap.put("quarters",quarters);
        dimes = balance / 10;
        balance -= dimes*10;
        changeMap.put("dimes",dimes);
        nickels = balance / 5;
        balance -= nickels*5;
        changeMap.put("nickels",nickels);

        balance = 0;

        return changeMap;

    }

}
