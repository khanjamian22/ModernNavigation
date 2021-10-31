package com.lynhill.wingallery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lynhill.wingallery.databinding.VideoFolderItemBinding;
import com.lynhill.wingallery.model.ShowVideoFolders;
import com.lynhill.wingallery.utils.ItemListenToClick;
import java.util.ArrayList;

public class Videos_Folders_Adapter extends RecyclerView.Adapter<Videos_Folders_Adapter.VideoViewHolder> {
    private ArrayList<ShowVideoFolders>  videoFoldersArrayList;
    private Context mContext;
    private ItemListenToClick itemListenToClick;

    public Videos_Folders_Adapter(ArrayList<ShowVideoFolders> videoFoldersArrayList, Context mContext, ItemListenToClick itemListenToClick) {
        this.videoFoldersArrayList = videoFoldersArrayList;
        this.mContext = mContext;
        this.itemListenToClick = itemListenToClick;
    }

    public Videos_Folders_Adapter() {
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoViewHolder(VideoFolderItemBinding.inflate(LayoutInflater.from(mContext),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        final ShowVideoFolders folder=videoFoldersArrayList.get(position);
        Glide.with(mContext).load(folder.getFirstVideo())
                .apply(new RequestOptions().centerCrop())
                .into(holder.vb.folderPicImageView);
        /*TODO set the no of images and folder name*/
        String text=""+folder.getFolderName();
        holder.vb.folderName.setText(text);
        String fsize=""+folder.getNumberOfVideo();
        holder.vb.folderSize.setText(fsize);
        holder.vb.linearLayout.setOnClickListener(v->{
            itemListenToClick.onPicClicked(folder.getPath(),folder.getFolderName(),"video");
        });

    }

    @Override
    public int getItemCount() {
        return videoFoldersArrayList.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        VideoFolderItemBinding vb;
        public VideoViewHolder(VideoFolderItemBinding itemView) {
            super(itemView.getRoot());
            vb=itemView;
        }
    }
}
