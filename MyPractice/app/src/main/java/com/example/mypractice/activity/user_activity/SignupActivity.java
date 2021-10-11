package com.example.mypractice.activity.user_activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.example.mypractice.R;
import com.example.mypractice.base.BaseActivity;
import com.example.mypractice.databinding.ActivitySignupBinding;
import com.example.mypractice.model.signup_model.SignUpDetailsModel;
import com.example.mypractice.utils.Constance;
import com.example.mypractice.utils.Utils;

public class SignupActivity extends BaseActivity {
   /* TODO SIGNUP ACTIVITY BINDING */
    private ActivitySignupBinding vb;

    /* TODO UPDATE DETAILS USER*/
    SignUpDetailsModel signUpDetailsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vb=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(vb.getRoot());
        initOnclickListener();
    }

    private void initOnclickListener() {
        vb.btnRegister.setOnClickListener(v->{
            String Fname = vb.etFname.getText().toString();
            String Lname = vb.etLname.getText().toString();
            String Email = vb.etEmail.getText().toString();
            String Password = vb.etPassword.getText().toString();
            if(isValidate())
            {
                sendOtp(Fname,Lname,Email,Password);
            }

        });
    }

    private boolean isValidate() {
        String fname = vb.etFname.getText().toString();
        String lname = vb.etLname.getText().toString();
        String email = vb.etEmail.getText().toString();
        String password = vb.etPassword.getText().toString();
        String con_password = vb.etConPassword.getText().toString();

        if (TextUtils.isEmpty(fname)) {
            vb.etFname.requestFocus();
            Utils.showToast(getString(R.string.errorPleaseEnterfirstName), this);
            return false;
        }
        if (vb.etFname.length() <= 2) {
            vb.etFname.requestFocus();
            Utils.showToast(getString(R.string.errorNamelentghtooshort), this);
            return false;
        }

        if (TextUtils.isEmpty(lname)) {
            vb.etLname.requestFocus();
            Utils.showToast(getString(R.string.errorPleaseEnterlastName), this);
            return false;
        }
        if (vb.etFname.length() <= 2) {

            vb.etFname.requestFocus();
            Utils.showToast(getString(R.string.errorNamelentghtooshort), this);
            return false;
        }

        if (TextUtils.isEmpty(email)) {
            vb.etEmail.requestFocus();
            Utils.showToast(getString(R.string.errorPleaseEnteremail), this);
            return false;
        }
        if (!validateEmail(email)) {
            vb.etEmail.requestFocus();
            Utils.showToast(getString(R.string.errorInvalidemail), this);
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            vb.etPassword.requestFocus();
            Log.d("pass", vb.etPassword.getText().toString());
            Utils.showToast(getString(R.string.errorPleaseEnterpassword), this);

            return false;
        }
        if (validatePWD(password)) {
            vb.etPassword.requestFocus();
            Utils.showToast(getString(R.string.errorPasswordlentghtooshort), this);
            return false;
        }

        if (TextUtils.isEmpty(con_password)) {
            vb.etConPassword.requestFocus();
            Utils.showToast(getString(R.string.errorPleaseEnterconpassword), this);

            return false;
        }
        if (!password.equals(con_password)) {
            vb.etConPassword.requestFocus();
            Log.d("cn_pass", vb.etConPassword.getText().toString());
            Utils.showToast(getString(R.string.errorpasswordnotmatch), this);
            return false;
        }

        return true;

    }

    private void sendOtp(String fname,String lname ,String email ,String password) {
        signUpDetailsModel=new SignUpDetailsModel(fname,lname,email,password);
        Intent intent=new Intent(SignupActivity.this,OtpActivity.class);
        intent.putExtra(Constance.intent.SIGN_UP_DETAILS,signUpDetailsModel);
        startActivity(intent);
        finish();

    }


}