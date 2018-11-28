package com.agilis.units;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class WateringSystemTest {

    private WateringSystem wS;

    @Before
    public void initUnit() {
        wS = new WateringSystem();
    }

    @Test
    public void startsInStateOff() {
        Assert.assertEquals(WateringSystem.State.STATE_OFF, wS.getState());
    }

    @Test
    public void canSendSensorData() {
        int newData = 59;
        wS.sendMoistureSensorData(newData);
        Assert.assertEquals(newData, wS.getMoisturePercentage());
    }

    @Test
    public void changesStateWhenMoistureChanges() {
        //Really dry
        int newData = 10;
        wS.sendMoistureSensorData(newData);
        Assert.assertEquals(WateringSystem.State.STATE_HIGH, wS.getState());
        //Quite damp
        newData = 70;
        wS.sendMoistureSensorData(newData);
        Assert.assertEquals(WateringSystem.State.STATE_LOW, wS.getState());
        //Wet
        newData = 98;
        wS.sendMoistureSensorData(newData);
        Assert.assertEquals(WateringSystem.State.STATE_OFF, wS.getState());
    }
}
