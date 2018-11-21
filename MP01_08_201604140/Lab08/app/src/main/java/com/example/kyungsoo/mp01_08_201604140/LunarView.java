package com.example.kyungsoo.mp01_08_201604140;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class LunarView extends SurfaceView implements SurfaceHolder.Callback {
    private final int LEFT = 1;
    private final int RIGHT = 2;
    public Handler mHandler = new Handler(); // 핸들러
    Matrix rotateMatrix = new Matrix(); // 회전각도 매트릭스
    private int fuel = 100; // 연료 0~100
    private LunarThread thread;
    public SurfaceHolder mSurfaceHolder;
    private Paint paint;
    private int x = 0, y = 0; // 좌표 x, y
    private boolean boost; // 부스터 T/F
    private int trans = 0; // 키보드 좌, 우 눌렀는지 확인
    private int rotateRate = 0; // 좌우 각도
    private int game = 0; // 게임이 진행중 = 0, over = 1
    private int speed = 1; // 속도 1~200
    private int success = 0; // 성공 횟수

    // 착륙지를 위한 randomValue
    double randomV;
    int intV= 0;

    public LunarView(Context context) {
        super(context);
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        thread = new LunarThread();
        paint = new Paint();

        // 처음 착륙지 랜덤 설정
        randomV = Math.random();
        intV = (int)(randomV * 300);
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
                Log.e("Log", e.toString());
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
                while (game == 0) {
                    try {
                        c = mSurfaceHolder.lockCanvas(null);
                        synchronized (mSurfaceHolder) {
                            doDraw(c);
                        }
                    }catch (Exception e){
                        Log.e("Log", e.toString());
                    }
                    finally {
                        if(c != null) {
                            mSurfaceHolder.unlockCanvasAndPost(c);
                        }
                    }
                }
            }
        }

        // 속도를 리턴하는 함수
        private int speed(){
            int i;
            int tempSpeed = speed;
            for(i = 0; tempSpeed > 0; i++) {
                tempSpeed -= 20;
            }

            return i;
        }

        // 그리기 함수
        private void doDraw(Canvas canvas) {
            try {
                // 백그라운드
                Bitmap mBackgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.earthrise);
                Bitmap scaledBackgroundImage = mBackgroundImage.createScaledBitmap(mBackgroundImage, canvas.getWidth(), canvas.getHeight(), false);
                canvas.drawBitmap(scaledBackgroundImage, 0, 0, null);
                Bitmap lander; // 우주선

                // 우주선 그리기
                // 부스터 여부
                if(boost && fuel >= 0) {
                    lander = BitmapFactory.decodeResource(getResources(), R.drawable.lander_firing);
                    y = y + 1;
                    y = y + speed()/2;
                }
                else {
                    lander = BitmapFactory.decodeResource(getResources(), R.drawable.lander_plain);
                    y = y + 3;
                    y = y + speed();
                }

                // 좌우 방향키
                if(trans == LEFT && rotateRate > -15) {
                    rotateRate = rotateRate-15;
                    rotateMatrix.postRotate(-15);
                    trans = 0;
                }
                else if (trans == RIGHT && rotateRate < 15){
                    rotateRate = rotateRate+15;
                    rotateMatrix.postRotate(15);
                    trans = 0;
                }

                // 각도에 따른 x값
                if(rotateRate <= -15 && game == 0) {
                    x = x - 5;
                }
                else if(rotateRate >= 15 && game == 0) {
                    x = x + 5;
                }

                // 우주선이 창 밖으로 못가도록 처리
                if( x > canvas.getWidth()-200) x = canvas.getWidth()-200;
                if( y > canvas.getHeight()-200) y = canvas.getHeight()-200;
                if( x <= 0) x = 0;
                if( y <= 200) y = 200;

                // 연료 그리기
                if(fuel <= 0) {
                    fuel = 0;
                }
                paint.setColor(Color.RED);
                paint.setStrokeWidth(30f);
                canvas.drawLine(0, 0, fuel, 0, paint);

                // 속도조절
                if(game == 0) {
                    if(boost) {
                        speed = speed-2;
                    }
                    else {
                        speed = speed+2;
                    }
                    if(speed >= 200) {
                        speed = 200;
                    }
                    else if (speed <= 0) {
                        speed = 1;
                    }
                }

                // 속도 그리기
                if(speed <= 100) {
                    paint.setColor(Color.RED);
                    paint.setStrokeWidth(35f);
                    canvas.drawLine(150, 0, 150+speed, 0, paint);
                }
                else if(speed <= 200){
                    paint.setColor(Color.RED);
                    paint.setStrokeWidth(35f);
                    canvas.drawLine(150, 0, 250, 0, paint);
                    paint.setColor(Color.GREEN);
                    paint.setStrokeWidth(28f);
                    canvas.drawLine(250, 0, 150+speed, 0, paint);
                }

                // 착륙지 그리기
                paint.setColor(Color.RED);
                paint.setStrokeWidth(25f);
                canvas.drawLine(intV, canvas.getHeight(), intV+600, canvas.getHeight(), paint);

                // 착륙지까지 왔을 때
                if(y == canvas.getHeight()-200) {
                    game = 1;
                    boost = false;
                    String text1, text2 = "", text3 = "계속하려면 UP버튼을 누르세요";
                    paint.setTextSize(70);
                    paint.setColor(Color.WHITE);
                    paint.setTextAlign(Paint.Align.CENTER);
                    // 성공 했을 때
                    if((x+100 >= intV && x+100 <= intV+600) && rotateRate == 0 && speed <= 100) {
                        success++;
                        text1 = "성공!";
                        text2 = "연속으로 " + success + "번 성공";
                    }
                    // 실패 했을 때
                    else {
                        success = 0;
                        text1 = "게임 오버!!";
                        if(!(x+100 >= intV && x+100 <= intV+600)) text2 = "착륙지가 아닙니다!";
                        if(rotateRate != 0) text2 = "기울어져 있습니다!";
                        if(speed > 100) text2 = "너무 빠릅니다!";

                        // 우주선 다시 그리기
                        lander = BitmapFactory.decodeResource(getResources(), R.drawable.lander_crashed);
                    }
                    canvas.drawText(text1, canvas.getWidth()/2, canvas.getHeight()/2, paint);
                    canvas.drawText(text2, canvas.getWidth()/2, canvas.getHeight()/2+90, paint);
                    canvas.drawText(text3, canvas.getWidth()/2, canvas.getHeight()/2+180, paint);
                }

                // 우주선 그리기
                Bitmap rotateLander = Bitmap.createBitmap(lander, 0, 0, lander.getWidth(), lander.getHeight(), rotateMatrix, false);
                canvas.drawBitmap(rotateLander, x, y, null);
            }catch (Exception e){
                Log.e("Log", e.toString());
            }
        }
    }

    // 부스터 함수
    private void booster() {
        mHandler.postDelayed(new Runnable() {
            public void run() {
                fuel = fuel - 2;
                if(fuel <= 0) boost = false;
                if(boost)
                    mHandler.postDelayed(this, 100);
            }
        }, 100);
    }

    // 키를 눌렀을 때
    public void KeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_DPAD_UP) {
            if(game == 0 && boost == false) {
                boost = true;
                booster();
            }
            else if(game == 1) {
                rotateMatrix.postRotate(0);
                fuel = 100;
                game = 0;
                x=0; y=0;
                speed = 1;
                randomV = Math.random();
                intV = (int)(randomV * 300);
            }
        }
        else if(keyCode == event.KEYCODE_DPAD_DOWN) {
            boost = false;
        }
        else if(keyCode == event.KEYCODE_DPAD_RIGHT) {
            trans = RIGHT;
        }
        else if(keyCode == event.KEYCODE_DPAD_LEFT) {
            trans = LEFT;
        }
    }
}
