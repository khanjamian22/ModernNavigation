package com.techakram.Online_Shop;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class ProductImageAdapter extends PagerAdapter
{
     private List<String> productImageList;

    public ProductImageAdapter(List<String> productImageList) {
        this.productImageList = productImageList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView productImageView=new ImageView(container.getContext());
        //productImageView.setImageResource(productImageList.get(position));
        Glide.with(container.getContext()).load(productImageList.get(position))
                .apply(new RequestOptions().placeholder(R.drawable.mobile_icons))
                .into(productImageView);

        container.addView(productImageView);
        return productImageView;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView)object);
    }

    @Override
    public int getCount() {
        return productImageList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
}
