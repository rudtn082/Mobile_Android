package org.techtown.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SingleTouchView extends View {
    private Paint paint = new Paint();
    private Path path = new Path();

    public  SingleTouchView(Context context, AttributeSet attr) {
        super(context, attr);

        paint.setAntiAlias(true);
        paint.setStrokeWidth(10f);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paint);
    }

    public boolean onTouchEvent(MotionEvent event) {
        float evnetX = event.getX();
        float eventY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: path.moveTo(evnetX, eventY);
                return true;
            case MotionEvent.ACTION_MOVE: path.lineTo(evnetX, eventY);
            break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }
}
