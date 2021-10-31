package com.lynhill.wingallery.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lynhill.wingallery.R;
import com.lynhill.wingallery.adapter.ImageFoldersAdapter;
import com.lynhill.wingallery.databinding.ActivityHomeBinding;
import com.lynhill.wingallery.model.ShowImageFoldersModel;
import com.lynhill.wingallery.model.ShowImagesModel;
import com.lynhill.wingallery.utils.ItemListenToClick;
import com.lynhill.wingallery.utils.MyPicViewHolder;

import java.util.ArrayList;

public class ShowFoldersActivity extends AppCompatActivity implements ItemListenToClick {
    /*TODO for viewBinding*/
    ActivityHomeBinding hvb;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hvb=ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(hvb.getRoot());
          /*TODO For requset permission*/
         requestPermission();
         /*TODO for set folder in recyclerview*/
         setRecyclerView();

    }


    /*TODO for set folder in recyclerview*/
    private void setRecyclerView() {
        // set a GridLayoutManager with 3 number of columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        hvb.folderRecycler.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        hvb.folderRecycler.hasFixedSize();
        /*TODO for getPicturePath*/
        ArrayList<ShowImageFoldersModel> folders=getPicturePath();
        Log.d("foldermmm",folders.toString());

        if(folders.isEmpty())
        {
            hvb.empty.setVisibility(View.VISIBLE);
        }
        else {
            RecyclerView.Adapter adapter=new ImageFoldersAdapter(folders,this,this);
            Log.d("gdhgdd",folders.toString());
            hvb.folderRecycler.setAdapter(adapter);
        }
        changeStatusBarColor();
    }


    /*TODO For requset permission*/
    private void requestPermission() {
        if ((ContextCompat.checkSelfPermission(ShowFoldersActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
                && (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED))
//            if ((ActivityCompat.shouldShowRequestPermissionRationale(HomeActivity.this,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(HomeActivity.this,
//                    Manifest.permission.READ_EXTERNAL_STORAGE))) {
//
//            } else {
//                ActivityCompat.requestPermissions(HomeActivity.this,
//                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
//                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
//            }
             ActivityCompat.requestPermissions(ShowFoldersActivity.this,
                     new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                     MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
    }


    /*TODO for getPicturePath*/
    private ArrayList<ShowImageFoldersModel> getPicturePath() {
        ArrayList<ShowImageFoldersModel> picfolders=new ArrayList<>();
          ArrayList<String> picPath=new ArrayList<>();

         /*uri = MediaStore.Files.getContentUri("external");
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(FileColumns._ID));
            int media = cursor.getInt(cursor.getColumnIndexOrThrow(FileColumns.MEDIA_TYPE));
            Uri uri = Uri.withAppendedPath(this.uri, "" + id);
            list.add(new Media(uri.toString(), media == FileColumns.MEDIA_TYPE_VIDEO));*/

        Uri allImagesUri= MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//        String[] projection={MediaStore.Images.ImageColumns.DATA,MediaStore.Images.Media.DISPLAY_NAME,
//                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,MediaStore.Images.Media.BUCKET_ID,MediaStore.Images.Media._ID};
        //String[] projection={MediaStore.MediaColumns.DATA};
        //final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        /*String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                                    MediaStore.Images.Media.DATE_TAKEN,
                                          MediaStore.Images.Media.DATE_ADDED};*/
        /////////////////////////
        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME,};

        final String orderBy = MediaStore.Images.Media.DISPLAY_NAME;
        //cursor = getApplicationContext().getContentResolver().query(uri, projection, null, null, orderBy + " DESC");
        //////////////////////////////
        Cursor cursor=this.getContentResolver().query(allImagesUri,projection,null,null,orderBy);
        try {

            if (cursor != null) {
                cursor.moveToFirst();
            }
            do {
                ShowImageFoldersModel folders = new ShowImageFoldersModel();
                //String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME));
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
    /*TODO Version set stautus */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void changeStatusBarColor()
    {
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.main_dark_color));

    }

    @Override
    public void onPicClicked(MyPicViewHolder holder, int position, ArrayList<ShowImagesModel> pics) {

    }

    @Override
    public void onPicClicked(String pictureFolderPath, String folderName) {
        Intent intent=new Intent(ShowFoldersActivity.this,DisplayImagesActivity.class);
        intent.putExtra("folderPath",pictureFolderPath);
        intent.putExtra("folderName",folderName);
        startActivity(intent);

    }
}