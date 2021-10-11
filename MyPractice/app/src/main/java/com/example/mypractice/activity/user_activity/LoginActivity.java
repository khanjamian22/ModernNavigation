package com.example.mypractice.activity.user_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.mypractice.R;
import com.example.mypractice.activity.service_provider_activity.HomeActivity;
import com.example.mypractice.activity.service_provider_activity.LocalBusinessManFormActivity;
import com.example.mypractice.base.BaseActivity;
import com.example.mypractice.callback.BaseCallBack;
import com.example.mypractice.databinding.ActivityLoginBinding;
import com.example.mypractice.model.signup_model.SignUpModel;
import com.example.mypractice.networking.Api;
import com.example.mypractice.utils.Constance;
import com.example.mypractice.utils.Utils;

public class LoginActivity extends BaseActivity {
      ActivityLoginBinding vb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vb=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(vb.getRoot());
        initview();
        initOnClickListener();
    }

    private void initview() {
    }

    private void initOnClickListener() {
        vb.btnLogin.setOnClickListener(v->{
            String email=vb.etEmail.getText().toString();
            String password=vb.etPassword.getText().toString();
            if(isValidate())
            {
               calloginApi(email,password);
            }
        });
    }
    private boolean isValidate() {
        String email = vb.etEmail.getText().toString();
        String password = vb.etPassword.getText().toString();
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
            Utils.showToast(getString(R.string.errorPleaseEnterpassword), this);
            return false;
        }
        if (validatePWD(password)) {
            vb.etPassword.requestFocus();
            Utils.showToast(getString(R.string.errorPasswordlentghtooshort), this);
            return false;
        }
        return true;
    }
    private void calloginApi(String email,String password)
    {
        new Api(this).login(new BaseCallBack<SignUpModel>() {
            @Override
            public void onResponse(SignUpModel response) {
                Intent intent;
                if(response.isError())
                {
                  Utils.showToast(response.getMessage(),LoginActivity.this);
                }
                else if(TextUtils.isEmpty(response.getSuccess().getRole().toString())){
                    ChooseRolesActivity.startActivity(LoginActivity.this);
                    finish();
                }
                else {
                    /*TODO Save User Detail in Paper Library*/
                    Utils.saveUserDetail(response.getSuccess());
                    /*TODO save login User role*/
                   Utils.saveRole(true);
                   String role=response.getSuccess().getRole().toString();
                   Utils.saveRoleName(role);
                   if(role.equalsIgnoreCase(Constance.USER_ROLE))
                   {
                       intent=new Intent(LoginActivity.this,UserHomeActivity.class);
                   }
                   else {
                       if(TextUtils.isEmpty(response.getSuccess().getAvatar().toString()))
                       {
                          intent=new Intent(LoginActivity.this, LocalBusinessManFormActivity.class);
                       }
                       else {
                           /*TODO save data If the form User details update*/
                           Utils.saveIsUserFillForm(true);
                           intent = new Intent(LoginActivity.this, HomeActivity.class);

                       }
                   }
                   startActivity(intent);
                   finish();
                }
            }

            @Override
            public void onError(String error) {
                Utils.showToast(error,LoginActivity.this);
            }
        },email,password);
    }
}