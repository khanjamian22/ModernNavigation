package com.lynhill.wingallery.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.lynhill.wingallery.databinding.FragmentImageViewBinding;
import com.lynhill.wingallery.model.ShowVideosModel;
import com.lynhill.wingallery.utils.imageIndicatorListener;

import java.util.ArrayList;


public class ImageViewFragment extends Fragment implements imageIndicatorListener {
    FragmentImageViewBinding vb;
    private static final String ARG_COUNT = "ARG_COUNT";
    private Integer position;
    SimpleExoPlayer simpleExoPlayer;
    public static ArrayList<ShowVideosModel> showVideosModels=new ArrayList<>();
    public ImageViewFragment() {
        // Required empty public constructor
    }

    /*TODO create instance of fragment */
    public static Fragment newInstance(int counter, ArrayList<ShowVideosModel> url) {
        ImageViewFragment viewFragment = new ImageViewFragment();
        Bundle args = new Bundle();
        Log.e("counter", "newInstance: "+counter );
        args.putInt(ARG_COUNT, counter);
        viewFragment.setArguments(args);
        showVideosModels = url;
        return viewFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vb= FragmentImageViewBinding.inflate(inflater,container,false);

        return vb.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    /*TODO click image*/



    /*TODO for set video  to video view */
    public void setVideo(String videoUrl) {
        Uri uri = Uri.parse(videoUrl.toString());
        simpleExoPlayer = new SimpleExoPlayer.Builder(getContext()).build();
        simpleExoPlayer.addMediaItem(MediaItem.fromUri(uri));
        vb.exoPlayerVIew.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
        //vb.exoPlayerVIew.setUseController(false);
       // vb.exoPlayerVIew.hideController();
        simpleExoPlayer.prepare();
        simpleExoPlayer.setPlayWhenReady(true);
        vb.exoPlayerVIew.setPlayer(simpleExoPlayer);
        vb.exoPlayerVIew.setVisibility(View.VISIBLE);

    }
    @Override
    public void onResume() {
        super.onResume();
        Log.e("simpleExo", "onResume: "+simpleExoPlayer );
        if(simpleExoPlayer!=null)
        {
            Log.d("lc","resume"+simpleExoPlayer);
            releasePlayer();
            simpleExoPlayer=null;
        }

        if (getArguments() != null) {
            position = getArguments().getInt(ARG_COUNT);
            Log.e("coounterdata", "onCreateView: "+ position);
            ShowVideosModel sliderItems = showVideosModels.get(position);
            String url = sliderItems.getVideoPath();
            Log.d("ghfhghjgh", url.toString());
            setVideo(url);
            /*TODO check for video and image url*/

        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(simpleExoPlayer!=null)
        {
            Log.d("lc","resume"+simpleExoPlayer);
            releasePlayer();
            simpleExoPlayer=null;
        }
    }
    public void releasePlayer() {
        if(simpleExoPlayer!=null) {
            simpleExoPlayer.stop();
            simpleExoPlayer.setPlayWhenReady(false);
            simpleExoPlayer.release();
        }
    }

    @Override
    public void onImageIndicatorClicked(int ImagePosition) {

    }
}