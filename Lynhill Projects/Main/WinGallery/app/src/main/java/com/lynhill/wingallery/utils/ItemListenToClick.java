package com.lynhill.wingallery.utils;

import com.lynhill.wingallery.adapter.ShowImagesAdapter;
import com.lynhill.wingallery.model.ShowImagesModel;

import java.util.ArrayList;

public interface ItemListenToClick {
    void onPicClicked(ShowImagesAdapter.MyPicViewHolder holder, int position, ArrayList<ShowImagesModel> pics,String tag);
    void onPicClicked(String pictureFolderPath,String folderName,String tag);

    void onImageClicked(int ImagePosition);

    void onVideoClicked(int VideoPosition, String tag);
}
