package com.example.zivameos.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.zivameos.R;
import com.example.zivameos.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding lvb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lvb=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(lvb.getRoot());
    }

}