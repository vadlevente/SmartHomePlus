package com.agilis.units;

import com.agilis.MainActivity;

import java.util.Calendar;

public class CentralHeatingUnit {

    private static CentralHeatingUnit instance;

    private Calendar calendar;

    private int heatingTemperature; //celsius

    private boolean isManual = false;  // set the temperature manually or calculate it based on the calendar

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
        if (isManual){
            try {
                heatingTemperature = Integer.parseInt(MainActivity.manualTemperatureET.getText().toString());
            } catch (NumberFormatException e) {
                //e.printStackTrace();
            }
        } else {
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
    }

    public int getHeatingTemperature(){
        return heatingTemperature;
    }


    public void setManual(boolean isManual){ this.isManual = isManual; }
    public boolean isManual(){ return this.isManual; }

}
