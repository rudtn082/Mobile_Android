package com.example.kyungsoo.mp01_09_201604140;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DBHelper helper;
    SQLiteDatabase db;
    final ArrayList<String> todoItems = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list = (ListView) findViewById(R.id.list);
        Button add = (Button) findViewById(R.id.add);

        helper = new DBHelper(this);
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM movies", null);
        startManagingCursor(cursor);

        for (int i = 0; i<cursor.getColumnCount(); i++) {
            todoItems.add(cursor.getString(i));
            cursor.moveToNext();
        }

        final ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(  this,
                android.R.layout.simple_list_item_1,
                todoItems
        );
        list.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, addmovie.class);
                startActivityForResult(intent, 1);
            }
        });
    }
}
