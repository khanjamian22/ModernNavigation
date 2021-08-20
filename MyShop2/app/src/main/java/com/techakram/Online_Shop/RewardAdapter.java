package com.techakram.Online_Shop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RewardAdapter extends RecyclerView.Adapter<RewardAdapter.ViewHolder> {

    private List<RewardModel> rewardModelList;
    private Boolean useminiLayout=false;
    public RewardAdapter(List<RewardModel> rewardModelList,Boolean useminiLayout) {
        this.rewardModelList = rewardModelList;
        this.useminiLayout=useminiLayout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (useminiLayout) {
            view = LayoutInflater.from(parent.getContext( )).inflate(R.layout.mini_rewards_item_layout, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext( )).inflate(R.layout.reward_item_layout, parent, false);
        }
        return new ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       String title=rewardModelList.get(position).getTitle();
       String date=rewardModelList.get(position).getExpiryDate();
       String body=rewardModelList.get(position).getRewardBody();
       holder.setData(title,date,body);
    }

    @Override
    public int getItemCount() {
        return rewardModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView reward_imageView;
        TextView tv_title,tv_expiryDate,tv_rewardBody;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            reward_imageView=itemView.findViewById(R.id.reward_imageView);
            tv_title=itemView.findViewById(R.id.title_textView);
            tv_expiryDate=itemView.findViewById(R.id.expiry_datae_textView);
            tv_rewardBody=itemView.findViewById(R.id.body_textView);
        }
        private void setData(String title,String date,String body)
        {
            tv_title.setText(title);
            tv_expiryDate.setText(date);
            tv_rewardBody.setText(body);
            if(useminiLayout)
            {
                itemView.setOnClickListener(new View.OnClickListener( ) {
                    @Override
                    public void onClick(View v) {
                      ProductDetailsActivity.coupenTitle.setText(title);
                        ProductDetailsActivity.coupenExpiryDate.setText(date);
                        ProductDetailsActivity.coupenBody.setText(body);
                        ProductDetailsActivity.settoggleRecyclerView();
                    }
                });
            }

        }
    }
}
