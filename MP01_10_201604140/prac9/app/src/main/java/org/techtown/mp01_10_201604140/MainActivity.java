package org.techtown.mp01_10_201604140;


import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mOrientation;
    TextView text;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mOrientation = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        text = (TextView) findViewById(R.id.text);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mOrientation,
                SensorManager.SENSOR_DELAY_UI);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
            text.setText("방향 센서값\n\n방위각: " + values[0] + "\n피치:" + values[1]
                    + "\n롤:" + values[2]);
        }
    }
}
