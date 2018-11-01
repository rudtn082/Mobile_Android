package com.example.kyungsoo.mp_05_201604140;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class MyView_6 extends View {
    public MyView_6(Context context) {
        super(context);
        setBackgroundColor(Color.YELLOW);
    }
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        Paint paint = new Paint();

        paint.setStyle(Paint.Style.STROKE);

        path.moveTo(20, 400);
        path.lineTo(300, 800);
        path.cubicTo(450, 120, 600, 1200, 900, 800);

        paint.setColor(Color.BLUE);
        canvas.drawPath(path, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(200);
        canvas.drawTextOnPath("This is a test!!", path, 0, 0, paint);
    }
}