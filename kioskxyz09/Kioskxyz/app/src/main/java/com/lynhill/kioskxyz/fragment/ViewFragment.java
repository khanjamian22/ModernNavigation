package com.lynhill.kioskxyz.fragment;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.lynhill.kioskxyz.databinding.FragmentViewBinding;
import com.lynhill.kioskxyz.model.UrlSuccessResponse;

import java.util.ArrayList;
import java.util.List;

public class ViewFragment extends Fragment {
    /*TODO viewBinding*/
    FragmentViewBinding vb;

    private static final String ARG_COUNT = "ARG_COUNT";
    private Integer position;
    SimpleExoPlayer simpleExoPlayer;
    public static List<UrlSuccessResponse> Url;
    public ViewFragment() {

    }
     /*TODO create instance of fragment */
    public static Fragment newInstance(int counter, List<UrlSuccessResponse> url) {
        ViewFragment viewFragment = new ViewFragment();
        Bundle args = new Bundle();
        Log.e("counter", "newInstance: "+counter );
        args.putInt(ARG_COUNT, counter);
        viewFragment.setArguments(args);
         Url = url;
        return viewFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vb = FragmentViewBinding.inflate(inflater, container, false);

        Log.e("simpleExo", "onCreateView: "+simpleExoPlayer );
        return vb.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public ImageView getImage() {
        return this.vb.imageView;
    }
  /*TODO for set image to image view */
    public void setImage(String img_url) {
        Glide.with(getContext())
                .load(img_url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(getImage());
        vb.imageView.setVisibility(View.VISIBLE);
        vb.exoPlayerVIew.setVisibility(View.GONE);
    }

    /*TODO for set video  to video view */
    public void setVideo(String videoUrl) {
        Uri uri = Uri.parse(videoUrl.toString());
        simpleExoPlayer = new SimpleExoPlayer.Builder(getContext()).build();
        simpleExoPlayer.addMediaItem(MediaItem.fromUri(uri));
        vb.exoPlayerVIew.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
        vb.exoPlayerVIew.setUseController(false);
        vb.exoPlayerVIew.hideController();
        simpleExoPlayer.prepare();
        simpleExoPlayer.setPlayWhenReady(true);
        vb.exoPlayerVIew.setPlayer(simpleExoPlayer);
        vb.exoPlayerVIew.setVisibility(View.VISIBLE);
        vb.imageView.setVisibility(View.GONE);
    }

/*TODO differentiate for image and video url via split of url*/
    private boolean isImage(String url) {
        String[] splitedArray = url.split("\\.");
        String lastValueOfArray = splitedArray[splitedArray.length - 1];
        if (lastValueOfArray.equals("jpg") || lastValueOfArray.equals("png") || lastValueOfArray.equals("gif")) {
            Log.d("hfhjh", url.toString());
            return true;
        } else {
            Log.d("hfhjhghjhh", url.toString());
            return false;
        }
    }

    /*TODO control background via Exoplayer controller*/
//    public void startPlayer() {
//        simpleExoPlayer.setPlayWhenReady(true);
//        simpleExoPlayer.getPlaybackState();
//    }

//    public void stopPlayer(){
//       if(simpleExoPlayer!=null) {
//           simpleExoPlayer.setPlayWhenReady(false);
//           simpleExoPlayer.stop();
//          // simpleExoPlayer.setPlayPause(false);
//           simpleExoPlayer.release();
//
//       }
//    }
//    public void pausePlayer() {
//        simpleExoPlayer.setPlayWhenReady(false);
//        simpleExoPlayer.getPlaybackState();
//    }
    public void releasePlayer() {
        if(simpleExoPlayer!=null) {
            simpleExoPlayer.stop();
            simpleExoPlayer.setPlayWhenReady(false);
            simpleExoPlayer.release();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(simpleExoPlayer!=null)
        {
            Log.d("lc","pause"+simpleExoPlayer);
            releasePlayer();
            simpleExoPlayer=null;

        }
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
            UrlSuccessResponse sliderItems = Url.get(position);
            String url = sliderItems.getUrl();
            Log.d("ghfhghjgh", url.toString());

            /*TODO check for video and image url*/
            if (isImage(url)) {
                setImage(url);

            } else {
                setVideo(url);
            }
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
}
