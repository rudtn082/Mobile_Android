package com.example.kyungsoo.mp01_08_201604140;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private ProgressBar mProgress;
    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        Button bt = (Button)findViewById(R.id.button);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(mProgressStatus < 100) {
                            try {
                                Thread.sleep(1000);
                            }catch (InterruptedException e) {
                            }
                            mProgressStatus = i++;
                            mHandler.post(new Runnable() {
                                public void run() {
                                    mProgress.setProgress(mProgressStatus);
                                }
                            });
                        }
                    }
                }).start();
            }
        });

    }
}
