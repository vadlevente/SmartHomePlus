package com.agilis.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.agilis.MainActivity;
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
                CentralHeatingUnitTDD centralHeatingUnitTDD = CentralHeatingUnitTDD.getInstance();
                if (centralHeatingUnitTDD.isManual()){
                    try {
                        int heatingTemperature = Integer.parseInt(MainActivity.manualTemperatureET.getText().toString());
                        centralHeatingUnitTDD.setHeatingTemperature(heatingTemperature);
                    } catch (NumberFormatException e) {
                        //e.printStackTrace();
                    }
                }
                centralHeatingUnitTDD.adjustTemperature();
            }
        }, 0, period);

        return START_STICKY;
    }



    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
