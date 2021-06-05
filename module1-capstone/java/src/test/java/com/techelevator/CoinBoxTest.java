package com.techelevator;

import org.junit.*;
import java.util.Map;

public class CoinBoxTest {
    CoinBox coinBox;

    @Before
    public void init(){
        coinBox = new CoinBox();
        Assert.assertNotNull(coinBox);
    }

    @Test
    public void testFeed(){
        coinBox.feedMoney(100);
        int expected = 100;
        int result = coinBox.getBalance();
        Assert.assertEquals(expected,result);
    }

    @Test
    public void setBalanceTest()
    {
        coinBox.setBalance(2000);
        int expected = 2000;
        int result = coinBox.getBalance();
        Assert.assertEquals(expected,result);
    }

    @Test
    public void getBalanceTest()
    {
        int expected = 0;
        int result = coinBox.getBalance();
        Assert.assertEquals(expected,result);
    }

    @Test
    public void testChange(){
        coinBox.setBalance(90);
        Map<String , Integer> changeMap = coinBox.makeChange();
        int expected1 = 3;
        int expected2 = 1;
        int expected3 = 1;

        int result1 = changeMap.get("quarters");
        int result2 = changeMap.get("dimes");
        int result3 = changeMap.get("nickels");
         Assert.assertEquals(expected1,result1);
        Assert.assertEquals(expected2,result2);
        Assert.assertEquals(expected3,result3);


    }


}
