package com.techelevator;
import org.junit.*;

import java.util.Map;

public class MachineTest {

    @Test
    public void machineStartsupOK() {
        Machine machineStartup = new Machine();
        Assert.assertNotNull(machineStartup);
    }

    @Test
    public void testItemsByLocation() {
        Machine machineStartup = new Machine();

        // Arrange
        Map<String,Item> itemMap = machineStartup.getItemsByLocation();
        String expected1 = "Potato Crisps"; // at A1
        String expected2 = "Triplemint"; // at D4
        int expected3 = 175; // at B4

        Item atA1 = itemMap.get("A1");
        Item atD4 = itemMap.get("D4");
        Item atB4 = itemMap.get("B4");

        String result1 = atA1.getItemName();
        String result2 = atD4.getItemName();
        int result3 = atB4.getPrice();

        Assert.assertEquals(expected1,result1);
        Assert.assertEquals(expected2,result2);
        Assert.assertEquals(expected3,result3);

    }
}
