package com.example.kyungsoo.mp01_04_201604140;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MP01_04_201604140 extends AppCompatActivity {
    final Calendar c = Calendar.getInstance();
    int mYear = c.get(Calendar.YEAR);
    int mMonth = c.get(Calendar.MONTH);
    int mDay = c.get(Calendar.DAY_OF_MONTH);
    int mHour = c.get(Calendar.HOUR_OF_DAY);
    int mMinute = c.get(Calendar.MINUTE);
    private static final float FONT_SIZE = 20;
    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp01_04_201604140);
    }

    private void timepic(final String s) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String result = s.concat(String.valueOf(hourOfDay) + ":" + String.valueOf(minute));
                setContentView(R.layout.activity_mp01_04_201604140);
                //부모 뷰
                container = (LinearLayout) findViewById(R.id.wholeArea);

                //TextView 생성
                TextView view1 = new TextView(getApplicationContext());
                view1.setText(result);
                view1.setTextSize(FONT_SIZE);
                view1.setTextColor(Color.BLACK);

                //layout_width, layout_height, gravity 설정
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.gravity = Gravity.CENTER;
                view1.setLayoutParams(lp);

                //부모 뷰에 추가
                container.addView(view1);
            }
        }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private void datepic() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String s = String.valueOf(year) + "." +String.valueOf(monthOfYear+1) + "." + String.valueOf(dayOfMonth) + "-";
                timepic(s);
            }
        }, mYear, mMonth,mDay);
        datePickerDialog.show();
    }

    public void onClick_Button(View view) {
        datepic();
    }
}
