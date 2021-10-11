package com.lynhill.kioskxyz.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import com.lynhill.kioskxyz.adapter.ViewPagerAdapter;
import com.lynhill.kioskxyz.callback.BaseCallBack;
import com.lynhill.kioskxyz.databinding.ActivityHomeBinding;
import com.lynhill.kioskxyz.model.UrlResponseModel;
import com.lynhill.kioskxyz.model.UrlSuccessResponse;
import com.lynhill.kioskxyz.networking.Api;


import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity {
    /*TODO for viewBinding*/
    ActivityHomeBinding vb;

    private List<UrlSuccessResponse> urlSuccessResponse=new ArrayList<>();
    private Handler sliderHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        vb = ActivityHomeBinding.inflate(getLayoutInflater());

        setContentView(vb.getRoot());
        /*if(!Paper.book().contains("Username"))
         {
          Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
         }*/

        //or

        if (!Paper.book().contains("isLogin")) {

            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);

        } else {

            callGetUrlApi();

        }



        /*TODO AUTO SLIDER And View to Viewpager2*/
        vb.vPager.setClipToPadding(false);
        vb.vPager.setClipChildren(false);
        vb.vPager.setOffscreenPageLimit(3);
        vb.vPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        vb.vPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 15000); // slide duration 15 seconds
            }
        });
    }
    /*TODO set adapter*/
    private ViewPagerAdapter createCardAdapter( )
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, urlSuccessResponse);
        Log.d("mylisturl",urlSuccessResponse.toString());
        return adapter;
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            vb.vPager.setCurrentItem( vb.vPager.getCurrentItem() + 1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 15000);//  for 15 seconds
    }


    @Override
    public void onBackPressed() {

        // super.onBackPressed(); //for disable back button
    }
    /*TODO set data */
    public void callGetUrlApi()
    {

         //urlSuccessResponse=new ArrayList<UrlSuccessResponse>();
        Log.d("callapi","callling");
      new Api(this).getMediaUrls(new BaseCallBack() {
          @Override
          public void onResponse(Object response) {
              Log.d("ffff",response.toString());

              UrlResponseModel urlResponseModel=(UrlResponseModel) response;

              urlSuccessResponse=urlResponseModel.getSuccess();
              Log.d("mylist",urlSuccessResponse.toString());
              Log.d("rrrrrrr",urlResponseModel.getSuccess().toString());
              vb.vPager.setOrientation(vb.vPager.ORIENTATION_HORIZONTAL);
              vb.vPager.setAdapter(createCardAdapter());
          }

          @Override
          public void onError(String error) {
           Log.d("ggggg",error.toString());
          }
      });
       /* Url=new ArrayList<SliderItems>();
        Url.add(new SliderItems("https://www.guinnessworldrecords.com/Images/Michael-Jordan-main_tcm25-15662.jpg"));
        Url.add(new SliderItems("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4"));
        Url.add(new SliderItems("https://sample-videos.com/video123/mp4/720/big_buck_bunny_720p_20mb.mp4"));
        Url.add(new SliderItems("https://www.rmp-streaming.com/media/big-buck-bunny-360p.mp4"));
        Url.add(new SliderItems("https://www.guinnessworldrecords.com/Images/Michael-Jordan-main_tcm25-15662.jpg"));
        Url.add(new SliderItems("https://thelegacyproject.co.za/wp-content/uploads/2015/04/Michael_Jordan_Net_Worth.jpg"));
        Url.add(new SliderItems("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4"));
        Url.add(new SliderItems("https://sportsmockery.com/wp-content/uploads/2015/03/michael-jordan-1600x1200.jpg"));
        Url.add(new SliderItems("https://cdn.videvo.net/videvo_files/video/free/2017-08/large_watermarked/170724_15_Setangibeach_preview.mp4"));
         */
    }

}
