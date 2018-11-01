package com.agilis.units;

import java.util.Calendar;

public class CoffeeMachine {

    public static final int STATE_STANDBY = 0;
    public static final int STATE_WORKING = 1;
    public static final int STATE_READY = 2;

    private static CoffeeMachine instance;

    private int state;
    private Calendar calendar;

    public static CoffeeMachine getInstance(){
        if(instance==null){
            instance=new CoffeeMachine();
        }
        return instance;
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
        // TODO
    }

    public void checkState(){
        // TODO
    }

    public int getState(){
        return state;
    }

    public void setCalendar(Calendar calendar){
        this.calendar = calendar;
    }

}
