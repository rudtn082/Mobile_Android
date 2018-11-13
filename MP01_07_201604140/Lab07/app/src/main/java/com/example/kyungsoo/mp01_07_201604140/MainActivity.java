package com.example.kyungsoo.mp01_07_201604140;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SensorManager mSensorManager = null;
    private SensorEventListener mAccLis;
    private Sensor mAccelometerSensor = null;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textview);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mAccelometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mAccLis = new AccelometerListener();

        mSensorManager.registerListener(mAccLis, mAccelometerSensor, SensorManager.SENSOR_DELAY_UI);
    }

    private class AccelometerListener implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent event) {
            double accX = event.values[0];
            double accY = event.values[1];
            double accZ = event.values[2];

            Log.d("LOG", "\n[0] = " + String.valueOf(event.values[0])
                            + "\n[1] = " + String.valueOf(event.values[1])
                            + "\n[2] = " + String.valueOf(event.values[2]));


            ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningServiceInfo> procInfos = am.getRunningServices(1);

            if(event.values[1] == -9.8100004196167) {
                if(procInfos.size() != 1)
                    startService(new Intent(getApplicationContext(), MusicStart.class));
            }
            else if(event.values[1] == 9.8100004196167) {
                if(procInfos.size() != 0)
                    stopService(new Intent(getApplicationContext(), MusicStart.class));
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d("LOG", "onPause()");
        mSensorManager.unregisterListener(mAccLis);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("LOG", "onDestroy()");
        mSensorManager.unregisterListener(mAccLis);
    }
}
