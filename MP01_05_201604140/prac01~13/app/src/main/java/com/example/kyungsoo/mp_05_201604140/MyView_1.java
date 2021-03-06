package com.example.kyungsoo.mp_05_201604140;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyView_1 extends View {
    public MyView_1(Context context){
        super(context);
        setBackgroundColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(20);
        canvas.drawLine(100, 100, 700, 100, paint);
        canvas.drawRect(100, 300, 700, 700, paint);
        canvas.drawCircle(300, 1200, 200, paint);
        paint.setTextSize(80);
        canvas.drawText("This is a text.", 100, 900, paint);
    }
}
