package com.agilis.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.agilis.units.TDDUnits.CentralHeatingUnitTDD;

import java.util.Timer;
import java.util.TimerTask;

public class HeatingService extends Service {

    Timer timer;
    long period=1000;

    public HeatingService() {
        timer=new Timer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                CentralHeatingUnitTDD.getInstance().adjustTemperature();
            }
        }, 0, period);

        return START_STICKY;
    }



    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
