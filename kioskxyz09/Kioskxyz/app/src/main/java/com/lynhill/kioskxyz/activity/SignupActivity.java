package com.lynhill.kioskxyz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;

import com.lynhill.kioskxyz.R;
import com.lynhill.kioskxyz.base.Base;
import com.lynhill.kioskxyz.callback.BaseCallBack;
import com.lynhill.kioskxyz.databinding.ActivitySignupBinding;
import com.lynhill.kioskxyz.model.RegisterModel;
import com.lynhill.kioskxyz.networking.Api;
import com.lynhill.kioskxyz.utils.Constant;
import com.lynhill.kioskxyz.utils.Utils;

import io.paperdb.Paper;

public class SignupActivity extends Base {
     ActivitySignupBinding vb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vb=ActivitySignupBinding.inflate(getLayoutInflater());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(vb.getRoot());
        initOnClickListener();
    }
     /*TODO initonClickListener*/
    private void initOnClickListener() {
        vb.btnRegister.setOnClickListener(v->{
             String email=vb.etEmail.getText().toString();
            String name=vb.etName.getText().toString();
            Log.d("ggfgd","button clicked");

            if(isValidate())
            {
                Log.d("Email=",email.toString()+"Name="+name.toString());
                  callRegisterApi(email,name);

            }


        });
    }


    public boolean isValidate(){
        String email=vb.etEmail.getText().toString();
        String name=vb.etName.getText().toString();
        if (TextUtils.isEmpty(email)) {
            vb.etEmail.requestFocus();
            Utils.showToast(getString(R.string.pleaseeneteremail), this);
            return false;
        }
        if (!validateEmail(email)) {
            vb.etEmail.requestFocus();
            Utils.showToast(getString(R.string.invalidemail), this);
            return false;
        }
        if(TextUtils.isEmpty(name))
        {
            vb.etName.requestFocus();
            Utils.showToast(getString(R.string.pleaseenetrname),this);
            return false;
        }
        if(name.length()<4)
        {
            vb.etName.requestFocus();
            Utils.showToast(getString(R.string.invalidname),this);
            return false;
        }
        return true;

    }
    /*TODO call register api*/
    private void callRegisterApi(String email,String name) {
        new Api(this).register(new BaseCallBack<RegisterModel>() {
            @Override
            public void onResponse(RegisterModel response) {
                Log.d("gggggggg",response.getMsg().toString()+" "+ response.getError());
                 if(response==null && response.getError().equalsIgnoreCase("false"))
                 {
                     Utils.showToast(response.getMsg(),SignupActivity.this);

                 }
                 else {
                       /*TODO save user email*/
                     Paper.book().write(Constant.paperlib.USER_DETAIL,true);
                     Paper.book().write(Constant.paperlib.USER_EMAIL,name);
                     Paper.book().write(Constant.paperlib.USER_NAME,email);

                       Intent intent=new Intent(SignupActivity.this,HomeActivity.class);
                       startActivity(intent);
                 }
            }

            @Override
            public void onError(String error) {
               Utils.showToast(error,SignupActivity.this);
               Log.d("fffff",error.toString());
            }
        },email,name);
    }
}

