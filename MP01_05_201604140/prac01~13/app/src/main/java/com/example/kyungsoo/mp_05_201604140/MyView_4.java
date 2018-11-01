package com.example.kyungsoo.mp_05_201604140;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class MyView_4 extends View {
    private Paint mPains, mFramePaint;
    private RectF mBigOval;
    private float mStart, mSweep;
    private static final float SWEEP_INC = 2;
    private static final float START_INC = 15;
    public MyView_4(Context context) {
        super(context);
        mPains = new Paint();
        mPains.setAntiAlias(true);
        mPains.setStyle(Paint.Style.FILL);
        mPains.setColor(0x88FF0000);
        mFramePaint = new Paint();
        mFramePaint.setAntiAlias(true);
        mFramePaint.setStyle(Paint.Style.STROKE);

        mFramePaint.setStrokeWidth(3);
        mFramePaint.setColor(0x8800ff00);
        mBigOval = new RectF(100, 40, 900, 1000);
    }

    protected  void onDraw(Canvas canvas) {
        canvas.drawColor(Color.YELLOW);
        canvas.drawRect(mBigOval,mFramePaint);
        canvas.drawArc(mBigOval, mStart, mSweep, false, mPains);
        mSweep += SWEEP_INC;
        if(mSweep > 360) {
            mSweep -= 360;
            mStart += START_INC;
            if(mStart >= 360)
                mStart -= 360;
        }
        invalidate();
    }

}
