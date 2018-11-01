package com.agilis.units;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class CentralHeatingTest {

    CentralHeating centralHeating;
    final String dateFormat = "yyyy-MM-dd HH:mm";

    @Before
    public void initUnit() {
        centralHeating = CentralHeating.getInstance();
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
        centralHeating.setCalendar(calendar);
    }

    @Test
    public void unitExists() {
        assertNotNull(centralHeating);
    }

    @Test
    public void hasTemperatureField() {
        assertNotNull(centralHeating.getHeatingTemperature());
    }

    @Test
    public void summerTemperatureIs0() {
        setCalendar(createCalendar("2018-06-10 12:00"));
        centralHeating.setManual(false);
        centralHeating.adjustTemperature();
        int heatingTemperature = centralHeating.getHeatingTemperature();
        assertEquals(0,heatingTemperature);
    }

    @Test
    public void winterNightTemperatureIs23() {
        setCalendar(createCalendar("2018-12-10 02:00"));
        centralHeating.setManual(false);
        centralHeating.adjustTemperature();
        int heatingTemperature = centralHeating.getHeatingTemperature();
        assertEquals(23,heatingTemperature);
    }

    @Test
    public void winterDayTemperatureIs21() {
        setCalendar(createCalendar("2018-12-10 12:00"));
        centralHeating.setManual(false);
        centralHeating.adjustTemperature();
        int heatingTemperature = centralHeating.getHeatingTemperature();
        assertEquals(21,heatingTemperature);
    }


    @Test
    public void manualStateTrue() {
        centralHeating.setManual(true);
        assertEquals(true, centralHeating.isManual());
    }

    @Test
    public void manualStateFalse() {
        centralHeating.setManual(false);
        assertEquals(false, centralHeating.isManual());
    }

    @Test
    public void manualTemperature() {
        centralHeating.setManual(true);
        centralHeating.setHeatingTemperature(15);
        centralHeating.adjustTemperature();
        assertEquals(15, centralHeating.getHeatingTemperature());
    }
}
