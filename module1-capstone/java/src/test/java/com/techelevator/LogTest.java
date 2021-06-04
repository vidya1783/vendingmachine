package com.techelevator;

import org.junit.*;

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
}
