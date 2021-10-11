package com.example.grpchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {
    private FirebaseAuth mAuth = null;
    private FirebaseAuth.AuthStateListener mAuthListener;

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                mAuth = FirebaseAuth.getInstance();
                mAuthListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user != null) {
                            // User is signed in, send to mainmenu
                            Log.d("akram", "onAuthStateChanged:signed_in:" + user.getUid());
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        } else {
                            // User is signed out, send to register/login
                            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        }
                    }
                };
            }
        }, SPLASH_DISPLAY_LENGTH);


    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//        if(firebaseAuth.getCurrentUser()!=null)
//        {
//            startActivity(new Intent(this,MainActivity.class));
//            finish();
//        }
//    }
}