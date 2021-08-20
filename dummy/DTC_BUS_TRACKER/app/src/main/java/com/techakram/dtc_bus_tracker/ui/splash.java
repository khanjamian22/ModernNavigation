package com.techakram.dtc_bus_tracker.ui;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.techakram.dtc_bus_tracker.MainActivity;
import com.techakram.dtc_bus_tracker.R;

public class splash extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    Animation top,bottom;
    private  static  int SPLASH_SCREEN =1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView=findViewById(R.id.image);
         //textView=findViewById(R.id.textview);
        top = AnimationUtils.loadAnimation(this, R.anim.top);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom);
        imageView.setAnimation(top);
         //textView.setAnimation(bottom);
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
                Intent i = new Intent(splash.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        },SPLASH_SCREEN);
    }
}