package com.lynhill.wingallery.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lynhill.wingallery.R;
import com.lynhill.wingallery.activity.Display_Images_OR_Videos_Activity;
import com.lynhill.wingallery.model.ShowVideosModel;
import com.lynhill.wingallery.utils.ItemListenToClick;

import java.io.Serializable;
import java.util.ArrayList;

public class Videos_Adapter extends RecyclerView.Adapter<Videos_Adapter.MyVideoViewHolder> {
    private ArrayList<ShowVideosModel> showVideosModels;
    Display_Images_OR_Videos_Activity displayImagesActivity;
    private final ItemListenToClick imageListener;

    public Videos_Adapter(ArrayList<ShowVideosModel> showVideosModels, Display_Images_OR_Videos_Activity displayImagesActivity, ItemListenToClick imageListener) {
        this.showVideosModels = showVideosModels;
        this.displayImagesActivity=displayImagesActivity;
        this.imageListener = imageListener;
    }

    @NonNull
    @Override
    public MyVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return new MyPicViewHolder(PicHolderItemBinding.inflate(LayoutInflater.from(mContext),parent,false));
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.video_holder_item,parent,false);
        MyVideoViewHolder myPicViewHolder=new MyVideoViewHolder(v,displayImagesActivity);
        return myPicViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyVideoViewHolder holder, int position) {
        final ShowVideosModel image=showVideosModels.get(position);
        if(!Display_Images_OR_Videos_Activity.isContexualModeEnablede)
        {
            holder.video_checkBox.setVisibility(View.GONE);
        }
        else {
            holder.video_checkBox.setVisibility(View.VISIBLE);
            holder.video_checkBox.setChecked(false);

        }

        Glide.with(displayImagesActivity).load(image.getVideoPath())
                .into(holder.videoView);

        //setTransitionName(holder.imageView, String.valueOf(position) + "_image");
        holder.videoView.setOnClickListener(v->{
            if (Display_Images_OR_Videos_Activity.longClick) {
                // do scaling and moving ...
                Display_Images_OR_Videos_Activity.longClick = true;

                Log.d("myposition", String.valueOf(position));
                return;
                // imageListener.onImageClicked(position);

            }
//
           else {
                imageListener.onVideoClicked(position,"video");
            }
        });
    }

    @Override
    public int getItemCount() {
        return showVideosModels.size();
    }


    public class MyVideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView videoView;
        CheckBox video_checkBox;
        View view;

        public MyVideoViewHolder(@NonNull View itemView, Display_Images_OR_Videos_Activity displayImagesActivity) {
            super(itemView);

            videoView=itemView.findViewById(R.id.video_view);
            video_checkBox=itemView.findViewById(R.id.vcheckbox);
            view=itemView;
            videoView.setOnLongClickListener(displayImagesActivity);
            Log.d("kkkkvv","videoOnlong");
            video_checkBox.setOnClickListener(this);
        }

        @Override
        public void onClick(View video) {
            /*TODO for check clicked */
            displayImagesActivity.VMakeSelection(video,getAdapterPosition());

        }
    }
    public void RemoveItem(ArrayList<ShowVideosModel> vselectionList) {
        for (int i=0;i<vselectionList.size();i++)
        {
            Log.d("hgyugdwywt",(vselectionList.get(i).getVideoPath().toString()));
            showVideosModels.remove(vselectionList.get(i));
            notifyDataSetChanged();
        }
    }
}
