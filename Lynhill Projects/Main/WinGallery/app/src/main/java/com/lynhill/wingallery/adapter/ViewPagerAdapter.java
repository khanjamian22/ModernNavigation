package com.lynhill.wingallery.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.lynhill.wingallery.fragment.Photos_folders_Fragment;
import com.lynhill.wingallery.fragment.Videos_Folders_Fragment;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    public ViewPagerAdapter(Context context,FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Photos_folders_Fragment photos_folders_fragment = new Photos_folders_Fragment();
                return photos_folders_fragment;
            case 1:
                Videos_Folders_Fragment videos_folders_fragment= new Videos_Folders_Fragment();
                return videos_folders_fragment;

            default:
                return null;
        }
    }
    // below method is to set the title for tab layout item.

    @Override
    public int getCount() {

        return 2;
    }
}
