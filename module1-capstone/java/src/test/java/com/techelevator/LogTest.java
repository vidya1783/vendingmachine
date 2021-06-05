package com.techelevator;

import org.junit.*;
import java.io.*;
import java.util.Scanner;

public class LogTest {
    @Test
    public void currentDateTimeNotNull(){
        String result = Log.currentDateTime();
        Assert.assertNotNull(result);
    }

    @Test
    public void currentDateTimeMakesSense(){
        String result = Log.currentDateTime();
        System.out.println(result);
    }

    @Test
    public void startAndEndBalanceWorks(){
        String expected = "$5.00 $2.50";
        String result = Log.startAndEndBalanceString(500,250);
        Assert.assertEquals(expected,result);
    }

    @Test
    public void testAssembleLogEntry() {
        String expected = "FEED MONEY: $5.00 $1.50";
        String testEntry = Log._testAssembleLogEntry("FEED MONEY:",500,150);
        String result = testEntry.substring(testEntry.length()-expected.length(),
                testEntry.length());
        Assert.assertEquals(expected,result);
    }

    @Test
    public void createNewLogTest(){
        Log testLog = new Log();
        Assert.assertNotNull(testLog);
    }

    @Test
    public void testAddMoneyWriteToFile() {
        try {
            Log testLog = new Log();
            String expected = "FEED MONEY: $5.00 $1.50";
            testLog.writeLog(500, 150);
            File logFile = new File("Log.txt");
            Scanner scanner = new Scanner(logFile);
            String lineJustRead = "";
            while (scanner.hasNextLine()){
                lineJustRead = scanner.nextLine();

            }
            String result = lineJustRead.substring(lineJustRead.length()-expected.length(),
                    lineJustRead.length());
            Assert.assertEquals(expected,result);

        } catch (Exception ex) {
            Assert.fail("Exception while executing the test.");
            System.out.println(ex.getMessage());
        }

    }

    @Test
    public void testGiveChangeWriteToFile() {
        try {
            Log testLog = new Log();
            String expected = "GIVE CHANGE: $5.00 $0.00";
            testLog.writeLog(500);
            File logFile = new File("Log.txt");
            Scanner scanner = new Scanner(logFile);
            String lineJustRead = "";
            while (scanner.hasNextLine()){
                lineJustRead = scanner.nextLine();

            }
            String result = lineJustRead.substring(lineJustRead.length()-expected.length(),
                    lineJustRead.length());
            Assert.assertEquals(expected,result);

        } catch (Exception ex) {
            Assert.fail("Exception while executing the test.");
            System.out.println(ex.getMessage());
        }

    }

    @Test
    public void testVendProductWriteToFile() {
        try {
            Log testLog = new Log();
            String expected = "Crunchie B4 $10.00 $8.50";
            testLog.writeLog("Crunchie","B4",1000,850);
            File logFile = new File("Log.txt");
            Scanner scanner = new Scanner(logFile);
            String lineJustRead = "";
            while (scanner.hasNextLine()){
                lineJustRead = scanner.nextLine();

            }
            String result = lineJustRead.substring(lineJustRead.length()-expected.length(),
                    lineJustRead.length());
            Assert.assertEquals(expected,result);

        } catch (Exception ex) {
            Assert.fail("Exception while executing the test.");
            System.out.println(ex.getMessage());
        }

    }



}
