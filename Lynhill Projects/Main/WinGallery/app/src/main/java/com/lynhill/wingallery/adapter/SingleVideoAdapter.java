package com.lynhill.wingallery.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.lynhill.wingallery.fragment.ImageViewFragment;
import com.lynhill.wingallery.model.ShowVideosModel;

import java.util.ArrayList;

public class SingleVideoAdapter extends FragmentStateAdapter {
    ArrayList<ShowVideosModel> videosModels;
    FragmentActivity fragmentActivity;
    public SingleVideoAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<ShowVideosModel> videosModels) {
        super(fragmentActivity);
        this.fragmentActivity=fragmentActivity;
        this.videosModels=videosModels;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return ImageViewFragment.newInstance(position,videosModels);
    }

    @Override
    public int getItemCount() {
        return videosModels.size();
    }
}
