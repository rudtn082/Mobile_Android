package com.example.kyungsoo.mp01_09_201604140;

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
    int value = 0;
    int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmovie);
        mname = (EditText) findViewById(R.id.mname);
        myear = (EditText) findViewById(R.id.myear);
        mdirector = (EditText) findViewById(R.id.mdirector);
        mrate = (EditText) findViewById(R.id.mrate);
        mcountry = (EditText) findViewById(R.id.mcountry);

        save = (Button) findViewById(R.id.save);
        update = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);

        helper = new DBHelper(this);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = helper.getReadableDatabase();
        }


        try {
            value = getIntent().getExtras().getInt("value");
            type = getIntent().getExtras().getInt("type");
        } catch (Exception e) {
            Log.e("sdsd", e.toString());
        }

        // 추가 버튼을 통해 들어왔을 때  save버튼 보이도록
        if (type == 1) save.setVisibility(View.VISIBLE);
            // 리스트를 통해 들어왔을 때  save버튼 안 보이도록
        else if (type == 2) {
            save.setVisibility(View.INVISIBLE);

            try {
                Cursor cursor = db.rawQuery("select * from movies where _id = " + value + ";", null);
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
                    Cursor res = db.rawQuery("SELECT _id FROM movies order by _id desc limit 1 ", null);
                    res.moveToNext();
                    int a;
                    if (res.getCount() == 0) {
                        a = 1;
                    } else a = res.getInt(0) + 1;
                    db.execSQL("INSERT INTO movies VALUES (" + a + ",'" + name + "'," + year + ",'" + director + "'," + rate + ",'" + country + "');");
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
                try {
                    db.execSQL("update movies set name ='" + mname.getText() + "'" + "where _id = " + value + ";");
                    db.execSQL("update movies set year ='" + myear.getText() + "'" + "where _id = " + value + ";");
                    db.execSQL("update movies set director ='" + mdirector.getText() + "'" + "where _id = " + value + ";");
                    db.execSQL("update movies set rate ='" + mrate.getText() + "'" + "where _id = " + value + ";");
                    db.execSQL("update movies set country ='" + mcountry.getText() + "'" + "where _id = " + value + ";");
                    Toast.makeText(getApplicationContext(), "수정 완료", Toast.LENGTH_LONG).show();
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

        // delete 눌렀을 때
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    db.execSQL("delete FROM movies where name ='" + mname.getText() + "';");
                    Toast.makeText(getApplicationContext(), "삭제 완료", Toast.LENGTH_LONG).show();
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
    }
}
