package com.lynhill.kioskxyz.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.lynhill.kioskxyz.fragment.ViewFragment;
import com.lynhill.kioskxyz.model.UrlSuccessResponse;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public static int LOOPS_COUNT = 1000;
    FragmentActivity fragmentActivity;
    List<UrlSuccessResponse> sliderItems;
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<UrlSuccessResponse> sliderItems) {
        super(fragmentActivity);
        this.fragmentActivity = fragmentActivity;
        this.sliderItems = sliderItems;
    }


    /*TODO Fragments */
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        /*TODO for infinite slider*/
        if (sliderItems != null && sliderItems.size() > 0) {
            position = position % sliderItems.size(); // use modulo for infinite cycling
            return ViewFragment.newInstance(position, sliderItems);//use to attach fragment in adapter
        } else {

            return ViewFragment.newInstance(position, sliderItems);
        }
        // return ViewFragment.newInstance(position,sliderItems);
    }

    @Override
    public int getItemCount() {
        //return sliderItems.size();
        if (sliderItems != null && sliderItems.size() > 0) {
            return sliderItems.size() * LOOPS_COUNT; // simulate infinite by big number of products
        } else {
            return 1;
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            sliderItems.addAll(sliderItems);
            notifyDataSetChanged();
        }
    };
}
