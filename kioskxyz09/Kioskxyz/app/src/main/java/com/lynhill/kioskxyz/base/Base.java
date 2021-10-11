package com.lynhill.kioskxyz.base;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class Base extends AppCompatActivity {
    private static String TAG = Base.class.getSimpleName();

    // TODO   Validation patter
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    /**
     * TODO Email validation  method
     */
    public boolean validateEmail(String email) {
        return pattern.matcher(email).matches();
    }
}
