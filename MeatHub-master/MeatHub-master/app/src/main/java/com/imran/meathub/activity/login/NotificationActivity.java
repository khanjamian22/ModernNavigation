package com.imran.meathub.activity.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.imran.meathub.R;
import com.imran.meathub.activity.model.FaqModel;
import com.imran.meathub.activity.model.NotificationModel;
import com.imran.meathub.adapter.FaqLineAdapter;
import com.imran.meathub.adapter.NotificationAdapter;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {
    private RecyclerView notRecyclerView;
    private NotificationAdapter notificationAdapter;
    private List<NotificationModel> notificationModelList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initView();
        setRecyclerView();
    }

    private void initView() {
        notRecyclerView=findViewById(R.id.faq_recycler_view);
    }

    private void setRecyclerView() {
        notificationModelList.add(new NotificationModel(getString(R.string.tv_faq_line),getString(R.string.tv_notf_date),getString(R.string.tv_notf_time)));
        notificationModelList.add(new NotificationModel(getString(R.string.tv_faq_line),getString(R.string.tv_notf_date),getString(R.string.tv_notf_time)));
        notificationModelList.add(new NotificationModel(getString(R.string.tv_faq_line),getString(R.string.tv_notf_date),getString(R.string.tv_notf_time)));
        notificationModelList.add(new NotificationModel(getString(R.string.tv_faq_line),getString(R.string.tv_notf_date),getString(R.string.tv_notf_time)));
        notificationModelList.add(new NotificationModel(getString(R.string.tv_faq_line),getString(R.string.tv_notf_date),getString(R.string.tv_notf_time)));
        notificationModelList.add(new NotificationModel(getString(R.string.tv_faq_line),getString(R.string.tv_notf_date),getString(R.string.tv_notf_time)));
        notificationModelList.add(new NotificationModel(getString(R.string.tv_faq_line),getString(R.string.tv_notf_date),getString(R.string.tv_notf_time)));
        notificationModelList.add(new NotificationModel(getString(R.string.tv_faq_line),getString(R.string.tv_notf_date),getString(R.string.tv_notf_time)));
        notificationModelList.add(new NotificationModel(getString(R.string.tv_faq_line),getString(R.string.tv_notf_date),getString(R.string.tv_notf_time)));
        notificationModelList.add(new NotificationModel(getString(R.string.tv_faq_line),getString(R.string.tv_notf_date),getString(R.string.tv_notf_time)));
        notificationModelList.add(new NotificationModel(getString(R.string.tv_faq_line),getString(R.string.tv_notf_date),getString(R.string.tv_notf_time)));
        notificationModelList.add(new NotificationModel(getString(R.string.tv_faq_line),getString(R.string.tv_notf_date),getString(R.string.tv_notf_time)));
        notificationModelList.add(new NotificationModel(getString(R.string.tv_faq_line),getString(R.string.tv_notf_date),getString(R.string.tv_notf_time)));
        notRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        notificationAdapter=new NotificationAdapter(this,notificationModelList);
        notRecyclerView.setAdapter(notificationAdapter);
    }
}