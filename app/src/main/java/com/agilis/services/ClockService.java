package com.agilis.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.agilis.MainActivity;
import com.agilis.units.AutomaticShutter;
import com.agilis.units.CentralHeating;

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


            }
        }, 0, period);

        return START_STICKY;
    }



    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}