package com.speed.proximity_sensor;

import static com.speed.proximity_sensor.R.string.Objectisnear;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE); //we get sensor value but pc think we call a object value hense the typecast is genrated

        if(sensorManager!=null){
            Sensor proxiSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

            if(proxiSensor!=null){
                sensorManager.registerListener(this,proxiSensor,sensorManager.SENSOR_DELAY_NORMAL);
            }
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_PROXIMITY){
            ((TextView)findViewById(R.id.s_v)).setText("Values: " + event.values[0]);
        }
          if(event.values[0] > 0){
               Toast.makeText(this, R.string.Objectisfar,Toast.LENGTH_SHORT).show();

           }else{
               Toast.makeText(this, Objectisnear,Toast.LENGTH_SHORT).show();
           }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}