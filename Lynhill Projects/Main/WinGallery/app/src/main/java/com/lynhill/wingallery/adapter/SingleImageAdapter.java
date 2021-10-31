package com.lynhill.wingallery.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.lynhill.wingallery.databinding.PictureBrowserPagerBinding;
import com.lynhill.wingallery.model.ShowImagesModel;

import com.lynhill.wingallery.utils.ItemListenToClick;
import com.lynhill.wingallery.utils.imageIndicatorListener;

import java.util.ArrayList;

public class SingleImageAdapter extends RecyclerView.Adapter<SingleImageAdapter.MyViewHolder> {
     //PictureBrowserPagerBinding pvb;
    ArrayList<ShowImagesModel> pictureList;
    Context mContext;
    public final ItemListenToClick imageListerner;

    public SingleImageAdapter(ArrayList<ShowImagesModel> pictureList, Context mContext, ItemListenToClick imageListerner) {
        this.pictureList = pictureList;
        this.mContext = mContext;
        this.imageListerner = imageListerner;
    }

    @NonNull
    @Override
    public SingleImageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(PictureBrowserPagerBinding.inflate(LayoutInflater.from(mContext),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position) {
        final ShowImagesModel pic = pictureList.get(position);

       // holder.pvb.positionController.setBackgroundColor(pic.getSelected() ? Color.parseColor("#00000000") : Color.parseColor("#8c000000"));

        Glide.with(mContext)
                .load(pic.getPicturePath())
                .apply(new RequestOptions().centerCrop())
                .into(holder.pvb.singleImage);
    }

    @Override
    public int getItemCount() {
        return pictureList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
         PictureBrowserPagerBinding pvb;
        public MyViewHolder(PictureBrowserPagerBinding itemView) {
            super(itemView.getRoot());
            pvb=itemView;
        }
    }
}
