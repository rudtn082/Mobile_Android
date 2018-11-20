package com.example.kyungsoo.mp01_08_201604140;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ProgressBar;

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

