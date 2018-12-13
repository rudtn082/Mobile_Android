package org.techtown.mp01_10_201604140;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button playButton = (Button) findViewById(R.id.play);
        playButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("http://sites.google.com/site/ubiaccessmobile/sample_audio.amr");
                intent.setDataAndType(uri, "audio/amr");
                startActivity(intent);
            }
        });
    }
}