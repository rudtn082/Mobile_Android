package com.example.kyungsoo.mp01_08_201604140;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

public class MainActivity extends AppCompatActivity {
    LunarView lunarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lunarView = new LunarView(this);
        setContentView(lunarView);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        lunarView.KeyDown(keyCode, event);
        return super.onKeyDown(keyCode, event);
    }
}

