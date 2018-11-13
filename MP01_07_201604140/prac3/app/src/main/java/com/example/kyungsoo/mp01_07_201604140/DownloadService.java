package com.example.kyungsoo.mp01_07_201604140;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadService extends IntentService {
    private int result = Activity.RESULT_CANCELED;

    public DownloadService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Uri data = intent.getData();
        String urlPath = intent.getStringExtra("urlpath");
        String buffer = " ";

        InputStream stream = null;
        try {
            URL url = new URL(urlPath);
            Log.w("aa", url.toString());
            //stream = url.openConnection().getInputStream();


            Log.w("aa", "sdf");
            HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
            urlConn.setRequestProperty("Accept", "text/html");
            urlConn.setDoInput(true);
            Log.w("aa", "asd");

            InputStream IS = urlConn.getInputStream();
            Log.w("aa", "zx");
            InputStreamReader reader = new InputStreamReader(IS);
            int i = 0, next = -1;
            while ((next = reader.read()) != -1) {
                buffer += " " + (char) next;
                if (++i > 100) break;
            }
            result = Activity.RESULT_OK;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                    Log.w("aa", "1");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.w("aa", "IOException sending message", e);
                }
            }
        }

        Bundle extras = intent.getExtras();
        if (extras != null) {
            Messenger messenger = (Messenger) extras.get("MESSENGER");
            Message msg = Message.obtain();
            msg.arg1 = result;
            msg.obj = buffer;
            try {
                messenger.send(msg);
            } catch (android.os.RemoteException e) {
                Log.w("aa", "RemoteException sending message", e);
            }
        }
    }
}
