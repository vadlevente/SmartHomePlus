package com.agilis.units;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

public class AutomaticShutterTest {

    private static AutomaticShutter automaticShutter;

    @Before
    public void setUp() {
        automaticShutter = new AutomaticShutter();
    }

    @Test
    public void setState_newValue_newStateSet() {
        automaticShutter.setState(0);
        Assert.assertEquals(0, automaticShutter.getCurrentState());
    }

    @Test
    public void getCurrentState_noInput_currentStateReturned() {
        Assert.assertEquals(0, automaticShutter.getCurrentState());
    }

    @Test
    public void getDaybreak_noInput_DaybreakReturned() {
        Assert.assertEquals(6, automaticShutter.getDaybreak());
    }

    @Test
    public void setDaybreak_newValue_newDaybreakSet() {
        automaticShutter.setDaybreak(12);
        Assert.assertEquals(12, automaticShutter.getDaybreak());
    }

    @Test
    public void getMidday_noInput_MiddayReturned() {
        Assert.assertEquals(13, automaticShutter.getMidday());
    }

    @Test
    public void setMidday_newValue_newMiddaySet() {
        automaticShutter.setMidday(14);
        Assert.assertEquals(14, automaticShutter.getMidday());
    }

    @Test
    public void getDusk_noInput_DuskReturned() {
        Assert.assertEquals(18, automaticShutter.getDusk());
    }

    @Test
    public void setDusk_newValue_newDuskSet() {
        automaticShutter.setDusk(20);
        Assert.assertEquals(20, automaticShutter.getDusk());
    }

    @Test
    public void moveUp_startingAndEndState_shutterMovedUp() throws InterruptedException {
        automaticShutter.setState(20);
        automaticShutter.moveUp(automaticShutter.getCurrentState(), 80);
        Assert.assertEquals(80, automaticShutter.getCurrentState());
    }

    @Test
    public void moveDown_startingAndEndState_shutterMovedDown() throws InterruptedException {
        automaticShutter.setState(80);
        automaticShutter.moveDown(automaticShutter.getCurrentState(), 20);
        Assert.assertEquals(20, automaticShutter.getCurrentState());
    }
}