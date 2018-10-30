package com.agilis;

import com.agilis.units.TDDUnits.CentralHeatingUnitTDD;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class CentralHeatingTest {

    CentralHeatingUnitTDD heatingUnit;
    final String dateFormat = "yyyy-MM-dd HH:mm";

    @Before
    public void initUnit() {
        heatingUnit = CentralHeatingUnitTDD.getInstance();
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
        heatingUnit.setCalendar(calendar);
    }

    @Test
    public void unitExists() {
        assertNotNull(heatingUnit);
    }

    @Test
    public void hasTemperatureField() {
        int heatingTemperature = heatingUnit.getHeatingTemperature();
        assertEquals(heatingTemperature, 0);
    }

    @Test
    public void summerTemperatureIs0() {
        setCalendar(createCalendar("2018-06-10 12:00"));
        heatingUnit.adjustTemperature();
        int heatingTemperature = heatingUnit.getHeatingTemperature();
        assertEquals(0,heatingTemperature);
    }

    @Test
    public void winterNightTemperatureIs23() {
        setCalendar(createCalendar("2018-12-10 02:00"));
        heatingUnit.adjustTemperature();
        int heatingTemperature = heatingUnit.getHeatingTemperature();
        assertEquals(23,heatingTemperature);
    }

    @Test
    public void winterDayTemperatureIs21() {
        setCalendar(createCalendar("2018-12-10 12:00"));
        heatingUnit.adjustTemperature();
        int heatingTemperature = heatingUnit.getHeatingTemperature();
        assertEquals(21,heatingTemperature);
    }

}
