package com.techelevator;

public class PennyMath {
    public static int priceToInt(String price) {
        // accepts a price, such as "1.25," and converts to penny
        // math int 125
        // dollarsAndCents[] = {"1","25"}

        if (price==null) {return 0;}

        String firstChar = price.substring(0,1);
        if (firstChar.equals("$")&price.length()==1) {return 0;}

        if (firstChar.equals("$")) {  // accept either "$1.25" or "1.25"
            price = price.substring(1,price.length());
        }

        String[] dollarsAndCents = price.split("\\.");
        String dollarPortionString = dollarsAndCents[0];
        String centsPortionString = dollarsAndCents[1];

        int dollarPortion = Integer.parseInt(dollarPortionString);
        int centsPortion = Integer.parseInt(centsPortionString);

        int centsTotal = dollarPortion*100 + centsPortion;

        return centsTotal;
    }

    public static String intToPrice(int pennies){

        int dollars = pennies/100;
        int cents = pennies % 100;
        String dollarsString = Integer.toString(dollars);
        String centsString = (cents >= 10) ? Integer.toString(cents) : "0" + Integer.toString(cents);

        String money = dollarsString + "." + centsString;
        return money;
    }

    public static String intToPriceWithDollarSign(int pennies) {

        String returnValue = "$" + intToPrice(pennies);
        return returnValue;
    }


}
