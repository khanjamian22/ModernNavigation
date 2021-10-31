package com.lynhill.wingallery.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

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
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.lynhill.wingallery.R;
import com.lynhill.wingallery.adapter.ShowImagesAdapter;
import com.lynhill.wingallery.adapter.SingleImageAdapter;
import com.lynhill.wingallery.databinding.ActivitySingleImageShowBinding;
import com.lynhill.wingallery.model.ShowImagesModel;
import com.lynhill.wingallery.utils.ItemListenToClick;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Show_SingleImage_Activity extends AppCompatActivity implements ItemListenToClick {
     ActivitySingleImageShowBinding svb;
    int REQUEST_PERM_DELETE=101;
    protected int current;
    protected String modelsize;
    SingleImageAdapter madapter;
    private Toolbar toolbar;
    String fName;
    String tab;
    BottomSheetDialog bottomSheetDialog;
    int mpicposition;
    ArrayList<ShowImagesModel> models=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        svb=ActivitySingleImageShowBinding.inflate(getLayoutInflater());

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
           // window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
           // window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
           // getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(svb.getRoot());
        toolbar = (Toolbar) findViewById(R.id.edit_toolBar);
        setSupportActionBar(toolbar);
        //toolbar.inflateMenu(R.menu.menu);
        mpicposition=getIntent().getIntExtra("picposition",1);
        fName=getIntent().getStringExtra("folder");
        svb.itemDate.setText(fName);
        svb.backbtn.setOnClickListener(v->{

            finish();
        });
        Log.d("ghdtg", String.valueOf(mpicposition));

        /*TODO getDetail for Images*/

        getImageDetails();

    }





    @Override
    public void onPicClicked(ShowImagesAdapter.MyPicViewHolder holder, int position, ArrayList<ShowImagesModel> pics,String tag) {

    }

    @Override
    public void onPicClicked(String pictureFolderPath, String folderName, String tag) {

    }

    @Override
    public void onImageClicked(int ImagePosition) {

    }

    @Override
    public void onVideoClicked(int ImagePosition,String tag) {

    }

    @Override
    protected void onResume() {
        super.onResume();

        models=getIntent().getParcelableArrayListExtra("list");
        tab=getIntent().getStringExtra("Tab");
        Log.d("mmnhjfd", String.valueOf(models.size()));
        modelsize=String.valueOf(models.size());
        Log.d("currentpifjjf", String.valueOf(mpicposition));
            madapter=new SingleImageAdapter(models,this,  this);
//        if (svb.viewPager.getAdapter() != null)
//            svb.viewPager.setAdapter(null)
            svb.viewPager.setAdapter(madapter);
            svb.viewPager.setCurrentItem(mpicposition,false);

/*TODO back button */
//        svb.backbtn.setOnClickListener(v->{
//            finish();
//        });
        /*TODO for counting the images when slide these images*/
        svb.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                current = position;
                Log.d("picpositoin", String.valueOf(current));
                //updatePercent();
            }
        });
    }

    /*TODO for change page no.*/
    protected void updatePercent() {
        svb.itemDate.setText((current + 1) + "/" + modelsize);
    }
    /*TODO for show toolbar*/
    /*TODO option menu*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mdelete) {

            getImageDelete(mpicposition);
            models.remove(mpicposition);
//              if (svb.viewPager.getAdapter() != null)
//             svb.viewPager.setAdapter(null);
//             svb.viewPager.setAdapter(madapter);
//             svb.viewPager.setCurrentItem(++mpicposition,false);
//
            madapter.notifyDataSetChanged();

        }

        else if (item.getItemId() == R.id.send)
        {
            imagesShare(mpicposition);

        }
        else if (item.getItemId() == R.id.mdetails)
        {
            showBottomSheetDialog();
        }
        return true;
    }

    /*TODO for dialog*/
    private void showBottomSheetDialog() {
             bottomSheetDialog = new BottomSheetDialog(this);
        // final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottomsheet_model);
        ImageView close_btn=bottomSheetDialog.findViewById(R.id.close_btn);
        TextView name=bottomSheetDialog.findViewById(R.id.img_name);
        TextView date=bottomSheetDialog.findViewById(R.id.img_date);

        TextView size=bottomSheetDialog.findViewById(R.id.img_size);

        TextView path=bottomSheetDialog.findViewById(R.id.img_path);

        ShowImagesModel list=models.get(mpicposition);
        name.setText(list.getPicturName());
        date.setText(list.getImageDate());
        size.setText(list.getPictureSize()+" KB");
        path.setText(list.getPicturePath());
        bottomSheetDialog.show();
        close_btn.setOnClickListener(v->{
            bottomSheetDialog.dismiss();
        });
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

    /*TODO sharing files for images*/
    public void imagesShare(int position) {
        List<Uri> uriList=new ArrayList<Uri>();
        String uri = models.get(position).getPicturePath();
        Log.d("gdrter",models.get(position).getPicturePath());
        File file = new File(uri);

        long mediaID=getFilePathToMediaID(file.getAbsolutePath(),  this);

        Uri Uri_one = ContentUris.withAppendedId( MediaStore.Images.Media.getContentUri("external"),mediaID);
        uriList.add(Uri_one);
        shareFiles((ArrayList<Uri>) uriList);
        Log.d("myuri",Arrays.toString(new List[]{uriList}));
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
        String uri = models.get(position).getPicturePath();
        Log.d("gdrter",models.get(position).getPicturePath());
        File file = new File(uri);
        long mediaID=getFilePathToMediaID(file.getAbsolutePath(),  this);

        Uri Uri_one = ContentUris.withAppendedId( MediaStore.Images.Media.getContentUri("external"),mediaID);
        uriList.add(Uri_one);

        Log.d("myuri",Arrays.toString(new List[]{uriList}));

        requestDeletePermission(uriList);
        svb.viewPager.setCurrentItem(++mpicposition,false);

        madapter.notifyDataSetChanged();
    }


}