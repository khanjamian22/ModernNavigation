package com.example.zivameos.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.example.zivameos.R;
import com.example.zivameos.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding spvb;
    public static final int SPLASH_TIME=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spvb=ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(spvb.getRoot());
        loadSplash();
    }

    private void loadSplash() {
        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },SPLASH_TIME);
    }
}