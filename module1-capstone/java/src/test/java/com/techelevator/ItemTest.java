package com.techelevator;

import org.junit.*;

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

}
