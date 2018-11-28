package com.agilis.units;

import java.util.Calendar;

public class CoffeeMachine {

    public static final int STATE_STANDBY = 0;
    public static final int STATE_WORKING = 1;
    public static final int STATE_READY = 2;

    private static final int TIME_TO_MAKE_COFFEE = 10; // minutes

    private static CoffeeMachine instance;

    private int state;
    private int coffeeTimeHour;
    private int coffeeTimeMinute;
    private Calendar calendar;

    public static CoffeeMachine getInstance(){
        if(instance==null){
            instance=new CoffeeMachine();
        }
        return instance;
    }

    public CoffeeMachine() {
        coffeeTimeHour = 7;
        coffeeTimeMinute = 50;
        calendar = Calendar.getInstance();
    }

    public static String stateToString(int state){
        switch (state){
            case STATE_STANDBY:
                return "Készenlétben.";
            case STATE_WORKING:
                return "Dolgozik...";
            case STATE_READY:
                return "Kész!";
        }
        return "Error";
    }

    public void setCoffeeTime(int hour, int minute){
        this.coffeeTimeHour = hour;
        this.coffeeTimeMinute = minute;
    }

    public void checkState(){
        int timeToNext = ( (coffeeTimeHour+24)*60+coffeeTimeMinute
                           - (calendar.get(Calendar.HOUR_OF_DAY)*60+calendar.get(Calendar.MINUTE))
                         ) % (24*60);
        if (timeToNext==0 || timeToNext>12*60){
            this.state = STATE_READY;
        } else if (timeToNext<TIME_TO_MAKE_COFFEE){
            this.state = STATE_WORKING;
        } else {
            this.state = STATE_STANDBY;
        }
    }

    public int getState(){
        return state;
    }

    public void setCalendar(Calendar calendar){
        this.calendar = calendar;
    }

}
