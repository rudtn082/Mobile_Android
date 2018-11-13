package com.example.kyungsoo.mp01_07_201604140;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class LocalService extends Service {

    // 클라이언트에게반환되는바인더
    private final IBinder mBinder= new LocalBinder();
    // 난수발생기
    private final Random mGenerator= new Random();

    // 클라이언트바인더를위한클래스
    public class LocalBinder extends Binder {
        LocalService getService( ) {
            return LocalService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    // 클라이언트를위한메소드
    public int getRandomNumber( ) {
        return mGenerator.nextInt(100);
    }
}
