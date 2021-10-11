package com.example.mypractice.networking;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;

import com.example.mypractice.R;
import com.example.mypractice.callback.BaseCallBack;
import com.example.mypractice.model.RoleUpdateResponse;
import com.example.mypractice.model.otp_model.OTPResponse;
import com.example.mypractice.model.otp_model.SendOTPResponse;
import com.example.mypractice.model.signup_model.SignUpModel;
import com.example.mypractice.utils.App;
import com.example.mypractice.utils.Utils;
import com.google.android.material.snackbar.BaseTransientBottomBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Api {
    private String TAG = Api.class.getSimpleName();
    private WebServices mInterface_method = App.getRetroInstance().create(WebServices.class);
    private Dialog mProgressDialog;
    private Activity mActivity;
    private String mAccessToken;
    SignUpModel signUpModel;
    String ANDROID_ID;
    /*TODO FOR ACTIVITIES */
    public Api(Activity mActivity) {
        this.mActivity = mActivity;
        mProgressDialog = Utils.returnDialog(mActivity, R.layout.progress_layout);
        mProgressDialog.setCancelable(false);
        mAccessToken = Utils.getAccessToke();
        ANDROID_ID = Utils.getAndroi_id();

    }
    /*TODO FOR FRAGMENTS */
     public Api()
     {

     }
    /*TODO DISMISS PROGRESS DIALOG*/

    public void dismissProgress(Dialog dialog)
    {
        if(dialog!=null && dialog.isShowing())
        {
            dialog.dismiss();
        }
    }
    /*TODO --------------------------------------------  Common Apis call   -------------------------------------- */

    /*TODO REGISTER USER/SIGNUP*/
    public  void register(BaseCallBack<SignUpModel> callBack,String fname,String lname,String email,String password,String otp )
    {
        mProgressDialog.show();
        if(!Utils.isNetworkConnected())
        {
            callBack.onError(mActivity.getString(R.string.errorNoInternetConnection));
            return;
        }
        Call<SignUpModel> call=mInterface_method.registration(fname,lname,email,password,otp);
        call.enqueue(new Callback<SignUpModel>() {
            @Override
            public void onResponse(Call<SignUpModel> call, Response<SignUpModel> response) {
                if(response.isSuccessful())
                {
                  callBack.onResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<SignUpModel> call, Throwable t) {
               callBack.onError(t.getMessage());

            }
        });

    }
    /*TODO resend OTP*/
    public void sendOTP(BaseCallBack callBack, String fname, String lname, String email) {

        mProgressDialog.show();

        if (!Utils.isNetworkConnected()) {
            callBack.onError(mActivity.getString(R.string.errorNoInternetConnection));
            return;
        }

        Call<SendOTPResponse> call = mInterface_method.getOTP(fname, lname, email);

        call.enqueue(new Callback<SendOTPResponse>() {
            @Override
            public void onResponse(Call<SendOTPResponse> call, Response<SendOTPResponse> response) {
                dismissProgress(mProgressDialog);
                callBack.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<SendOTPResponse> call, Throwable t) {

                dismissProgress(mProgressDialog);
                callBack.onError(t.getMessage());


            }
        });


    }

    /*TODO OTP VERIFICATION*/

    public  void otpVerification(BaseCallBack<OTPResponse> callBack, String otp, String email)
    {
        mProgressDialog.show();
        if(!Utils.isNetworkConnected())
        {
            callBack.onError(mActivity.getString(R.string.errorNoInternetConnection));
            return;
        }
        Call<OTPResponse> call=mInterface_method.otpVerification(otp,email);
         call.enqueue(new Callback<OTPResponse>() {
             @Override
             public void onResponse(Call<OTPResponse> call, Response<OTPResponse> response) {
                 callBack.onResponse(response.body());
             }

             @Override
             public void onFailure(Call<OTPResponse> call, Throwable t) {
                 callBack.onError(t.getMessage());
             }
         });
    }
    /*TODO LOGIN API*/
    public void login(BaseCallBack<SignUpModel> callBack,String email,String password)
    {
        Call<SignUpModel> call=mInterface_method.login(email,password);
        call.enqueue(new Callback<SignUpModel>() {
            @Override
            public void onResponse(Call<SignUpModel> call, Response<SignUpModel> response) {
                callBack.onResponse(response.body());
                Log.d("checkresponse",response.body().toString());
            }

            @Override
            public void onFailure(Call<SignUpModel> call, Throwable t) {
                callBack.onError(t.getMessage());
            }
        });
    }
   /*TODO update role*/
    public void roleUpdate(BaseCallBack callBack,String role)
    {
        mProgressDialog.show();

        if (!Utils.isNetworkConnected()) {
            callBack.onError(mActivity.getString(R.string.errorNoInternetConnection));
            return;
        }
        Call<RoleUpdateResponse> call=mInterface_method.roleUpdate("Bearer" +mAccessToken,role);
         call.enqueue(new Callback<RoleUpdateResponse>() {
             @Override
             public void onResponse(Call<RoleUpdateResponse> call, Response<RoleUpdateResponse> response) {
                 mProgressDialog.dismiss();
                 callBack.onResponse(response.body());
             }

             @Override
             public void onFailure(Call<RoleUpdateResponse> call, Throwable t) {
               mProgressDialog.dismiss();
               callBack.onError(t.getMessage());
             }
         });
    }
}

