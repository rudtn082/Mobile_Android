package com.example.kyungsoo.mp_05_201604140;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MP_05_201604140 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView_2(this));
        //setContentView(new MyView_6(this));
    }
}
