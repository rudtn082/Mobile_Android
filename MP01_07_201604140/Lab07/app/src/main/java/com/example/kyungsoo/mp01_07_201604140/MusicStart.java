package com.example.kyungsoo.mp01_07_201604140;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class MusicStart extends Service {
    MediaPlayer player;

    public MusicStart() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public  void onCreate() {
        player = MediaPlayer.create(this, R.raw.old_pop);
        player.setLooping(false);
        player.start();
        Toast.makeText(this, "음악 켜짐", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        player.stop();
        Toast.makeText(this, "음악 꺼짐", Toast.LENGTH_LONG).show();
    }
}
