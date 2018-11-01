package com.agilis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.agilis.services.ClockService;
import com.agilis.services.HeatingService;
import com.agilis.units.AutomaticShutter;
import com.agilis.units.TDDUnits.CentralHeatingUnitTDD;

public class MainActivity extends AppCompatActivity{

    CentralHeatingUnitTDD centralHeatingUnitTDD;
    AutomaticShutter automaticShutter;

    TextView temperatureTV;
    TextView shutterTV;
    Button refreshBtn;
    ToggleButton manualTemperatureTBtn;
    LinearLayout manualTemperatureLL;
    public static EditText manualTemperatureET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        centralHeatingUnitTDD=CentralHeatingUnitTDD.getInstance();
        automaticShutter=AutomaticShutter.getInstance();

        temperatureTV=(TextView)findViewById(R.id.temperatureTV);
        temperatureTV.setText(String.valueOf(centralHeatingUnitTDD.getHeatingTemperature()));

        shutterTV=(TextView)findViewById(R.id.shutterTV);
        shutterTV.setText(String.valueOf(automaticShutter.getCurrentState())+"%");

        refreshBtn = (Button)findViewById(R.id.refreshBtn);
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temperatureTV.setText(String.valueOf(centralHeatingUnitTDD.getHeatingTemperature()));
                shutterTV.setText(String.valueOf(automaticShutter.getCurrentState())+"%");
            }
        });

        manualTemperatureTBtn = (ToggleButton)findViewById(R.id.manualTemperatureTBtn);
        manualTemperatureLL = (LinearLayout)findViewById(R.id.manualTemperatureLL);
        manualTemperatureET = (EditText) findViewById(R.id.manualTemperatureET);
        manualTemperatureTBtn.setChecked(false);
        manualTemperatureLL.setVisibility(LinearLayout.GONE);
        manualTemperatureTBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    centralHeatingUnitTDD.setManual(true);
                    manualTemperatureLL.setVisibility(LinearLayout.VISIBLE);
                } else {
                    centralHeatingUnitTDD.setManual(false);
                    manualTemperatureLL.setVisibility(LinearLayout.GONE);
                }
            }
        });

        Intent intentHeatingService = new Intent(this, HeatingService.class);
        startService(intentHeatingService);

        Intent intentClockService = new Intent(this, ClockService.class);
        startService(intentClockService);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, HeatingService.class);
        stopService(intent);
    }


}
