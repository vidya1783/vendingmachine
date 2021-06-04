package com.techelevator;

import org.junit.*;

public class PennyMathTest {
    @Test
    public void priceToIntTest() {
        int expected = 125;
        int result = PennyMath.priceToInt("1.25");
        Assert.assertEquals(expected,result);
    }

    @Test
    public void priceToIntNoChokeOnNull() {
        int expected = 0;
        int result = PennyMath.priceToInt(null);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void priceToIntProcessesWholeDollar() {
        int expected = 100;
        int result = PennyMath.priceToInt("1.00");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void priceToIntAcceptsDollarSign() {
        int expected = 125;
        int result = PennyMath.priceToInt("$1.25");
        Assert.assertEquals(expected,result);
    }

    @Test
    public void priceToIntNoChokeOnDollarSign() {
        int expected = 0;
        int result = PennyMath.priceToInt("$");
        Assert.assertEquals(expected,result);
    }

    @Test
    public void intToPriceSingleDigitCents() {
        String expected = "1.09";
        String result = PennyMath.intToPrice(109);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void intToPriceDoubleDigits() {
        String expected = "1.50";
        String result = PennyMath.intToPrice(150);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void intToPriceWithDollarSign() {
        String expected = "$1.25";
        String result = PennyMath.intToPriceWithDollarSign(125);
        Assert.assertEquals(expected,result);
    }

}