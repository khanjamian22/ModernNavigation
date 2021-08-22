package com.example.broadcastreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

public class MyBroadcastReciever extends BroadcastReceiver {
    private final TextView tv;
    public MyBroadcastReciever(TextView tv) {
        this.tv=tv;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int percentage=intent.getIntExtra("level",0);
        if(percentage!=0)
        {
          tv.setText(percentage+"%");
        }

    }
}
