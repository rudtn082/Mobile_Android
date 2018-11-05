package com.example.kyungsoo.mp01_06_201604140;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void myListener (View target) {
        Intent intent = new Intent(getApplicationContext(), IntroActivity.class);
        startActivity(intent);
    }

    public void myListener1 (View target) {
        Intent intent = new Intent(getApplicationContext(), SetupActivity.class);
        startActivity(intent);
    }

    public void myListener2 (View target) {
        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
        startActivity(intent);
    }
}
