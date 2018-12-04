package com.example.kyungsoo.mp01_09_201604140;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DBHelper helper;
    SQLiteDatabase db;
    final ArrayList<String> todoItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button add = (Button) findViewById(R.id.add);

        // 리스트뷰 생성
        listview();

        // 추가 눌렀을 때
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, addmovie.class);
                intent.putExtra("type", 1);
                startActivityForResult(intent, 1);
            }
        });

        // 리스트 아이템 눌렀을 때
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor res = db.rawQuery("SELECT _id FROM movies where name = '" + todoItems.get(position).substring(2) + "';", null);
                res.moveToNext();

                Intent intent = new Intent(MainActivity.this, addmovie.class);
                intent.putExtra("type", 2);
                intent.putExtra("value", res.getInt(0));
                Log.e("sdsd", String.valueOf(res.getInt(0)));
                startActivityForResult(intent, 1);
            }
        });
    }

    // 리스트 뷰
    private void listview() {
        helper = new DBHelper(this);
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM movies", null);
        startManagingCursor(cursor);

        try {
            todoItems.clear();
            cursor.moveToNext();
            for (int i = 1; i <= cursor.getCount(); i++) {
                todoItems.add(i + " " + cursor.getString(1));
                cursor.moveToNext();
            }

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoItems);
            list = (ListView) findViewById(R.id.list);
            list.setAdapter(adapter);
        } catch (Exception e) {
            Log.e("sdsd", e.toString());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                listview();
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
