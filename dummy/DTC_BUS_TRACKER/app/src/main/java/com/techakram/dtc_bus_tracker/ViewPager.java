package com.techakram.dtc_bus_tracker;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Locale;
public class ViewPager extends FragmentPagerAdapter {
    private final ArrayList<Fragment> fragmentsList=new ArrayList<>();
    private final ArrayList<String> fragmentTitle=new ArrayList<String>();
    public ViewPager(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new MainActivity.DummySectionFragment();
        Bundle args = new Bundle();
        args.putInt(MainActivity.DummySectionFragment.ARG_SECTION_NUMBER, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }
    public  void addFragments(Fragment fragment,String title)
    {
        fragmentsList.add(fragment);
        fragmentTitle.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Locale l= Locale.getDefault();
        switch (position)
        {

        }

        return fragmentTitle.get(position);
    }
}

