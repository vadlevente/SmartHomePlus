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
import com.agilis.units.AutomaticShutter;
import com.agilis.units.CentralHeating;
import com.agilis.units.CoffeeMachine;
import com.agilis.units.WateringSystem;

public class MainActivity extends AppCompatActivity{

    CentralHeating centralHeating;
    AutomaticShutter automaticShutter;
    CoffeeMachine coffeeMachine;
    WateringSystem wateringSystem;

    public static TextView temperatureTV;
    public static TextView shutterTV;
    public static TextView moistureLevelTV;
    public static TextView wateringSystemStateTV;
    //Button refreshBtn;
    ToggleButton manualTemperatureTBtn;
    LinearLayout manualTemperatureLL;
    public static EditText manualTemperatureET;
    public static TextView coffeeStatusTV;
    public static EditText coffeeTimeHourET;
    public static EditText coffeeTimeMinuteET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        centralHeating=CentralHeating.getInstance();
        automaticShutter=AutomaticShutter.getInstance();
        coffeeMachine=CoffeeMachine.getInstance();
        wateringSystem= WateringSystem.getInstance();

        temperatureTV=(TextView)findViewById(R.id.temperatureTV);
        temperatureTV.setText(String.valueOf(centralHeating.getHouseTemperature()));

        shutterTV=(TextView)findViewById(R.id.shutterTV);
        shutterTV.setText(String.valueOf(automaticShutter.getCurrentState())+"%");

        moistureLevelTV = findViewById(R.id.MoistureLevelTV);
        moistureLevelTV.setText(wateringSystem.getMoistureText());
        wateringSystemStateTV = findViewById(R.id.WateringSystemStateTV);
        wateringSystemStateTV.setText(wateringSystem.getStateText());


        /*
        refreshBtn = (Button)findViewById(R.id.refreshBtn);
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temperatureTV.setText(String.valueOf(centralHeating.getHauseTemperature()));
                shutterTV.setText(String.valueOf(automaticShutter.getCurrentState())+"%");
                coffeeStatusTV.setText(CoffeeMachine.stateToString(coffeeMachine.getState()));
            }
        });
        */

        manualTemperatureTBtn = (ToggleButton)findViewById(R.id.manualTemperatureTBtn);
        manualTemperatureLL = (LinearLayout)findViewById(R.id.manualTemperatureLL);
        manualTemperatureET = (EditText) findViewById(R.id.manualTemperatureET);
        manualTemperatureTBtn.setChecked(false);
        manualTemperatureLL.setVisibility(LinearLayout.INVISIBLE);
        manualTemperatureTBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    centralHeating.setManual(true);
                    manualTemperatureLL.setVisibility(LinearLayout.VISIBLE);
                } else {
                    centralHeating.setManual(false);
                    manualTemperatureLL.setVisibility(LinearLayout.INVISIBLE);
                }
            }
        });

        coffeeStatusTV = (TextView)findViewById(R.id.coffeeStatusTV);
        coffeeStatusTV.setText(CoffeeMachine.stateToString(coffeeMachine.getState()));
        coffeeTimeHourET = (EditText) findViewById(R.id.coffeeTimeHourET);
        coffeeTimeMinuteET = (EditText) findViewById(R.id.coffeeTimeMinuteET);

        Intent intentClockService = new Intent(this, ClockService.class);
        startService(intentClockService);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, ClockService.class);
        stopService(intent);
    }


}
