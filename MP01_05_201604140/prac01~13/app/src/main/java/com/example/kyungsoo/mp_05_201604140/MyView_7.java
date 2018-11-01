package com.example.kyungsoo.mp_05_201604140;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

public class MyView_7 extends View {
    public MyView_7(Context context) {
        super(context);
        setBackgroundColor(Color.YELLOW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
        canvas.drawBitmap(b, 0, 0, null);
    }

    public class ImageDispActivity extends Activity {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            MyView_7 w = new MyView_7(this);
            setContentView(w);
        }
    }
}
