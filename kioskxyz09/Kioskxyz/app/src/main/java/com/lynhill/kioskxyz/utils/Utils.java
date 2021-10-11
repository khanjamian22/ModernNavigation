package com.lynhill.kioskxyz.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.lynhill.kioskxyz.R;
import com.lynhill.kioskxyz.model.RegisterModel;

import io.paperdb.Paper;

public class Utils  {
    public static boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) App.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public static Dialog returnDialog(Activity activity, int layoutId) {
        Dialog alertDialog = new Dialog(activity);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(layoutId);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);
        return alertDialog;
    }
    public static Dialog simpleDialog(Activity activity, int layoutId) {
        Dialog alertDialog = new Dialog(activity);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(layoutId);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return alertDialog;
    }
    public static void showLog(String tag, String msg) {
        if (msg != null)
            Log.e(tag, msg);
    }
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
}
