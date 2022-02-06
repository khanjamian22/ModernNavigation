package com.imran.meathub.activity.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.imran.meathub.R;
import com.imran.meathub.activity.model.FaqModel;
import com.imran.meathub.adapter.FaqLineAdapter;

import java.util.ArrayList;
import java.util.List;

public class FaqActivity extends AppCompatActivity {
     private RecyclerView faqRecyclerView;
     private FaqLineAdapter faqLineAdapter;
     private List<FaqModel> faqModelList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        initView();
        setRecyclerView();
    }

    private void initView() {
        faqRecyclerView=findViewById(R.id.faq_recycler_view);
    }
    private void setRecyclerView() {
        faqModelList.add(new FaqModel(getString(R.string.tv_faq_line),R.drawable.rightarr));
        faqModelList.add(new FaqModel(getString(R.string.tv_faq_line),R.drawable.rightarr));
        faqModelList.add(new FaqModel(getString(R.string.tv_faq_line),R.drawable.rightarr));
        faqModelList.add(new FaqModel(getString(R.string.tv_faq_line),R.drawable.rightarr));
        faqModelList.add(new FaqModel(getString(R.string.tv_faq_line),R.drawable.rightarr));
        faqModelList.add(new FaqModel(getString(R.string.tv_faq_line),R.drawable.rightarr));
        faqModelList.add(new FaqModel(getString(R.string.tv_faq_line),R.drawable.rightarr));
        faqModelList.add(new FaqModel(getString(R.string.tv_faq_line),R.drawable.rightarr));
        faqModelList.add(new FaqModel(getString(R.string.tv_faq_line),R.drawable.rightarr));
        faqModelList.add(new FaqModel(getString(R.string.tv_faq_line),R.drawable.rightarr));
        faqModelList.add(new FaqModel(getString(R.string.tv_faq_line),R.drawable.rightarr));
        faqModelList.add(new FaqModel(getString(R.string.tv_faq_line),R.drawable.rightarr));
        faqModelList.add(new FaqModel(getString(R.string.tv_faq_line),R.drawable.rightarr));
        faqModelList.add(new FaqModel(getString(R.string.tv_faq_line),R.drawable.rightarr));
        faqModelList.add(new FaqModel(getString(R.string.tv_faq_line),R.drawable.rightarr));
        faqRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        faqLineAdapter=new FaqLineAdapter(this,faqModelList);
        faqRecyclerView.setAdapter(faqLineAdapter);
    }

}