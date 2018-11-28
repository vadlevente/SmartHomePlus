package com.agilis.units;

import com.agilis.MainActivity;

import java.util.Calendar;

public class CentralHeating {

    private static CentralHeating instance;

    private Calendar calendar;
    private int heatingTemperature;
    private int houseTemperature;

    private boolean isManual;  // set the temperature manually or calculate it based on the calendar

    public static CentralHeating getInstance(){
        if(instance==null){
            instance=new CentralHeating();
        }
        return instance;
    }

    private CentralHeating(){
        resetUnit();
        adjustTemperature();
    }

    private void resetUnit(){
        calendar = Calendar.getInstance();
        heatingTemperature = 0;
        houseTemperature = 0;
        isManual = false;
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

        houseTemperature = (int)Math.round((houseTemperature + 2*heatingTemperature) / 3.0);
    }

    public int getHeatingTemperature() {
        return heatingTemperature;
    }
    public void setHeatingTemperature(int heatingTemperature) {
        this.heatingTemperature = heatingTemperature;
    }

    public int getHouseTemperature() {
        return houseTemperature;
    }
    public void setHouseTemperature(int houseTemperature) {
        this.houseTemperature = houseTemperature;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }


    public void setManual(boolean isManual){ this.isManual = isManual; }
    public boolean isManual(){ return this.isManual; }

}
