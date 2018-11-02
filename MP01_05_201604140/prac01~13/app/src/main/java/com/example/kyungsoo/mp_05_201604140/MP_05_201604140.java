package com.example.kyungsoo.mp_05_201604140;

import android.annotation.TargetApi;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MP_05_201604140 extends AppCompatActivity {
    private LinearLayout layout;
    private Button fadeButton, slideButton, explodeButton;
    private ImageView imageView;
    boolean visible;
    AnimationDrawable rocketAnimation;
    MySurfaceView view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //       setContentView(new MyView_10(this));
        view = new MySurfaceView(this);
        setContentView(view);

//        ImageView rocketImage = (ImageView) findViewById(R.id.rocket_image);
//        rocketImage.setBackgroundResource(R.drawable.values);
//        rocketAnimation = (AnimationDrawable) rocketImage.getBackground();

        // 11번 실습
//        layout = (LinearLayout) findViewById(R.id.linearLayout);
//        fadeButton = (Button) findViewById(R.id.fade);
//        slideButton = (Button) findViewById(R.id.slide);
//        explodeButton = (Button) findViewById(R.id.explode);
//        imageView = (ImageView) findViewById(R.id.imageView2);
//
//        fadeButton.setOnClickListener(new View.OnClickListener() {
//            @TargetApi(Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View view) {
//                TransitionManager.beginDelayedTransition(layout, new Fade());
//                visible = !visible;
//                imageView.setVisibility(visible ? View.VISIBLE : View.GONE);
//            }
//        });
//
//        slideButton.setOnClickListener(new View.OnClickListener() {
//            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View view) {
//                TransitionManager.beginDelayedTransition(layout, new Slide());
//                visible = !visible;
//                imageView.setVisibility(visible ? View.VISIBLE : View.GONE);
//            }
//        });
//
//        explodeButton.setOnClickListener(new View.OnClickListener() {
//            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View view) {
//                TransitionManager.beginDelayedTransition(layout, new Explode());
//                visible = !visible;
//                imageView.setVisibility(visible ? View.VISIBLE : View.GONE);
//            }
//        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

//    public boolean onTouchEvent(MotionEvent event) {
//        if(event.getAction() == MotionEvent.ACTION_DOWN) {
//            rocketAnimation.start();
//            return true;
//        }
//        return super.onTouchEvent(event);
//    }
}
