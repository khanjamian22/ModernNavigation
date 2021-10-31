package com.lynhill.wingallery.adapter;

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
import com.lynhill.wingallery.databinding.PicHolderItemBinding;
import com.lynhill.wingallery.model.ShowImagesModel;
import com.lynhill.wingallery.utils.ItemListenToClick;


import java.util.ArrayList;

public class ShowImagesAdapter extends RecyclerView.Adapter<ShowImagesAdapter.MyPicViewHolder> {
    PicHolderItemBinding phvb;
    private ArrayList<ShowImagesModel> showImagesModelList;
    Display_Images_OR_Videos_Activity displayImagesActivity;
    private final ItemListenToClick imageListener;

    public ShowImagesAdapter(ArrayList<ShowImagesModel> showImagesModelList, Display_Images_OR_Videos_Activity displayImagesActivity, ItemListenToClick imageListener) {
        this.showImagesModelList = showImagesModelList;
       this.displayImagesActivity=displayImagesActivity;
        this.imageListener = imageListener;
    }

    @NonNull
    @Override
    public MyPicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return new MyPicViewHolder(PicHolderItemBinding.inflate(LayoutInflater.from(mContext),parent,false));
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.pic_holder_item,parent,false);
        MyPicViewHolder myPicViewHolder=new MyPicViewHolder(v,displayImagesActivity);
         return myPicViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyPicViewHolder holder, int position) {
        final ShowImagesModel image=showImagesModelList.get(position);
       if(!Display_Images_OR_Videos_Activity.isContexualModeEnablede)
       {
           holder.checkBox.setVisibility(View.GONE);
       }
       else {
           holder.checkBox.setVisibility(View.VISIBLE);
           holder.checkBox.setChecked(false);

       }

        Glide.with(displayImagesActivity).load(image.getPicturePath())
                .into(holder.imageView);

         //setTransitionName(holder.imageView, String.valueOf(position) + "_image");
         holder.imageView.setOnClickListener(v->{
             if (Display_Images_OR_Videos_Activity.longClick) {
                 // do scaling and moving ...
                 Display_Images_OR_Videos_Activity.longClick = true;

                 Log.d("myposition", String.valueOf(position));
                 return;
                 // imageListener.onImageClicked(position);

             }
             else {
                 imageListener.onPicClicked(holder, position, showImagesModelList,"image");
             }

         });

    }

    @Override
    public int getItemCount() {
        return showImagesModelList.size();
    }


    public class MyPicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView imageView;
        CheckBox checkBox;
        View view;

        public MyPicViewHolder(@NonNull View itemView, Display_Images_OR_Videos_Activity displayImagesActivity) {
            super(itemView);

            imageView=itemView.findViewById(R.id.image);
            checkBox=itemView.findViewById(R.id.checkbox);
            view=itemView;
            imageView.setOnLongClickListener(displayImagesActivity);
            //Log.d("kkkk","imageOnlong");
            checkBox.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            /*TODO for check clicked */
          displayImagesActivity.MakeSelection(v,getAdapterPosition());
            Log.d("kkkk","imageOnlong");
        }
    }
    public void RemoveItem(ArrayList<ShowImagesModel> selectionList) {

      for (int i=0;i<selectionList.size();i++)
      {
          Log.d("hgyugdwywt",(selectionList.get(i).getPicturePath().toString()));
          showImagesModelList.remove(selectionList.get(i));
           notifyDataSetChanged();
      }
    }
}
