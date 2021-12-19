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
import com.lynhill.wingallery.adapter.Videos_Folders_Adapter;
import com.lynhill.wingallery.model.ShowImagesModel;
import com.lynhill.wingallery.model.ShowVideoFolders;
import com.lynhill.wingallery.utils.ItemListenToClick;

import java.util.ArrayList;

public class Videos_Folders_Fragment extends Fragment implements ItemListenToClick {
    com.lynhill.wingallery.databinding.FragmentVideosFoldersBinding vvb;

    ArrayList<ShowVideoFolders> videoFolders=new ArrayList<>();
    Videos_Folders_Adapter videos_folders_adapter;
    private RecyclerView mRecyclerView;

    public Videos_Folders_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_videos__folders_, container, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView=view.findViewById(R.id.videos_folder_recycler);// set Horizontal Orientation
        mRecyclerView.setLayoutManager(gridLayoutManager);
        /*TODO for getPicturePath*/
        videoFolders=getVideoPath();
        Log.d("videos",videoFolders.toString());
        videos_folders_adapter=new Videos_Folders_Adapter(videoFolders,getContext(),this);

        mRecyclerView.setAdapter(videos_folders_adapter);
        return view;

    }
    /*TODO for getVideoPath*/
    private ArrayList<ShowVideoFolders> getVideoPath() {
        ArrayList<ShowVideoFolders> videofolders=new ArrayList<>();
        ArrayList<String> videoPath=new ArrayList<>();


        Uri allVideosUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.Video.VideoColumns.DATA, MediaStore.Video.Media.BUCKET_DISPLAY_NAME,};

        final String orderBy = MediaStore.Video.Media.DISPLAY_NAME;

        Cursor cursor=getContext().getContentResolver().query(allVideosUri,projection,null,null,orderBy);
        try {

            if (cursor != null) {
                cursor.moveToFirst();
            }
            do {
                ShowVideoFolders folders = new ShowVideoFolders();
                //String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME));
                String folderName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME));
                String dataPath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.VideoColumns.DATA));
                //String folderpaths =  datapath.replace(name,"");
                String folderPaths = dataPath.substring(0, dataPath.lastIndexOf(folderName + "/"));
                folderPaths = folderPaths + folderName + "/";
                if (!videoPath.contains(folderPaths)) {
                    videoPath.add(folderPaths);
                    folders.setPath(folderPaths);
                    folders.setFolderName(folderName);
                    folders.setFirstVideo(dataPath);
                    folders.addvideos();
                    videofolders.add(folders);
                } else {
                    for (int i = 0; i < videofolders.size(); i++) {
                        if (videofolders.get(i).getPath().equals(folderPaths)) {
                            videofolders.get(i).setFirstVideo(dataPath);
                            videofolders.get(i).addvideos();

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
        for(int i = 0;i < videofolders.size();i++){
            Log.d("picture folders",videofolders.get(i).getFolderName()+" and path = "+videofolders.get(i).getPath()+" "+videofolders.get(i).getNumberOfVideo());
        }
        return videofolders;
    }

    @Override
    public void onPicClicked(ShowImagesAdapter.MyPicViewHolder holder, int position, ArrayList<ShowImagesModel> pics,String tag) {

    }

    @Override
    public void onPicClicked(String videoFolderPath, String folderName, String tag) {
        Intent intent=new Intent(getContext(), Display_Images_OR_Videos_Activity.class);
        intent.putExtra("videofolderPath",videoFolderPath);
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