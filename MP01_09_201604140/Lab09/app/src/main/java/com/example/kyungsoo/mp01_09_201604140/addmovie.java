package com.example.kyungsoo.mp01_09_201604140;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addmovie extends AppCompatActivity {
    EditText mname, myear, mdirector, mrate, mcountry;
    Button save, update, delete;
    DBHelper helper;
    SQLiteDatabase db;

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

        helper = new DBHelper(this);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = helper.getReadableDatabase();
        }

        String value = null;
        int type = 0;

        try {
            value = getIntent().getExtras().getString("value");
            type = getIntent().getExtras().getInt("type");
        } catch (Exception e) {
            Log.e("sdsd", e.toString());
        }

        if(type == 1) save.setVisibility(View.VISIBLE);
        else if (type == 2) {
            save.setVisibility(View.INVISIBLE);

            try {
                Cursor cursor = db.rawQuery("select * from movies where name = '" + value + "';", null);
                startManagingCursor(cursor);
                cursor.moveToNext();

                mname.setText(cursor.getString(1));
                myear.setText(cursor.getString(2));
                mdirector.setText(cursor.getString(3));
                mrate.setText(cursor.getString(4));
                mcountry.setText(cursor.getString(5));
            } catch (Exception e) {
                Log.e("sdsd", e.toString());
            }
        }

        // save 눌렀을 때
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mname.getText().toString();
                String year = myear.getText().toString();
                String director = mdirector.getText().toString();
                String rate = mrate.getText().toString();
                String country = mcountry.getText().toString();

                try {
                    Cursor res = db.rawQuery("SELECT _id FROM movies", null);
                    int a = res.getCount()+1;
                    db.execSQL("INSERT INTO movies VALUES ("+ a + ",'" + name + "'," + year + ",'" + director + "'," + rate + ",'" + country + "');");
                    Toast.makeText(getApplicationContext(), "추가 완료", Toast.LENGTH_LONG).show();
                    mname.setText("");
                    myear.setText("");
                    mdirector.setText("");
                    mrate.setText("");
                    mcountry.setText("");
                } catch (SQLException ex) {
                    Log.e("sdsd", ex.toString());
                } finally {
                    setResult(1);
                    finish();
                }
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
