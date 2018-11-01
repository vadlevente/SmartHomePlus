package com.agilis.units;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CoffeeMachineTest {

    CoffeeMachine coffeeMachine;
    final String dateFormat = "yyyy-MM-dd HH:mm";

    @Before
    public void initUnit() {
        coffeeMachine = CoffeeMachine.getInstance();
    }

    public Calendar createCalendar(String time) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = format.parse(time);
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        coffeeMachine.setCalendar(calendar);
    }

    @Test
    public void unitExists() {
        assertNotNull(coffeeMachine);
    }

    @Test
    public void hasStateField() {
        assertNotNull(coffeeMachine.getState());
    }

    @Test
    public void beforeStateIsStandby() {
        coffeeMachine.setCoffeeTime(7, 50);
        setCalendar(createCalendar("2018-11-10 07:00"));
        assertEquals( CoffeeMachine.STATE_STANDBY,coffeeMachine.getState());
    }

    @Test
    public void duringStateIsWorking() {
        coffeeMachine.setCoffeeTime(7, 50);
        setCalendar(createCalendar("2018-11-10 07:49"));
        assertEquals( CoffeeMachine.STATE_WORKING,coffeeMachine.getState());
    }

    @Test
    public void afterStateIsReady() {
        coffeeMachine.setCoffeeTime(7, 50);
        setCalendar(createCalendar("2018-11-10 07:51"));
        assertEquals( CoffeeMachine.STATE_READY,coffeeMachine.getState());
    }

    @Test
    public void makeCoffeeAtAfternoon() {
        coffeeMachine.setCoffeeTime(15, 00);
        setCalendar(createCalendar("2018-11-10 15:01"));
        assertEquals( CoffeeMachine.STATE_READY,coffeeMachine.getState());
    }

    @Test
    public void makeCoffeeAtNight() {
        coffeeMachine.setCoffeeTime(22, 00);
        setCalendar(createCalendar("2018-11-10 22:01"));
        assertEquals( CoffeeMachine.STATE_READY,coffeeMachine.getState());
    }

}
