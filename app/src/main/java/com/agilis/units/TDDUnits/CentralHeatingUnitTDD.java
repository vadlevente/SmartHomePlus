package com.agilis.units.TDDUnits;

import java.util.Calendar;

public class CentralHeatingUnitTDD {

    private static CentralHeatingUnitTDD instance;

    private Calendar calendar;
    private int heatingTemperature;

    public static CentralHeatingUnitTDD getInstance(){
        if(instance==null){
            instance=new CentralHeatingUnitTDD();
        }
        instance.resetUnit();
        return instance;
    }

    private CentralHeatingUnitTDD(){
        heatingTemperature=0;
    }

    private void resetUnit(){
        heatingTemperature=0;
    }

    public void adjustTemperature() {
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

    public int getHeatingTemperature() {
        return heatingTemperature;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
