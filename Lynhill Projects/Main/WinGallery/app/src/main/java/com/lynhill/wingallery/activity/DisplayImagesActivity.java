package com.lynhill.wingallery.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lynhill.wingallery.R;
import com.lynhill.wingallery.adapter.ShowImagesAdapter;
import com.lynhill.wingallery.databinding.ActivityDisplayImagesBinding;
import com.lynhill.wingallery.fragment.ImageViewFragment;
import com.lynhill.wingallery.model.ShowImagesModel;
import com.lynhill.wingallery.utils.ItemListenToClick;
import com.lynhill.wingallery.utils.MyPicViewHolder;

import java.util.ArrayList;

public class DisplayImagesActivity extends AppCompatActivity implements ItemListenToClick {
      ActivityDisplayImagesBinding dvb;
      ArrayList<ShowImagesModel> showImagesModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dvb=ActivityDisplayImagesBinding.inflate(getLayoutInflater());
        setContentView(dvb.getRoot());
        showImagesModelArrayList=new ArrayList<>();
        dvb.backbtn.setOnClickListener(v->{
            startActivity(new Intent(this,ShowFoldersActivity.class));
        });
        /*TODO set recycler*/
        setRecyclerview();

    }

    private void setRecyclerview() {
        if(showImagesModelArrayList.isEmpty())
        {
            // set a GridLayoutManager with 3 number of columns
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),4);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
            dvb.dispimgsRecycler.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
            dvb .dispimgsRecycler.hasFixedSize();
            dvb.prgsLoader.setVisibility(View.VISIBLE);
            String folderPath=getIntent().getStringExtra("folderPath");
            String folderName=getIntent().getStringExtra("folderName");
            dvb.foldrName.setText(folderName);
            showImagesModelArrayList=getAllImagesByfolder(folderPath);
            Log.d("mylist",showImagesModelArrayList.toString());
            dvb.dispimgsRecycler.setAdapter(new ShowImagesAdapter(showImagesModelArrayList,this,this));
            Log.d("fsffsvdfdt",showImagesModelArrayList.toString());
            dvb.prgsLoader.setVisibility(View.GONE);
        }
        else
        {
            Toast.makeText(this, "list", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPicClicked(MyPicViewHolder holder, int position, ArrayList<ShowImagesModel> pics) {
       ImageViewFragment imageViewFragment=ImageViewFragment.newInstance(pics,position,this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //browser.setEnterTransition(new Slide());
            //browser.setExitTransition(new Slide()); uncomment this to use slide transition and comment the two lines below
            imageViewFragment.setEnterTransition(new Fade());
            imageViewFragment.setExitTransition(new Fade());
        }

        getSupportFragmentManager()
                .beginTransaction()
                .addSharedElement(holder.phvb.image, position+"picture")
                .add(R.id.displayContainer, imageViewFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onPicClicked(String pictureFolderPath, String folderName) {

    }
    /*TODO get all images byfolder in arrayList*/
    public  ArrayList<ShowImagesModel> getAllImagesByfolder(String path){
        ArrayList<ShowImagesModel> images=new ArrayList<>();
        Uri allImagesUri= MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection={MediaStore.Images.ImageColumns.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                 MediaStore.Images.Media.SIZE};
        Cursor cursor=this.getContentResolver().query(allImagesUri,projection,MediaStore.Images.Media.DATA + " like ? ", new String[] {"%"+path+"%"},null);
        try {
            if(cursor!=null)
            {
                cursor.moveToFirst();
            }
            do {
                ShowImagesModel picture=new ShowImagesModel();
                picture.setPicturName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)));
                picture.setPicturePath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)));
                picture.setPictureSize(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)));
                //all all attribute in array
                images.add(picture);
            }
            while (cursor.moveToNext());
                   cursor.close();
            /*TODO for reselection*/
            ArrayList<ShowImagesModel> reselect=new ArrayList<>();
            for (int i=images.size()-1; i>-1; i--){
                reselect.add(images.get(i));

            }
            images=reselect;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return images;
    }
}