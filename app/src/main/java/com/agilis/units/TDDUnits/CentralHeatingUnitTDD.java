package com.agilis.units.TDDUnits;

import com.agilis.MainActivity;

import java.util.Calendar;

public class CentralHeatingUnitTDD {

    private static CentralHeatingUnitTDD instance;

    private Calendar calendar = Calendar.getInstance();
    private int heatingTemperature = 0;

    private boolean isManual = false;  // set the temperature manually or calculate it based on the calendar

    public static CentralHeatingUnitTDD getInstance(){
        if(instance==null){
            instance=new CentralHeatingUnitTDD();
        }
        return instance;
    }

    private CentralHeatingUnitTDD(){
        adjustTemperature();
    }

    private void resetUnit(){
        heatingTemperature=0;
    }

    public void adjustTemperature() {
        if (!isManual){
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
    }

    public int getHeatingTemperature() {
        return heatingTemperature;
    }
    public void setHeatingTemperature(int heatingTemperature) {
        this.heatingTemperature = heatingTemperature;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }


    public void setManual(boolean isManual){ this.isManual = isManual; }
    public boolean isManual(){ return this.isManual; }

}
