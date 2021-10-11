package com.example.mypractice.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class BaseActivity extends AppCompatActivity {
    /*TODO Response*/
    private static String TAG = BaseActivity.class.getSimpleName();

    // TODO   Validation patter
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    /*TODO calligraphy init*/
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        statusbarColor();
    }
    /*TODO status bar white color*/
    public void statusbarColor() {

        /*TODO TO make status bar white*/
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);

    }

    /**
     * TODO Email validation  method
     */
    public boolean validateEmail(String email) {
        return pattern.matcher(email).matches();
    }
    /*TODO Password validation method*/
    public boolean validatePWD(String password) {
        return password.length() < 6;

    }

}
