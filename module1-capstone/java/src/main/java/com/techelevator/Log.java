package com.techelevator;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class Log {
    public static String currentDateTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        LocalDateTime now = LocalDateTime.now();
        String formattedNow = dtf.format(now);
        return formattedNow;
    }

    public static String startAndEndBalanceString(int startingBalance,int endingBalance){
        String startingMoney = PennyMath.intToPriceWithDollarSign(startingBalance);
        String endingMoney = PennyMath.intToPriceWithDollarSign(endingBalance);
        String result = startingMoney + " " + endingMoney;
        return result;

    }

}
