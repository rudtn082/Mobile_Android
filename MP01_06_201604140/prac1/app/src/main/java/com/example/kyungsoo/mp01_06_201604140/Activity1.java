package com.example.kyungsoo.mp01_06_201604140;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        Button b = (Button)findViewById(R.id.Button1);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Activity1.this, Activity2.class);
                startActivity(intent);
            }
        });
    }
}
