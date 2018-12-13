package org.techtown.mp01_10_201604140;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.net.Uri;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        VideoView videoview = (VideoView)this.findViewById(R.id.videoview);
        MediaController mc = new MediaController(this);
        videoview.setMediaController(mc);
        String folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/download/trailer.mp4";
        Log.e("asd", folder);

        videoview.setVideoPath(folder);
        videoview.requestFocus();
        videoview.start();
    }
}
