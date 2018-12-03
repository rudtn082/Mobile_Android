package com.example.kyungsoo.mp01_09_201604140;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addmovie extends AppCompatActivity {
    EditText mname, myear, mdirector, mrate, mcountry;
    Button save, update, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmovie);
        mname = (EditText)findViewById(R.id.mname);
        myear = (EditText)findViewById(R.id.myear);
        mdirector = (EditText)findViewById(R.id.mdirector);
        mrate = (EditText)findViewById(R.id.mrate);
        mcountry = (EditText)findViewById(R.id.mcountry);

        save = (Button) findViewById(R.id.save);
        update = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);

        // save 눌렀을 때
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

        // update 눌렀을 때
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // delete 눌렀을 때
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
