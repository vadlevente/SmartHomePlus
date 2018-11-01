package com.agilis.units;

import java.util.Calendar;

import static java.lang.Math.abs;

public class AutomaticShutter {

    private static AutomaticShutter instance;

    private Calendar calendar;

    private int secTo100; //time to move blinds from 0% to 100%

    private int currentState;

    private int daybreak;

    private int midday;

    private int dusk;

    public static AutomaticShutter getInstance() {
        if (instance == null) {
            instance = new AutomaticShutter();
        }
        return instance;

    }

    public AutomaticShutter(){
        secTo100 = 10;
        currentState = getCurrentState();
        setDaybreak(6);
        setMidday(13);
        setDusk(18);
        moveShutter();
    }

    public void moveShutter() {
        calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        try {
            if (hour >= getDaybreak() && hour < getMidday()) {
                if (getCurrentState() != 0) {
                    moveUp(getCurrentState(), 0);
                }
            } else if (hour >= getMidday() && hour < getDusk()) {
                moveDown(getCurrentState(), 80);
            } else if (hour >= getDusk()) {
                moveDown(getCurrentState(), 100);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void setDaybreak(int hour) {
        daybreak = hour;
    }

    public int getDaybreak() {
        return daybreak;
    }

    public void setMidday(int hour) {
        midday = hour;
    }

    public int getMidday() {
        return midday;
    }

    public void setDusk(int hour) {
        dusk = hour;
    }

    public int getDusk() {
        return dusk;
    }

    public int getCurrentState(){
        return currentState;
    }

    public void setState(int state) {
        currentState = state;
    }

    public void moveUp(int startingState, int endState) throws InterruptedException {
        int timeToEnd = Math.max( (int)(secTo100 * abs(startingState - endState) / 100.0), 1);
        //call to Shutter controller API's moveUp() command
        synchronized (this) {
            wait(timeToEnd);
        }
        //call to Shutter controller API's stop() command
        setState(endState);
    }

    public void moveDown(int startingState, int endState) throws InterruptedException {
        int timeToEnd = Math.max( (int)(secTo100 * abs(startingState - endState) / 100.0), 1);
        //This method calls Shutter controller API's moveDown() command
        synchronized (this) {
            wait(timeToEnd);
        }
        //call to Shutter controller API's stop() command
        setState(endState);
    }

}