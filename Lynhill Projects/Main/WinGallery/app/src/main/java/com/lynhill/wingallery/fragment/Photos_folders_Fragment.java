package com.lynhill.wingallery.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lynhill.wingallery.R;
import com.lynhill.wingallery.activity.Display_Images_OR_Videos_Activity;
import com.lynhill.wingallery.adapter.Images_Folders_Adapter;
import com.lynhill.wingallery.adapter.ShowImagesAdapter;
import com.lynhill.wingallery.databinding.FragmentPhotosFoldersBinding;
import com.lynhill.wingallery.model.ShowImageFoldersModel;
import com.lynhill.wingallery.model.ShowImagesModel;
import com.lynhill.wingallery.utils.ItemListenToClick;

import java.util.ArrayList;

public class Photos_folders_Fragment extends Fragment implements ItemListenToClick {

    private Images_Folders_Adapter images_folders_adapter;
    private RecyclerView mRecyclerView;
    private ArrayList<ShowImageFoldersModel> imageFoldersModels=new ArrayList<>();

    public Photos_folders_Fragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
             View view= inflater.inflate(R.layout.fragment_photos_folders_, container, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        mRecyclerView=view.findViewById(R.id.Images_folder_recycler);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        /*TODO for getPicturePath*/
         imageFoldersModels=getPicturePath();
         images_folders_adapter=new Images_Folders_Adapter(imageFoldersModels,getContext(),this);
          mRecyclerView.setAdapter(images_folders_adapter);
        return view;
    }
    /*TODO for getPicturePath*/
    private ArrayList<ShowImageFoldersModel> getPicturePath() {
        ArrayList<ShowImageFoldersModel> picfolders=new ArrayList<>();
        ArrayList<String> picPath=new ArrayList<>();

        Uri allImagesUri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME,};

        final String orderBy = MediaStore.Images.Media.DISPLAY_NAME;

        Cursor cursor=getContext().getContentResolver().query(allImagesUri,projection,null,null,orderBy);
        try {

            if (cursor != null) {
                Log.d("ccccc",cursor.toString());
                cursor.moveToFirst();
            }
            do {
                ShowImageFoldersModel folders = new ShowImageFoldersModel();

                String folderName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
                String dataPath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA));
                //String folderpaths =  datapath.replace(name,"");
                String folderPaths = dataPath.substring(0, dataPath.lastIndexOf(folderName + "/"));
                folderPaths = folderPaths + folderName + "/";
                if (!picPath.contains(folderPaths)) {
                    picPath.add(folderPaths);
                    folders.setPath(folderPaths);
                    folders.setFolderName(folderName);
                    folders.setFirstPic(dataPath);
                    folders.addPics();
                    picfolders.add(folders);
                    Log.d("nnnnn",picfolders.toString());
                } else {
                    for (int i = 0; i < picfolders.size(); i++) {
                        if (picfolders.get(i).getPath().equals(folderPaths)) {
                            picfolders.get(i).setFirstPic(dataPath);
                            picfolders.get(i).addPics();
                        }
                    }
                }
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        for(int i = 0;i < picfolders.size();i++){
            Log.d("picture folders",picfolders.get(i).getFolderName()+" and path = "+picfolders.get(i).getPath()+" "+picfolders.get(i).getNumberOfPics());
        }
        return picfolders;
    }

    @Override
    public void onPicClicked(ShowImagesAdapter.MyPicViewHolder holder, int position, ArrayList<ShowImagesModel> pics,String tag) {

    }

    @Override
    public void onPicClicked(String pictureFolderPath, String folderName, String tag) {

        Intent intent=new Intent(getContext(), Display_Images_OR_Videos_Activity.class);
        intent.putExtra("folderPath",pictureFolderPath);
        intent.putExtra("folderName",folderName);
        intent.putExtra("Tab",tag);
        startActivity(intent);
    }

    @Override
    public void onImageClicked(int ImagePosition) {

    }

    @Override
    public void onVideoClicked(int VideoPosition,String tag) {

    }
}