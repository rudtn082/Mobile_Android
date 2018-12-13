package org.techtown.mp01_10_201604140;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import java.util.Random;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private TextView number, seeknum;
    private TextView a1,a2,a3;
    private Button reset;
    private SeekBar seekBar;
    private int seeknumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number = (TextView) findViewById(R.id.number);
        seeknum = (TextView) findViewById(R.id.seeknum);
        a1 = (TextView) findViewById(R.id.text1);
        a2 = (TextView) findViewById(R.id.text2);
        a3 = (TextView) findViewById(R.id.text3);
        reset = (Button) findViewById(R.id.reset);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number.setText("0");
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seeknumber = progress;
                seeknum.setText(String.valueOf(seeknumber));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        float xyz = (float) Math.sqrt(x + y + z);

        double tempFloat = 0.1 * seeknumber;

        a1.setText(String.valueOf(x));
        a2.setText(String.valueOf(y));
        a3.setText(String.valueOf(z));

        if (xyz > 3+tempFloat && seeknumber > 0) {
            number.setText(String.valueOf(Integer.parseInt(number.getText().toString())+1));
        }
    }

}

