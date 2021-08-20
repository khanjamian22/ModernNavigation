package com.techakram.Online_Shop;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ProductDetailsAdapter extends FragmentPagerAdapter {
    private int totalTabs;
    public ProductDetailsAdapter(@NonNull FragmentManager fm,int totalTabs) {
        super(fm);
        this.totalTabs=totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0:
                ProductDiscriptionFragment productDiscriptionFragment=new ProductDiscriptionFragment();
                return productDiscriptionFragment;
            case 1:
               ProductSpecificationFragment productSpecificationFragment=new ProductSpecificationFragment();
               return productSpecificationFragment;
            case 2:
                ProductDiscriptionFragment productDiscriptionFragment1=new ProductDiscriptionFragment();
                return productDiscriptionFragment1;
        }
        return null;
    }

    @Override
    public int getCount() {
        return totalTabs ;
    }
}
