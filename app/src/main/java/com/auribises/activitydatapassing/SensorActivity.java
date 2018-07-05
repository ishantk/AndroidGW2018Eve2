package com.auribises.activitydatapassing;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SensorActivity extends AppCompatActivity implements View.OnClickListener, SensorEventListener {

    TextView txtData;
    Button btnShake;

    SensorManager sensorManager;
    Sensor sensor;

    void initViews() {
        txtData = findViewById(R.id.textViewData);
        btnShake = findViewById(R.id.buttonShake);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        initViews();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonShake) {

            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];


        // for Proximity Sensor
        float[] values1 = event.values;
        float dist = values1[0];

        if (dist == 0) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:+91 99155 71177"));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this,"Please Grant Permissions from Settings",Toast.LENGTH_LONG).show();
            }else {
                startActivity(intent);
            }
        }


        float cal = ((x*x)+(y*y)+(z*z))/(SensorManager.GRAVITY_EARTH*SensorManager.GRAVITY_EARTH);

        if(cal>3){
            txtData.setText("Shake Detected !!");
            sensorManager.unregisterListener(this);

            // Request LocationManager
        }else{
            txtData.setText("Sensor Data : "+x+" : "+y+" : "+z);
        }



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onDestroy() {

        sensorManager.unregisterListener(this);

        super.onDestroy();
    }
}
