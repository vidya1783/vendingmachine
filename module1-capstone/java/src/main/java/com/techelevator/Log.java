package com.techelevator;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class Log {
    File outputFile;
    private static final String logfileName = "Log.txt";

    public Log() {
        outputFile = new File(logfileName);

        if (!outputFile.exists()) {
            try { outputFile.createNewFile(); }
            catch (IOException ex)
            { System.out.println("Could not create logfile."); }
            catch (Exception ex)
            { System.out.println("There was a problem creating the file."); }
        }
    }

    public void writeLog(int startingBalance, int endingBalance) // when adding money
    {
        String logMessage;
        logMessage = assembleFeedMoneyLog(startingBalance,endingBalance);
        writeLine(logMessage);
    }

    public void writeLog(int startingBalance) // when giving change, ending balance is 0
    {
        String logMessage;
        logMessage = assembleGiveChangeLog(startingBalance);
        writeLine(logMessage);
    }

    public void writeLog(String itemName, String itemLocation,
                         int startingBalance, int endingBalance) // when selling something
    {
        String logMessage;
        logMessage = assembleItemBoughtLog(itemName,itemLocation,startingBalance,endingBalance);
        writeLine(logMessage);
    }

    private void writeLine(String lineToWrite) {
        try (PrintWriter writer = new PrintWriter(outputFile)) {
             writer.println(lineToWrite);
        } catch (IOException ex){
            System.out.println("Problem writing to file.");

        } catch (Exception ex){
            System.out.println("There was another problem writing to the file.");
        }
    }



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

    private static String assembleGiveChangeLog(int startBalance)
    {
        return assembleLogEntry("GIVE CHANGE:",startBalance,0);
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

