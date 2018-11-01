package com.agilis.services;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import android.os.Looper;
import com.agilis.MainActivity;
import com.agilis.units.AutomaticShutter;
import com.agilis.units.CentralHeating;
import com.agilis.units.CoffeeMachine;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class ClockService extends Service {

    Timer timer;
    long period=1000;

    public ClockService() {
        timer = new Timer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                /*** Central Heating Unit ***/
                CentralHeating centralHeating = CentralHeating.getInstance();
                if (centralHeating.isManual()){
                    try {
                        int heatingTemperature = Integer.parseInt(MainActivity.manualTemperatureET.getText().toString());
                        centralHeating.setHeatingTemperature(heatingTemperature);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                centralHeating.adjustTemperature();


                /*** Automatic Shutter Unit ***/
                AutomaticShutter.getInstance().moveShutter();


                /*** Coffee Machine Unit ***/
                CoffeeMachine coffeeMachine = CoffeeMachine.getInstance();
                try {
                    int coffeeTimeHour = Integer.parseInt(MainActivity.coffeeTimeHourET.getText().toString());
                    int coffeeTimeMinute = Integer.parseInt(MainActivity.coffeeTimeMinuteET.getText().toString());
                    coffeeMachine.setCoffeeTime(coffeeTimeHour,coffeeTimeMinute);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                coffeeMachine.setCalendar(Calendar.getInstance());
                coffeeMachine.checkState();


                /*** Refresh client app values ***/
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        MainActivity.temperatureTV.setText(String.valueOf(CentralHeating.getInstance().getHauseTemperature()));
                        MainActivity.shutterTV.setText(String.valueOf(AutomaticShutter.getInstance().getCurrentState())+"%");
                        MainActivity.coffeeStatusTV.setText(CoffeeMachine.stateToString(CoffeeMachine.getInstance().getState()));
                    }
                });

            }
        }, 0, period);

        return START_STICKY;
    }



    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}