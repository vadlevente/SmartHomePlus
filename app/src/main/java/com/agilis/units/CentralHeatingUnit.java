package com.agilis.units;

import java.util.Calendar;

public class CentralHeatingUnit {

    private static CentralHeatingUnit instance;

    private Calendar calendar;

    private int heatingTemperature; //celsius

    public static CentralHeatingUnit getInstance() {
        if (instance == null) {
            instance = new CentralHeatingUnit();
        }
        return instance;

    }

    private CentralHeatingUnit(){
        adjustTemperature();
    }

    public void adjustTemperature() {
        calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (month == Calendar.NOVEMBER || month == Calendar.DECEMBER || month == Calendar.JANUARY || month == Calendar.FEBRUARY) {
            if (hour < 8 || hour > 18) {
                heatingTemperature = 23;
            } else {
                heatingTemperature = 21;
            }
        } else {
            heatingTemperature = 0;
        }

    }

    public int getHeatingTemperature(){
        return heatingTemperature;
    }



}
