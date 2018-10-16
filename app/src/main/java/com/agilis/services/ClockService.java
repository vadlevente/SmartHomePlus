package com.agilis.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.agilis.units.AutomaticShutter;

import java.util.Timer;
import java.util.TimerTask;

public class ClockService extends Service {

    Timer timer;
    long period=3600000;

    public ClockService() {
        timer = new Timer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
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