package com.agilis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.agilis.services.HeatingService;
import com.agilis.units.CentralHeatingUnit;

public class MainActivity extends AppCompatActivity{

    CentralHeatingUnit centralHeatingUnit;

    TextView temperatureTV;
    Button refreshBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        centralHeatingUnit=CentralHeatingUnit.getInstance();

        temperatureTV=(TextView)findViewById(R.id.temperatureTV);
        temperatureTV.setText(String.valueOf(centralHeatingUnit.getHeatingTemperature()));

        refreshBtn = (Button)findViewById(R.id.refreshBtn);
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temperatureTV.setText(String.valueOf(centralHeatingUnit.getHeatingTemperature()));
            }
        });

        Intent intent = new Intent(this, HeatingService.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, HeatingService.class);
        stopService(intent);
    }


}
