package com.example.kyungsoo.mp_05_201604140;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.View;

public class MyView_9 extends View {
    public MyView_9(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Matrix m = new Matrix();
        m.preScale(1, -1);
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.face);
        Bitmap mb = Bitmap.createBitmap(b,0,0,b.getWidth(),b.getHeight(),m,false);
        canvas.drawBitmap(mb,0,0,null);
    }
}
