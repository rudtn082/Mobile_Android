package com.example.kyungsoo.mp01_08_201604140;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LunarView lunarView = new LunarView(this);
        setContentView(lunarView);
    }

    public class LunarView extends SurfaceView implements SurfaceHolder.Callback {
        private LunarThread thread;
        public Handler mHandler;
        public SurfaceHolder mSurfaceHolder;

        public LunarView(Context context) {
            super(context);
            mSurfaceHolder = getHolder();
            mSurfaceHolder.addCallback(this);
            thread = new LunarThread();
        }
        public void surfaceCreated(SurfaceHolder holder) {
            thread.setRunning(true);
            thread.start();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            boolean retry = true;
            thread.setRunning(false);
            while(retry) {
                try {
                    thread.join();
                    retry = false;
                }catch (InterruptedException e) {

                }
            }
        }

        class LunarThread extends Thread {
            private boolean mRun = false;
            private int x = 0;
            private int y = 0;

            public void setRunning(boolean b) {
                mRun = b;
            }

            @Override
            public void run() {
                while (mRun) {
                    Canvas c = null;
                    try {
                        c = mSurfaceHolder.lockCanvas(null);
                        synchronized (mSurfaceHolder) {
                            doDraw(c);
                        }
                    }finally {
                        if(c != null) {
                            mSurfaceHolder.unlockCanvasAndPost(c);
                        }
                    }
                }
            }

            private void doDraw(Canvas canvas) {
                Bitmap mBackgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.earthrise);
                Bitmap a = mBackgroundImage.createScaledBitmap(mBackgroundImage, canvas.getWidth(), canvas.getHeight(), false);
                Drawable mLanderImage = getResources().getDrawable(R.drawable.app_lunar_lander);

                canvas.drawBitmap(a, 0, 0, null);
                mLanderImage.setBounds(x++, y++, x + 200, y + 200);
                if( x > canvas.getWidth()) x = 0;
                if( y > canvas.getHeight()) y = 0;
                mLanderImage.draw(canvas);
            }
        }
    }
}

