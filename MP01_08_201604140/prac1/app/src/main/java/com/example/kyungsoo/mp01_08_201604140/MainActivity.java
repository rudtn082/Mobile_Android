package com.example.kyungsoo.mp01_08_201604140;

import android.support.annotation.WorkerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    WorkerThread w;
    boolean running = true;

    class WorkerThread extends Thread {
        public void run() {
            int i = 0;
            for(i = 0; i < 20 && running; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                Log.v("THREAD", "time=" + i);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onStart() {
        super.onStart();
        w = new WorkerThread();
        running = true;
        w.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        running = false;
    }
}
