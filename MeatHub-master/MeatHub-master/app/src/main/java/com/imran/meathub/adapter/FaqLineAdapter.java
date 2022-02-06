package com.imran.meathub.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imran.meathub.R;
import com.imran.meathub.activity.model.FaqModel;

import java.util.List;

public class FaqLineAdapter extends RecyclerView.Adapter<FaqLineAdapter.ViewHolder> {
    private List<FaqModel> faqModelList;
    private Context context;

    public FaqLineAdapter(Context context,List<FaqModel> faqModelList) {
        this.faqModelList = faqModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.faq_layout_lines, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.tv_faq.setText(faqModelList.get(position).getFaqTextView());
       holder.faq_right_arrow.setImageResource(faqModelList.get(position).getRightArrow());
    }

    @Override
    public int getItemCount() {
        return faqModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_faq;
        ImageView faq_right_arrow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_faq=itemView.findViewById(R.id.tv_faq);
            faq_right_arrow=itemView.findViewById(R.id.faq_right_arrow);
        }
    }
}
