package com.lynhill.wingallery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lynhill.wingallery.databinding.PictureFolderItemBinding;
import com.lynhill.wingallery.fragment.Photos_folders_Fragment;
import com.lynhill.wingallery.model.ShowImageFoldersModel;
import com.lynhill.wingallery.utils.ItemListenToClick;

import java.util.ArrayList;

public class Images_Folders_Adapter extends RecyclerView.Adapter<Images_Folders_Adapter.MyViewHolder> {

    /*TODO viewBinding*/
    /// PictureFolderItemBinding vb; not use
    private ArrayList<ShowImageFoldersModel> folderList;
    private Context mContext;
    private ItemListenToClick listenToClick;

    public Images_Folders_Adapter(ArrayList<ShowImageFoldersModel> folderList, Context mContext, ItemListenToClick listen) {
        this.folderList = folderList;
        this.mContext = mContext;
        this.listenToClick=listen;
    }

//    public Images_Folders_Adapter(FragmentActivity activity, Photos_folders_Fragment photos_folders_fragment) {
//    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(PictureFolderItemBinding.inflate(LayoutInflater.from(mContext),parent,false));
//        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_folder_item,parent,false);
//        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ShowImageFoldersModel folder=folderList.get(position);
        Glide.with(mContext).load(folder.getFirstPic())
                .apply(new RequestOptions().centerCrop())
                .into(holder.vb.folderPicImageView);
        /*TODO set the no of images and folder name*/
        String text=""+folder.getFolderName();
        holder.vb.folderName.setText(text);
        String fsize=""+folder.getNumberOfPics();
        holder.vb.folderSize.setText(fsize);
        holder.vb.linearLayout.setOnClickListener(v->{
             listenToClick.onPicClicked(folder.getPath(),folder.getFolderName(),"image");
        });
    }

    @Override
    public int getItemCount() {
        return folderList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
         PictureFolderItemBinding vb;
        public MyViewHolder(@NonNull PictureFolderItemBinding itemView) {
            super(itemView.getRoot());
            vb=itemView;
        }
    }
}
