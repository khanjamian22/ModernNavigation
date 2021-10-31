package com.lynhill.wingallery.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import com.lynhill.wingallery.R;
import com.lynhill.wingallery.adapter.ShowImagesAdapter;
import com.lynhill.wingallery.adapter.Videos_Adapter;
import com.lynhill.wingallery.model.ShowImagesModel;
import com.lynhill.wingallery.model.ShowVideosModel;
import com.lynhill.wingallery.utils.ItemListenToClick;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Display_Images_OR_Videos_Activity extends AppCompatActivity implements ItemListenToClick, View.OnLongClickListener {
    /*TODO for videos*/
    Videos_Adapter videoGridViewAdapter;
    ArrayList<ShowVideosModel> allVideos ;
    ArrayList<ShowVideosModel> vselectionList;
    /*TODO for images*/
    ShowImagesAdapter showImagesAdapter;
    ArrayList<ShowImagesModel> showImagesModelArrayList;
    ArrayList<ShowImagesModel> selectionList;
    String folderName;
    String folderPath,videofolderPath;
    String tab;
    private Toolbar toolbar;
    TextView itemCounter;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    int counter = 0;
    int REQUEST_PERM_DELETE=101;
    String id;
    public static boolean isContexualModeEnablede = false;
    public static boolean longClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_images);
        isContexualModeEnablede=false;
        longClick = false;
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
        toolbar = (Toolbar) findViewById(R.id.custom_toolBar);
        toolbar.findViewById(R.id.custom_tool_backbtn);
        itemCounter = findViewById(R.id.itemCounter);
        progressBar = findViewById(R.id.prgsLoader);
        recyclerView = findViewById(R.id.dispimgs_recycler);
        id=getIntent().getStringExtra("myView");
        setSupportActionBar(toolbar);

        showImagesModelArrayList = new ArrayList<>();
        allVideos=new ArrayList<>();
        selectionList = new ArrayList<>();
        vselectionList=new ArrayList<>();
        toolbar.setOnClickListener(v->{
           finish();
        });

    }

    /*TODO set recycler*/
    private void setRecyclerview() {
        if (showImagesModelArrayList.isEmpty()) {
            Log.d("myrepeat", "sertrecycler");
            // set a GridLayoutManager with 3 number of columns
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
            recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
            recyclerView.hasFixedSize();
            progressBar.setVisibility(View.VISIBLE);
            folderPath = getIntent().getStringExtra("folderPath");
            videofolderPath = getIntent().getStringExtra("videofolderPath");
            folderName = getIntent().getStringExtra("folderName");
            tab = getIntent().getStringExtra("Tab");
            Log.d("tabhhfhf",tab.toString());
            itemCounter.setText(folderName);
            allVideos =getAllVideosByfolder(videofolderPath);
            Log.d("ggsgs",allVideos.toString());
            showImagesModelArrayList = getAllImagesByfolder(folderPath);
            if (tab.equalsIgnoreCase("image"))
            {
                Log.d("mylist", showImagesModelArrayList.toString());
            showImagesAdapter = new ShowImagesAdapter(showImagesModelArrayList, this, this);
            recyclerView.setAdapter(showImagesAdapter);

                showImagesAdapter.notifyDataSetChanged();

            }

            else  {

                videoGridViewAdapter=new Videos_Adapter(allVideos,this,this);
                recyclerView.setAdapter(videoGridViewAdapter);
                videoGridViewAdapter.notifyDataSetChanged();
            }
        }

            Log.d("fsffsvdfdt", showImagesModelArrayList.toString());
            progressBar.setVisibility(View.GONE);
      }

    @Override
    public void onImageClicked(int ImagePosition) {

    }

    @Override
    public void onVideoClicked(int position,String tag) {
        Intent intent=new Intent(Display_Images_OR_Videos_Activity.this, Show_SingleVideoActivity.class);
        intent.putExtra("list",allVideos);
        Log.d("mmmmm",allVideos.toString());
        intent.putExtra("folder",folderName);
        intent.putExtra("videoPosition",position);
        intent.putExtra("Tab",tag);
        startActivity(intent);

    }

    @Override
    public void onPicClicked(ShowImagesAdapter.MyPicViewHolder holder, int position, ArrayList<ShowImagesModel> pics,String tag) {
        Intent intent = new Intent(Display_Images_OR_Videos_Activity.this, Show_SingleImage_Activity.class);
        intent.putExtra("list", showImagesModelArrayList);
        intent.putExtra("folder", folderName);
        intent.putExtra("picposition",position);
        intent.putExtra("Tab",tag);
        startActivity(intent);
    }

    @Override
    public void onPicClicked(String pictureFolderPath, String folderName,String tag) {

    }

    /*TODO get all images byfolder in arrayList*/
    public ArrayList<ShowImagesModel> getAllImagesByfolder(String path) {
        ArrayList<ShowImagesModel> images = new ArrayList<>();
        Uri allImagesUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        //String orderby=MediaStore.Images.Media.DATE_TAKEN;
        String[] projection = {MediaStore.Images.ImageColumns.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.SIZE,
                MediaStore.Images.ImageColumns.DATE_TAKEN};
        Cursor cursor = this.getContentResolver().query(allImagesUri, projection, MediaStore.Images.Media.DATA + " like ? ", new String[]{"%" + path + "%"}, null);
        try {
            if (cursor != null) {
                cursor.moveToFirst();
            }
            do {
                ShowImagesModel picture = new ShowImagesModel();
                picture.setPicturName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)));
                picture.setPicturePath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)));
                picture.setPictureSize(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)));
               //   picture.setPicturePath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)));
                /** convet date formate */


                String dateTakenIndex =cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA));
                String  mdate =(dateTakenIndex);

                //picture.setImageDate(String.valueOf(mdate));

                //picture.setImageDate(mdate);
                Log.d("ggdgd", String.valueOf(mdate));
                /** convert date time*/
                File file = new File(mdate);
                if(file.exists()) //Extra check, Just to validate the given path
                {
                    ExifInterface intf = null;
                    try
                    {
                        intf = new ExifInterface(mdate);
                        if(intf != null)
                        {
                            String dateString = intf.getAttribute(ExifInterface.TAG_DATETIME);
                            Log.i("PHOTO DATE", "Dated : "+ dateString); //Display dateString. You can do/use it your own way
                            picture.setImageDate(dateString);
                        }
                    }
                    catch (IOException e)
                    {
                    }
                    if(intf == null)
                    {
                        Date lastModDate = new Date(file.lastModified());
                        Log.i("PHOTO DATE", "Dated : "+ lastModDate.toString());//Dispaly lastModDate. You can do/use it your own way
                        picture.setImageDate(String.valueOf(lastModDate));
                    }
                }
                /* Do your date/time stuff here */
                /**Convert bytes to MB*/
                 Long size= Long.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)));
                 long fileSizeInKB = size / 1024;
                 //long fileSizeInMB = fileSizeInKB / 1024;
                  picture.setPictureSize(String.valueOf(fileSizeInKB));
                //all all attribute in array
                images.add(picture);
            }
            while (cursor.moveToNext());
            cursor.close();
            /*TODO for reselection*/
            ArrayList<ShowImagesModel> reselect = new ArrayList<>();
            for (int i = images.size() - 1; i > -1; i--) {
                reselect.add(images.get(i));

            }
            images = reselect;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return images;
    }


    /*TODO get all videos byfolder in arrayList*/
    public  ArrayList<ShowVideosModel> getAllVideosByfolder(String path){
         int duration = 0;
        ArrayList<ShowVideosModel> videos=new ArrayList<>();
        Uri allVideosUri= MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection={MediaStore.Video.VideoColumns.DATA,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.SIZE,
                MediaStore.Video.Media.DATE_TAKEN};
        Cursor cursor=this.getContentResolver().query(allVideosUri,projection,MediaStore.Video.Media.DATA + " like ? ", new String[] {"%"+path+"%"},null);
        try {
            if(cursor!=null)
            {
                cursor.moveToFirst();
            }
            do {
                ShowVideosModel video=new ShowVideosModel();
                video.setVideoName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)));
                video.setVideoPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)));

                video.setVideoSize(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)));
               // video.setVideoDate(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_TAKEN)));
                  String dateTakenIndex = String.valueOf(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA));
                  String mdate = cursor.getString(Integer.parseInt(dateTakenIndex));
                /** convert for date formate */

                File file = new File(mdate);
                if(file.exists()) //Extra check, Just to validate the given path
                {
                    ExifInterface intf = null;
                    try
                    {
                        intf = new ExifInterface(mdate);
                        if(intf != null)
                        {
                            String dateString = intf.getAttribute(ExifInterface.TAG_DATETIME);
                            Log.i("PHOTO DATE", "Dated : "+ dateString); //Display dateString. You can do/use it your own way
                            video.setVideoDate(dateString);
                        }
                    }
                    catch (IOException e)
                    {
                    }
                    if(intf == null)
                    {
                        Date lastModDate = new Date(file.lastModified());
                        Log.i("PHOTO DATE", "Dated : "+ lastModDate.toString());//Dispaly lastModDate. You can do/use it your own way
                        video.setVideoDate(String.valueOf(lastModDate));
                    }
                }
//

                      Log.d("ndate", String.valueOf(mdate));

                /** convert bytes to mb*/
                Long size= Long.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)));
                long fileSizeInKB = size / 1024;
// Convert      the KB to MegaBytes (1 MB = 1024 KBytes)
                long fileSizeInMB = fileSizeInKB / 1024;
                video.setVideoSize(String.valueOf(fileSizeInMB));
                //all all attribute in array
                 videos.add(video);
            }
            while (cursor.moveToNext());
            cursor.close();
            /*TODO for reselection*/
            ArrayList<ShowVideosModel> reselect=new ArrayList<>();
            for (int i=videos.size()-1; i>-1; i--){
                reselect.add(videos.get(i));

            }
            videos=reselect;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return videos;
    }

    /*hdfuhfuhihug*/
    @Override
    public boolean onLongClick(View view) {
        //Log.d("checkView", String.valueOf(view.getId())+"   "+R.id.video_view );
        Log.d("ff", getResources().getResourceEntryName(view.getId()));
        String id =getResources().getResourceEntryName(view.getId());
        Log.d("bgh", id.toString());
        isContexualModeEnablede = true;
        //longClick = true;

        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_list);
        toolbar.setBackgroundColor(getColor(R.color.white));
        if (tab.equalsIgnoreCase("image"))
        {
            showImagesAdapter.notifyDataSetChanged();
        }
        else {
            videoGridViewAdapter.notifyDataSetChanged();
             }

          // return true;
        longClick = true;
        return true;
    }

    /*TODO For images selection and unselections */
    public void MakeSelection(View v, int adapterPosition) {
        if (((CheckBox) v).isChecked()) {
            Log.d("imggg", showImagesModelArrayList.get(adapterPosition).toString());
            selectionList.add(showImagesModelArrayList.get(adapterPosition));
            //Log.d("imggg", showImagesModelArrayList.get(adapterPosition).toString());
            Log.d("ffff", selectionList.toString());
            counter++;
            updateCounter();
        } else {
            selectionList.remove(showImagesModelArrayList.get(adapterPosition));
            Log.d("gggbderysdegcbhdcgg", showImagesModelArrayList.get(adapterPosition).toString());
            counter--;
            updateCounter();
        }
    }

    /*TODO For videos selection and unselections */
    public void VMakeSelection(View video, int video_adapterPosition) {
                if(((CheckBox) video).isChecked()){
                    Log.d("vidici", allVideos.get(video_adapterPosition).toString());
            vselectionList.add(allVideos.get(video_adapterPosition));
           // Log.d("vidici", allVideos.get(video_adapterPosition).toString());
            Log.d("ffff", vselectionList.toString());
            counter++;
            updateCounter();
        }
        else {
            //Log.d("gggbderysdegcbhdcgg",showImagesModelArrayList.get(adapterPosition).toString());
            vselectionList.remove(allVideos.get(video_adapterPosition));
            Log.d("gggbderysdegcbhdcgg", allVideos.get(video_adapterPosition).toString());
            counter--;
            updateCounter();
        }
    }

    public void updateCounter() {
        itemCounter.setText(counter + "item Selected");
    }

    /*TODO option menu*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.normal_menu, menu);
        return true;
    }
    /*TODO option item selected menu*/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete) {
            if (tab.equalsIgnoreCase("image"))
            {
                imagesDelete();
                showImagesAdapter.RemoveItem(selectionList);
                showImagesAdapter.notifyDataSetChanged();
                RemoveContexualMode();
            }
           else {
                videoDelete();
                videoGridViewAdapter.RemoveItem(vselectionList);
                videoGridViewAdapter.notifyDataSetChanged();
                VideoRemoveContexualMode();
                }

            //VideoRemoveContexualMode();
        } else if (item.getItemId() == R.id.share)
        {
            if (tab.equalsIgnoreCase("image"))
            {
                imagesShare();
                showImagesAdapter.RemoveItem(selectionList);
                RemoveContexualMode();
                showImagesAdapter.notifyDataSetChanged();
            }
            else
                {
                videoShare();
                videoGridViewAdapter.RemoveItem(vselectionList);
                VideoRemoveContexualMode();
                videoGridViewAdapter.notifyDataSetChanged();
                }
        }
        return true;
    }

    /*TODO Share Common Files*/
    private void shareFiles(ArrayList<Uri> uriList){
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("image/jpeg/video");
        share.putExtra(Intent.EXTRA_STREAM,uriList);
        startActivity(Intent.createChooser(share, "Share Files"));

    }

    /*TODO for Images RemoveContexualMode*/
    private void RemoveContexualMode() {
        isContexualModeEnablede = false;
        itemCounter.setText(folderName);
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.normal_menu);
        counter = 0;
        selectionList.clear();
        showImagesAdapter.notifyDataSetChanged();
    }

    /*TODO for VideoRemoveContexualMode*/
    private void VideoRemoveContexualMode() {
        isContexualModeEnablede = false;
        itemCounter.setText(folderName);
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.normal_menu);
        counter = 0;
        selectionList.clear();
        videoGridViewAdapter.notifyDataSetChanged();
    }

    /*todo delete for videos*/
    public void videoDelete() {
          List<Uri> uriList=new ArrayList<Uri>();
        for (int i = 0; i < vselectionList.size(); i++) {
             String uri = vselectionList.get(i).getVideoPath();
             File file = new File(uri);
            long mediaID=getFilePathToMediaID(file.getAbsolutePath(),  this);

            Uri Uri_one = ContentUris.withAppendedId( MediaStore.Video.Media.getContentUri("external"),mediaID);
            uriList.add(Uri_one);
        }
        Log.d("myuri",Arrays.toString(new List[]{uriList}));

         requestDeletePermission(uriList);

    }

    /*Todo delete for images*/
    public void imagesDelete() {
        List<Uri> uriList=new ArrayList<Uri>();
        for (int i = 0; i < selectionList.size(); i++) {
            String uri = selectionList.get(i).getPicturePath();
            File file = new File(uri);
            long mediaID=getFilePathToMediaID(file.getAbsolutePath(),  this);

            Uri Uri_one = ContentUris.withAppendedId( MediaStore.Images.Media.getContentUri("external"),mediaID);
            uriList.add(Uri_one);
        }
        Log.d("myuri",Arrays.toString(new List[]{uriList}));

        requestDeletePermission(uriList);

    }

  /*TODO sharing files for video*/
    public void videoShare() {
        List<Uri> uriList=new ArrayList<Uri>();
        for (int i = 0; i < vselectionList.size(); i++) {

            String uri = vselectionList.get(i).getVideoPath();
            File file = new File(uri);

//             getContentResolver().delete(Uri.parse("content:/"+uri),null,null);
            long mediaID=getFilePathToMediaID(file.getAbsolutePath(),  this);

            Uri Uri_one = ContentUris.withAppendedId( MediaStore.Video.Media.getContentUri("external"),mediaID);
            uriList.add(Uri_one);

        }
        Log.d("myuri",Arrays.toString(new List[]{uriList}));

         shareFiles((ArrayList<Uri>) uriList);
    }

    /*TODO sharing files for images*/
    public void imagesShare() {
        List<Uri> uriList=new ArrayList<Uri>();
        for (int i = 0; i < selectionList.size(); i++) {

            String uri = selectionList.get(i).getPicturePath();
            File file = new File(uri);

            long mediaID=getFilePathToMediaID(file.getAbsolutePath(),  this);

            Uri Uri_one = ContentUris.withAppendedId( MediaStore.Images.Media.getContentUri("external"),mediaID);
            uriList.add(Uri_one);

        }
        Log.d("myuri",Arrays.toString(new List[]{uriList}));

        shareFiles((ArrayList<Uri>) uriList);
    }

    /*TODO getFilePath to Media Id*/
    public long getFilePathToMediaID(String songPath, Context context)
    {
        long id = 0;
        ContentResolver cr = context.getContentResolver();

        Uri uri = MediaStore.Files.getContentUri("external");
        String selection = MediaStore.Audio.Media.DATA;
        String[] selectionArgs = {songPath};
        String[] projection = {MediaStore.Audio.Media._ID};
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";

        Cursor cursor = cr.query(uri, projection, selection + "=?", selectionArgs, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {

                int idIndex = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
                id = Long.parseLong(cursor.getString(idIndex));
            }
        }

        return id;
    }

    /*TODO delete request for android 11  */
    private void requestDeletePermission(List<Uri> uriList){

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            PendingIntent pi = MediaStore.createDeleteRequest(getContentResolver(), uriList);

            try {
//                Intent intent = new Intent(pi.getIntentSender());

                startIntentSenderForResult(pi.getIntentSender(), REQUEST_PERM_DELETE, null, 0, 0,
                        0);
            } catch (IntentSender.SendIntentException e) { }
        }
    }
    /*TODO on Resume method*/
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onfget","onresume start");
        setRecyclerview();

    }
    /*TODO  for time from file*/
//    Path path = Paths.get("C:\\1.txt");
//
//    FileTime fileTime;
//  try {
//        fileTime = Files.getLastModifiedTime(path);
//        printFileTime(fileTime);
//    } catch (IOException e) {
//        System.err.println("Cannot get the last modified time - " + e);
//    }
//    where printFileName can look like this:
//
//    private static void printFileTime(FileTime fileTime) {
//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
//        System.out.println(dateFormat.format(fileTime.toMillis()));
//    }
}