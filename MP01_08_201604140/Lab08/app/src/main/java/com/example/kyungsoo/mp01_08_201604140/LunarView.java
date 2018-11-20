package com.example.kyungsoo.mp01_08_201604140;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

public class LunarView extends SurfaceView implements SurfaceHolder.Callback {
    public Handler mHandler = new Handler();
    private int fuel = 100; // 연료
    private View view;
    private LunarThread thread;
    public SurfaceHolder mSurfaceHolder;
    Drawable mLanderImage; // 우주선 이미지
    Paint paint;
    boolean booststop; // 부스터
    private int x = 0; // 좌표 x
    private int y = 0; // 좌표 y

    public LunarView(Context context) {
        super(context);
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        thread = new LunarThread();
        paint = new Paint();
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
                Log.e("asd", e.toString());
            }
        }
    }

    class LunarThread extends Thread {
        private boolean mRun = false;

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
                }catch (Exception e){
                    Log.e("asd", e.toString());
                }
                finally {
                    if(c != null) {
                        mSurfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }

        private void doDraw(Canvas canvas) {
            try {
                Bitmap mBackgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.earthrise);
                Bitmap a = mBackgroundImage.createScaledBitmap(mBackgroundImage, canvas.getWidth(), canvas.getHeight(), false);
                mLanderImage = getResources().getDrawable(R.drawable.app_lunar_lander);


                // 우주선 그리기
                canvas.drawBitmap(a, 0, 0, null);
                mLanderImage.setBounds(x++, y++, x + 200, y + 200);
                if( x > canvas.getWidth()) x = 0;
                if( y >  canvas.getHeight()) y = 0;
                mLanderImage.draw(canvas);

                // 연료 그리기
                paint.setColor(Color.BLUE);
                paint.setStrokeWidth(30f);
                canvas.drawLine(0, 0, fuel, 0, paint);
            }catch (Exception e){
                Log.e("asd", e.toString());
            }
        }
    }

    // 부스터 함수
    private void booster() {
        mHandler.postDelayed(new Runnable() {
            public void run() {
                fuel--;
                if(booststop)
                    mHandler.postDelayed(this, 100);
            }
        }, 100);  // 2000은 2초를 의미합니다.
    }

    public void KeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_DPAD_UP) {
            booststop = true;
            booster();
        }
        else if(keyCode == event.KEYCODE_DPAD_DOWN) {
            booststop = false;
        }
        else if(keyCode == event.KEYCODE_DPAD_RIGHT) {
        }
        else if(keyCode == event.KEYCODE_DPAD_LEFT) {

        }
    }
}
