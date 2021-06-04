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

    private static String assembleLogEntry(String modeString, int startBalance, int endBalance)
    {
        // modeString is either GIVE CHANGE:, or FEED MONEY:, or a product followed by position
        String result = currentDateTime() + " " + modeString + " " +
                startAndEndBalanceString(startBalance, endBalance);
        return result;
    }

    public static String _testAssembleLogEntry(String m, int s, int e) {
        return assembleLogEntry(m,s,e);
    }

    private static String assembleGiveChangeLog(int startBalance, int endBalance)
    {
        return assembleLogEntry("GIVE CHANGE:",startBalance,endBalance);
    }

    private static String assembleFeedMoneyLog(int startBalance, int endBalance)
    {
        return assembleLogEntry("FEED MONEY:",startBalance, endBalance);
    }

    private static String assembleItemBoughtLog(String itemName, String itemLocation,
                                                int startBalance, int endBalance)
    {
        String itemPurchasedAtLocation = itemName + " " + itemLocation;
        return assembleLogEntry(itemPurchasedAtLocation,startBalance,endBalance);
    }

}
