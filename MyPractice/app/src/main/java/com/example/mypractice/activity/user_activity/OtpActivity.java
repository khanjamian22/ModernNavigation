package com.example.mypractice.activity.user_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.example.mypractice.R;
import com.example.mypractice.callback.BaseCallBack;
import com.example.mypractice.databinding.ActivityOtpBinding;
import com.example.mypractice.model.otp_model.SendOTPResponse;
import com.example.mypractice.model.signup_model.SignUP_SuccessModel;
import com.example.mypractice.model.signup_model.SignUpDetailsModel;
import com.example.mypractice.model.signup_model.SignUpModel;
import com.example.mypractice.networking.Api;
import com.example.mypractice.utils.Constance;
import com.example.mypractice.utils.Utils;

public class OtpActivity extends AppCompatActivity {
     /* TODO BINDING*/
     ActivityOtpBinding vb;
    /*TODO SignUpModel*/
    private SignUP_SuccessModel signUpModel;

    /*TODo SignUp Detail model*/
    private SignUpDetailsModel signUpDetailsModel;


    /*TODO Counter Timer*/
    private CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vb=ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(vb.getRoot());
        signUpDetailsModel=getIntent().getParcelableExtra(Constance.intent.SIGN_UP_DETAILS);
        initView();
        initOnClickListener();
        if(signUpDetailsModel!=null)
        {
            sendOTPApiCall();
        }
        else {
            Utils.showToast(getString(R.string.errorsomthingwrong),this);
        }

    }

    private void sendOTPApiCall() {
        new Api(this).sendOTP(new BaseCallBack<SendOTPResponse>() {
            @Override
            public void onResponse(SendOTPResponse response) {
                Log.e("dataREsoinbse", "onResponse: " + response);
                if (response == null || response.isError()) {
                    Utils.showToast(response.getMessage(), OtpActivity.this);
                } else {
                    hideOTPResendlayout();

                }
            }

            @Override
            public void onError(String error) {
                Utils.showToast(error, OtpActivity.this);
            }
        },signUpDetailsModel.getFirstName(),
           signUpDetailsModel.getLastName(),
            signUpDetailsModel.getEmail());
    }

    /*TODO Register the user */
    private void registerUser(SignUpDetailsModel signUpDetailsModel, String otp_code) {

        new Api(this).register(new BaseCallBack<SignUpModel>() {
                                   @Override
                                   public void onResponse(SignUpModel response) {

                                       if (response == null || response.isError()) {

                                           Utils.showToast(response.getMessage(), OtpActivity.this);

                                       } else {


                                           showDialogForEmailVerification(response);

                                       }

                                   }

                                   @Override
                                   public void onError(String error) {
                                       Utils.showToast(error, OtpActivity.this);
                                       finish();
                                   }
                               }, signUpDetailsModel.getFirstName(), signUpDetailsModel.getLastName(),
                signUpDetailsModel.getEmail(), signUpDetailsModel.getPassword(), otp_code);

    }
    /*TODO show registration completed*/
    private void showDialogForEmailVerification(SignUpModel response) {

        Dialog registrationCompletedDialog = Utils.returnDialog(this, R.layout.registerdsuccessfully_dialog_layout);
        registrationCompletedDialog.show();
        registrationCompletedDialog.setCancelable(false);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        registrationCompletedDialog.getWindow().setLayout((6 * width) / 8, (4 * height) / 9);

        FrameLayout image_container = (FrameLayout) registrationCompletedDialog
                .findViewById(R.id.image_container);

        AppCompatButton continue_btn = (AppCompatButton) registrationCompletedDialog
                .findViewById(R.id.continue_btn);


        Animation animation = AnimationUtils.loadAnimation(this, R.anim.shanking_anim);

        image_container.setAnimation(animation);


        continue_btn.setOnClickListener(v -> {

            /*TODO Save User Detail in Paper Library*/
            Utils.saveUserDetail(response.getSuccess());

            /*TODO start Activity */
            ChooseRolesActivity.startActivity(OtpActivity.this);

            finish();

        });


    }


    private void initView() {
    }

    private void initOnClickListener() {
        vb.continueBtn.setOnClickListener(v -> {

            String otp_code = vb.otpView.getText().toString();
            Log.e("otp_codeCheck", "initOnClickListener: " + otp_code);
            if (isValidate(otp_code)) {

                Log.e("otp_codeCheck", "initOnClickListener: " + signUpModel);


                if (signUpDetailsModel != null) {

                    registerUser(signUpDetailsModel, otp_code);

                } else {
                    Utils.showToast(getString(R.string.errorsomthingwrong), this);
                }


            }

        });


        vb.retryOTP.setOnClickListener(v -> {
            sendOTPApiCall();
        });

    }
    /*TODO is validate*/
    private boolean isValidate(String otp_code) {


        if (TextUtils.isEmpty(otp_code)) {
            Utils.showToast(getString(R.string.errorPleaseEnterotp), this);
            return false;
        }


        return true;
    }
    private void startTimer() {


        countDownTimer = new CountDownTimer(180000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                vb.otpcounter.setText(String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));

            }

            public void onFinish() {
                countDownTimer.cancel();
                showOTPResendLayout();


            }

        }.start();
    }
    /*Todo show opt resend layout*/
    private void showOTPResendLayout() {

        vb.otpView.setText("");
        vb.otpView.setEnabled(false);

        vb.otpcounter.setVisibility(View.GONE);
        vb.otpresendContainer.setVisibility(View.VISIBLE);
    }
    /*TODO hide otp resend layout*/
    private void hideOTPResendlayout() {

        vb.otpView.setText("");
        vb.otpView.setEnabled(true);

        vb.otpcounter.setVisibility(View.VISIBLE);
        vb.otpresendContainer.setVisibility(View.GONE);
        startTimer();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

}