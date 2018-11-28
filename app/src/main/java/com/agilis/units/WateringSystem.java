package com.agilis.units;

import android.app.Activity;
import android.app.Application;
import android.content.ContentProvider;
import android.content.Context;
import android.widget.TextView;

import com.agilis.R;

public class WateringSystem {
    public enum State {STATE_OFF, STATE_LOW, STATE_HIGH}

    //0 is completely dry 100 is sufficiently damp
    private int moisturePercentage = 0;

    private static WateringSystem instance;

    private State currentState = State.STATE_OFF;

    public static WateringSystem getInstance() {
        if (instance == null) {
            instance = new WateringSystem();
            instance.sendMoistureSensorData(27);
        }
        return instance;

    }

    public State getState() {
        return currentState;
    }

    public void sendMoistureSensorData(int percentage) {
        moisturePercentage = percentage;
        if(moisturePercentage <= 50) {
            setState(State.STATE_HIGH);
        } else if (moisturePercentage <= 75) {
            setState(State.STATE_LOW);
        } else {
            setState(State.STATE_OFF);
        }
    }

    private void setState(State newState) {
        //Start/Stop the system
        if(newState != currentState) {
            currentState = newState;
            //Do some stuff to the actual system
            //setThroughput(HIGH);
        }
    }

    public int getMoisturePercentage() {
        return moisturePercentage;
    }

    public String getMoistureText() {
        String ret = "Nedvesség: " + moisturePercentage + "%";
        return ret;
    }

    public String getStateText() {
        String ret = "Locsolórendszer állapota: ";
        switch (currentState) {
            case STATE_LOW:
                ret += "Alacsony";
                break;
            case STATE_OFF:
                ret += "Kikapcsolva";
                break;
            case STATE_HIGH:
                ret += "Magas";
                break;
        }
        return ret;
    }


}
