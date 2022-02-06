package com.imran.meathub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imran.meathub.R;
import com.imran.meathub.activity.model.NotificationModel;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private List<NotificationModel> notificationModelList;
    private Context context;

    public NotificationAdapter( Context context,List<NotificationModel> notificationModelList) {
        this.notificationModelList = notificationModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.notification_lines_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, int position) {
      holder.tv_notificationContent.setText(notificationModelList.get(position).getNotification());
      holder.tv_date.setText(notificationModelList.get(position).getDate());
      holder.tv_time.setText(notificationModelList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return notificationModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_notificationContent,tv_date,tv_time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_notificationContent=itemView.findViewById(R.id.tv_notification_content);
            tv_date=itemView.findViewById(R.id.notf_date);
            tv_time=itemView.findViewById(R.id.notf_time);
        }
    }
}
