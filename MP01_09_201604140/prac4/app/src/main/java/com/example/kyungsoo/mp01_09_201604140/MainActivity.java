package com.example.kyungsoo.mp01_09_201604140;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    dbHelper helper;
    SQLiteDatabase db;
    EditText edit_name, edit_tel;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new dbHelper(this);
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM contacts", null);
        startManagingCursor(cursor);
        String[] from = {"name", "tel"};
        int[] to = { android.R.id.text1, android.R.id.text2 };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2, cursor, from, to);
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
    }
}
