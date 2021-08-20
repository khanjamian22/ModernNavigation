package com.techakram.Online_Shop;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class GridProductLayoutAdapter extends BaseAdapter {
    List<Horizontal_product_scroll_Model> horizontal_product_scroll_modelList;

    public GridProductLayoutAdapter(List<Horizontal_product_scroll_Model> horizontal_product_scroll_modelList) {
        this.horizontal_product_scroll_modelList = horizontal_product_scroll_modelList;
    }

    @Override
    public int getCount() {
        return horizontal_product_scroll_modelList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view1;
        if(convertView==null)
        {
           view1= LayoutInflater.from(parent.getContext())
                   .inflate(R.layout.horizontal_scroll_item_layout,null);
           view1.setElevation(0);
           view1.setBackgroundColor(Color.parseColor("#ffffff"));
           //// for rating page...
            view1.setOnClickListener(new View.OnClickListener( ) {
                @Override
                public void onClick(View view) {
                    Intent product_details_intent=new Intent(parent.getContext(),ProductDetailsActivity.class);
                    parent.getContext().startActivity(product_details_intent);
                }
            });

            ///for rating page....
            ImageView productImage=view1.findViewById(R.id.Hs_product_image);
            TextView productTitle=view1.findViewById(R.id.Hs_product_title);
            TextView productDiscription=view1.findViewById(R.id.Hs_product_discription);
            TextView productPrice=view1.findViewById(R.id.Hs_product_price);
            //productImage.setImageResource(horizontal_product_scroll_modelList.get(position).getProductImage());
            Glide.with(convertView.getContext()).load(horizontal_product_scroll_modelList.get(position)
                    .getProductImage()).apply(new RequestOptions().placeholder(R.drawable.mobile_icons)).into(productImage);
            productTitle.setText(horizontal_product_scroll_modelList.get(position).getProductTitle());
            productDiscription.setText(horizontal_product_scroll_modelList.get(position).getProductDiscription());
            productPrice.setText(horizontal_product_scroll_modelList.get(position).getGetProductPrice());

        }
        else {

             view1=convertView;
        }

        return view1;
    }
}
