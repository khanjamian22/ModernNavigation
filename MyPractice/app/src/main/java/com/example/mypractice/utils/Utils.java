package com.example.mypractice.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mypractice.R;
import com.example.mypractice.model.signup_model.SignUP_SuccessModel;

import io.paperdb.Paper;

public class Utils {

    /* TODO SHOW CUSTUM TOAST */

    public static void showToast(String msg, Context activity) {
        try {
            if (activity != null) {
                if (msg != null && !msg.equals("")) {
                    if (!msg.contains("UnknownHostException")) {

                        Toast toast = new Toast(activity);
                        final ViewGroup nullParent = null;
                        View view = LayoutInflater.from(App.getInstance()).inflate(R.layout.custom_toast, nullParent);
                        TextView textView = view.findViewById(R.id.custom_toast_text);
                        textView.setText(msg);
                        toast.setView(view);
                        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 200);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Utils.showLog("error", e.getMessage());
        }
    }

    /*TODO save if user fill the form*/
    public static void saveIsUserFillForm(boolean isFill){


        Paper.book().write(Constance.paperlib.LOGIN_USER_FORM_FILL,isFill);

    }


    /*TODO LOG ERROR FOR TOAST*/

    public static void showLog(String tag, String msg) {
        if (msg != null)
            Log.e(tag, msg);
    }

    public static boolean isValidPhone(CharSequence target) {
        return (!TextUtils.isEmpty(target) && target.length() == 10);
    }

    public static Dialog returnDialog(Activity activity, int layoutId) {
        Dialog alertDialog = new Dialog(activity);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(layoutId);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);
        return alertDialog;
    }
    /*TODO getAccessToken*/
    public static String getAccessToke(){

        SignUP_SuccessModel successModel = Paper.book().read(Constance.paperlib.USER_DETAILS);

        if(successModel!=null){
            return successModel.getToken();
        }else{
            return null;
        }

    }
    /*TODO get Android_id*/
    public static String getAndroi_id(){

        String android_id =  Paper.book().read(Constance.paperlib.ANDROID_ID);
        return android_id;

    }

    /*TODO set Android_id*/
    public static void setAndroid_id(String android_id){
        Paper.book().write(Constance.paperlib.ANDROID_ID,android_id);
    }
    /* TODO FOR INTERNET CONNECTION */

    public static boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) App.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() !=null && cm.getActiveNetworkInfo().isConnected();
    }

    /*TODO Save User Detail*/
    public static void saveUserDetail(SignUP_SuccessModel successModel){

        Paper.book().write(Constance.paperlib.USER_DETAILS,successModel);

    }

    /*TODO get login User detail success model*/
    public static SignUP_SuccessModel getLoginUserDetail(){

        SignUP_SuccessModel successModel =  Paper.book().read(Constance.paperlib.USER_DETAILS);

        return successModel;
    }

    /*TODO saveRole */
    public static void saveRole(boolean isRoleCompleted){

        Paper.book().write(Constance.paperlib.LOGIN_USER_ROLE,isRoleCompleted);

    }

    /*TODO fetch login user role*/
    public static boolean  getLoginUserRole(){
        return  Paper.book().read(Constance.paperlib.LOGIN_USER_ROLE,false);
    }


    /*TODO save role Name*/
    public static void saveRoleName(String role){

        Paper.book().write(Constance.paperlib.SAVE_USER_ROLE,role);

    }



    /*TODO fetch login user Role name*/
    public static String getLoginUserRoleName(){

        return Paper.book().read(Constance.paperlib.SAVE_USER_ROLE);

    }


}
