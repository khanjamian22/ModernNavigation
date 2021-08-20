package com.techakram.Online_Shop;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class SplashActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private  static  int SPLASH_SCREEN =3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        firebaseAuth=FirebaseAuth.getInstance();
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();
            }
        },SPLASH_SCREEN);

    }

    @Override
    protected void onStart()
    {
        super.onStart( );
        FirebaseUser currentUser=firebaseAuth.getCurrentUser();
        if(currentUser==null)
        {
            Intent registerIntent = new Intent(SplashActivity.this,RegisterActivity.class);
            startActivity(registerIntent);
            finish();
        }
        else
            {
                Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
    }
}

