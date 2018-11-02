package com.example.kyungsoo.mp01_05_201604140;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MP01_05_201604140 extends AppCompatActivity {
    private MyView mMyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp01_05_201604140);
        mMyView = (MyView)findViewById(R.id.myview);
    }

    public void redraw(View v) {
        mMyView.invalidate();
    }
}
