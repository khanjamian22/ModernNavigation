package com.imran.meathub.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonMethods {
    SharedPreferences sharedPreferences;



    public void setPrefsData(Context context, String prefsKey,
                             String prefValue) {

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(prefsKey, prefValue);
        editor.apply();
        editor.commit();
    }

    public String getPrefsData(Context context, String prefsKey,
                               String defaultValue) {
        try {
            if (context != null) {
                sharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(context);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sharedPreferences.getString(prefsKey, defaultValue);
    }

    public Boolean getDateValidate(String fromDate, String Todate) {
        boolean response;
        Date date_in = null, to_date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            date_in = sdf.parse(fromDate.trim());
            to_date = sdf.parse(Todate.trim());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        response = !date_in.after(to_date);
        return response;

    }

    public String NullValueCheck(String value) {
        String val = "";
        if (value.equals("null")) {
            val = "";
        } else {
            val = value;
        }

        return val;
    }

    public String getDateTimeValue(String date) {
        String dateStr = "";

        if (date.length() > 0) {

            String[] value = date.split("T");
            dateStr = value[0];
        }
        return dateStr;

    }

    public String getTimeValue(String date) {
        String dateStr = "";
        if (date.length() > 0) {
            String[] value = date.split("T");
            dateStr = value[1];
        }
        return dateStr;

    }

    public String getNullValue(String date) {

        if (date.contains("null")) {
            date = "";
        }
        return date;
    }

    public boolean isValidMobile(String phoneNo) {
        boolean check = false;
        if (phoneNo.matches("\\d{10}")) return true;
            //validating phone number with -, . or spaces
        else if (phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
            //validating phone number with extension length from 3 to 5
        else if (phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
            //validating phone number where area code is in braces ()
        else if (phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
            //return false if nothing matches the input
        else if (phoneNo.length() < 10 || phoneNo.length() > 15) return false;
        else return false;
    }

    public void showAlert(String message, Context context) {
        if (context != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(message).setCancelable(false)
                    .setPositiveButton("OK", (dialog, id) -> {
                    });
            try {
                builder.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public static boolean isOnline(Context context) {

        NetworkInfo netInfo = null;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            netInfo = cm.getActiveNetworkInfo();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

/*
    public void toastMsg(Context context, String message) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, ((Activity) context).findViewById(R.id.customToast_layout));
        TextView text = layout.findViewById(R.id.custom_toast_message);
        text.setText(message);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }*/


}
