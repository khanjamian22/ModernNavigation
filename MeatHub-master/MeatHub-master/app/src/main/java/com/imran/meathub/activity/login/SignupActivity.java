package com.imran.meathub.activity.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.imran.meathub.R;

import java.util.Arrays;
import java.util.List;

public class SignupActivity extends AppCompatActivity {
    private final static String gender[]={"Male","Female"};
    private Button btn_login,btn_signup;
    private TextInputEditText et_mobile, et_email,et_password;
    private AutoCompleteTextView act_gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initView();
    }

    private void initView() {
        et_mobile=findViewById(R.id.et_mobile_no);
        et_email=findViewById(R.id.et_email);
        et_password=findViewById(R.id.et_password);
        act_gender=findViewById(R.id.et_gender);
        btn_login=findViewById(R.id.cirLoginButton);
        btn_signup=findViewById(R.id.btn_signup);
        List<String> gender_type= Arrays.asList(gender);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.item_view,gender_type);
        act_gender.setAdapter(arrayAdapter);
        btn_login.setOnClickListener(v->{
            Intent loginIntent=new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        });
    }
}