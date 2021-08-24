package com.example.airplanemode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
MyAirplaneMode myAirplaneMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myAirplaneMode=new MyAirplaneMode();
      registerReceiver(myAirplaneMode,new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myAirplaneMode);
    }
}