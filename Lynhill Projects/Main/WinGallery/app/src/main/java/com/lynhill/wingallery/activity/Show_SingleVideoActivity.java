package com.lynhill.wingallery.activity;

import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.lynhill.wingallery.R;
import com.lynhill.wingallery.adapter.SingleVideoAdapter;
import com.lynhill.wingallery.databinding.ActivityShowSingleVideoBinding;
import com.lynhill.wingallery.model.ShowImagesModel;
import com.lynhill.wingallery.model.ShowVideosModel;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Show_SingleVideoActivity extends AppCompatActivity {
       ActivityShowSingleVideoBinding vb;
       BottomSheetDialog bottomSheetDialog;
       private Toolbar toolbar;
       int current;
       String tab;
       String fName;
       int REQUEST_PERM_DELETE=101;
       int mvideoposition;
       SingleVideoAdapter madapter;
       private ArrayList<ShowVideosModel> videosModelArrayList=new ArrayList<>();
      private Handler sliderHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vb=ActivityShowSingleVideoBinding.inflate(getLayoutInflater());
        setContentView(vb.getRoot());
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        toolbar=(Toolbar) findViewById(R.id.edit_toolBar);
        setSupportActionBar(toolbar);
        /*
        * TODO for backbutton*/
        vb.backbtn.setOnClickListener(v->{

            finish();
        });
        videosModelArrayList=getIntent().getParcelableArrayListExtra("list");
        mvideoposition=getIntent().getIntExtra("videoPosition",1);
         fName=getIntent().getStringExtra("folder");
         vb.vPager.setAdapter(createCardAdapter());
         vb.itemDate.setText(fName);
         vb.vPager.setCurrentItem(mvideoposition,false);

        /*TODO for counting the images when slide these images*/
        vb.vPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                sliderHandler.removeCallbacks(sliderRunnable);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }
    private SingleVideoAdapter createCardAdapter( )
    {
        SingleVideoAdapter adapter = new SingleVideoAdapter(this, videosModelArrayList);
        Log.d("mylisturl",videosModelArrayList.toString());
        return adapter;
    }
    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            vb.vPager.setCurrentItem( vb.vPager.getCurrentItem() + 1);
        }
    };

    /*TODO for dialog*/
    private void showBottomSheetDialog() {
        bottomSheetDialog = new BottomSheetDialog(this);
        // final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottomsheet_model);
        TextView name=bottomSheetDialog.findViewById(R.id.img_name);

        TextView size=bottomSheetDialog.findViewById(R.id.img_size);
        TextView path=bottomSheetDialog.findViewById(R.id.img_path);
        TextView date=bottomSheetDialog.findViewById(R.id.img_date);


        tab=getIntent().getStringExtra("Tab");

        ImageView close_btn=bottomSheetDialog.findViewById(R.id.close_btn);
         ShowVideosModel list=videosModelArrayList.get(mvideoposition);
        name.setText(list.getVideoName());
        date.setText(list.getVideoDate());
        size.setText(list.getVideoSize()+" MB");
        path.setText(list.getVideoPath());

          bottomSheetDialog.show();
        close_btn.setOnClickListener(v->{
            bottomSheetDialog.dismiss();
        });
    }
    /*TODO for option menu*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mdelete) {

            getImageDelete(mvideoposition);
            videosModelArrayList.remove(mvideoposition);

            madapter.notifyDataSetChanged();

        }

        else if (item.getItemId() == R.id.send)
        {
            imagesShare(mvideoposition);

        }
        else if (item.getItemId() == R.id.mdetails)
        {
            showBottomSheetDialog();
        }
        return true;
    }

    /*TODO for deletion and share image and video*/
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

    /*TODO sharing files for images*/
    public void imagesShare(int position) {
        List<Uri> uriList=new ArrayList<Uri>();
        String uri = videosModelArrayList.get(position).getVideoPath();
        Log.d("gdrter",videosModelArrayList.get(position).getVideoPath());
        File file = new File(uri);

        long mediaID=getFilePathToMediaID(file.getAbsolutePath(),  this);

        Uri Uri_one = ContentUris.withAppendedId( MediaStore.Video.Media.getContentUri("external"),mediaID);
        uriList.add(Uri_one);
        shareFiles((ArrayList<Uri>) uriList);
        Log.d("myuri", Arrays.toString(new List[]{uriList}));
    }

    /*TODO Share Common Files*/
    private void shareFiles(ArrayList<Uri> uriList){
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("image/jpeg/video");
        share.putExtra(Intent.EXTRA_STREAM,uriList);
        startActivity(Intent.createChooser(share, "Share Files"));

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
    /*TODO for details */
    private void getImageDetails() {

    }

    /*TODO for delete*/
    private void getImageDelete(int position) {
        List<Uri> uriList=new ArrayList<Uri>();
        String uri = videosModelArrayList.get(position).getVideoPath();
        Log.d("gdrter",videosModelArrayList.get(position).getVideoPath());
        File file = new File(uri);
        long mediaID=getFilePathToMediaID(file.getAbsolutePath(),  this);

        Uri Uri_one = ContentUris.withAppendedId( MediaStore.Video.Media.getContentUri("external"),mediaID);
        uriList.add(Uri_one);

        Log.d("myuri",Arrays.toString(new List[]{uriList}));

        requestDeletePermission(uriList);
        vb.vPager.setCurrentItem(++mvideoposition,false);

        madapter.notifyDataSetChanged();
    }
}