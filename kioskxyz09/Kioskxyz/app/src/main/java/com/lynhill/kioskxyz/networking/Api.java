package com.lynhill.kioskxyz.networking;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;

import com.lynhill.kioskxyz.R;
import com.lynhill.kioskxyz.callback.BaseCallBack;
import com.lynhill.kioskxyz.model.RegisterModel;
import com.lynhill.kioskxyz.model.UrlResponseModel;
import com.lynhill.kioskxyz.utils.App;
import com.lynhill.kioskxyz.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Api {
    private String TAG = Api.class.getSimpleName();
    private WebServices mInterface_method = App.getRetroInstance().create(WebServices.class);
    private Dialog mProgressDialog;
    private Activity mActivity;
    public Api(Activity mActivity) {
        this.mActivity = mActivity;
        mProgressDialog = Utils.returnDialog(mActivity, R.layout.progress_layout);
        mProgressDialog.setCancelable(false);
    }
    /*TODO dismissProgress*/
    public void dismissProgress(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
    /*TODO Register  Signup*/
    public void register(BaseCallBack<RegisterModel> callBack, String email, String name) {

        mProgressDialog.show();

        if (!Utils.isNetworkConnected()) {
            callBack.onError(mActivity.getString(R.string.errorNoInternetConnection));
            return;
        }


        Call<RegisterModel> call = mInterface_method.registration(email, name );

        call.enqueue(new Callback<RegisterModel>() {
            @Override
            public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {
                dismissProgress(mProgressDialog);
                Log.d("hdgh",response.body().toString());
                if (response.isSuccessful()) {
                    callBack.onResponse(response.body());
                    Log.d("hdghfggg",response.body().toString());
                }

            }

            @Override
            public void onFailure(Call<RegisterModel> call, Throwable t) {
                dismissProgress(mProgressDialog);
                callBack.onError(t.getMessage());
                Log.d("hfgj",t.getMessage());
            }
        });
    }

    /*TODO get all media url*/
     public void getMediaUrls(BaseCallBack<UrlResponseModel> callBack){
         mProgressDialog.show();

         if (!Utils.isNetworkConnected()) {
             callBack.onError(mActivity.getString(R.string.errorNoInternetConnection));
             return;
         }
         Call<UrlResponseModel> call=mInterface_method.getAllMedialUrl();
         Log.d("fffgg","chalkds");
         call.enqueue(new Callback<UrlResponseModel>() {
             @Override
             public void onResponse(Call<UrlResponseModel> call, Response<UrlResponseModel> response) {
                 Log.d("fffff","chalkds");
                 mProgressDialog.dismiss();
                 if(response.isSuccessful()) {
                     callBack.onResponse(response.body());
                     Log.d("myresponse", response.body().toString());
                 }
             }

             @Override
             public void onFailure(Call<UrlResponseModel> call, Throwable t) {
                mProgressDialog.dismiss();
                callBack.onError(t.getMessage());
                Log.d("myerror",t.getMessage().toString());
             }
         });

     }
}
