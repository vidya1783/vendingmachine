package com.techelevator;

import org.junit.*;

import java.sql.SQLOutput;

public class ItemTest {
    Item testItem;

    @Before
    public void init(){
        testItem = new Chips("Pringles",200);
        Assert.assertNotNull(testItem);
    }

    @Test
    public void testGetItemName(){
        String expected = "Pringles";
        String result  = testItem.getItemName();
        Assert.assertEquals(expected,result);

    }

    @Test
    public void testGetItemPrice() {
        int expected = 200;
        int result = testItem.getPrice();
        Assert.assertEquals(expected,result);
    }

    @Test
    public void testGetItemRemaining() {
        int expected = 5;
        int result = testItem.getQuantityRemaining();
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testSetItemName() {
        String expected = "Herr's Potato Chips";
        testItem.setItemName(expected);
        String result = testItem.getItemName();
        Assert.assertEquals(expected,result);
    }

    @Test
    public void testSetQuantityRemaining() {
        int expected = 2;
        testItem.setQuantityRemaining(2);
        int result = testItem.getQuantityRemaining();
        Assert.assertEquals(expected,result);
    }

    @Test
    public void testSetItemPrice() {
        int expected = 2000;
        testItem.setPrice(2000);
        int result = testItem.getPrice();
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testAllSubtypes() {
        String name = "Amazing product";
        int price = 200;
        testItem = new Chips(name,price);
        Assert.assertNotNull(testItem);
        System.out.println("Printing chips message");
        testItem.dispenseMessage();
        testItem = new Gum(name,price);
        Assert.assertNotNull(testItem);
        System.out.println("Printing gum message");
        testItem.dispenseMessage();
        testItem = new Candy(name,price);
        Assert.assertNotNull(testItem);
        System.out.println("Printing candy message");
        testItem.dispenseMessage();
        testItem = new Beverage(name,price);
        Assert.assertNotNull(testItem);
        System.out.println("Printing drink message");
        testItem.dispenseMessage();

    }

}
