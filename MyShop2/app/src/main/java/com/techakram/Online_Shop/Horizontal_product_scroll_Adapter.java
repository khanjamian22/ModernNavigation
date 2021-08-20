package com.techakram.Online_Shop;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class Horizontal_product_scroll_Adapter extends RecyclerView.Adapter<Horizontal_product_scroll_Adapter.ViewHolder>
{
      private List<Horizontal_product_scroll_Model> horizontal_product_scroll_modelList;

    public Horizontal_product_scroll_Adapter(List<Horizontal_product_scroll_Model> horizontal_product_scroll_modelList) {
        this.horizontal_product_scroll_modelList = horizontal_product_scroll_modelList;
    }

    @NonNull
    @Override
    public Horizontal_product_scroll_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Horizontal_product_scroll_Adapter.ViewHolder holder, int position) {
      String resource=horizontal_product_scroll_modelList.get(position).getProductImage();
      String title=horizontal_product_scroll_modelList.get(position).getProductTitle();
      String discription=horizontal_product_scroll_modelList.get(position).getProductDiscription();
      String price=horizontal_product_scroll_modelList.get(position).getGetProductPrice();
      holder.setProductImage(resource);
      holder.setProductTitle(title);
      holder.setProductDiscription(discription);
      holder.setProductPrice(price);
    }

    @Override
    public int getItemCount() {
        if (horizontal_product_scroll_modelList.size() > 8)
        {
            return 8;
        }
        else {
            return horizontal_product_scroll_modelList.size();
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView titleTextView,discriptionTextView,priceTextview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.Hs_product_image);
            titleTextView=itemView.findViewById(R.id.Hs_product_title);
            discriptionTextView=itemView.findViewById(R.id.Hs_product_discription);
            priceTextview=itemView.findViewById(R.id.Hs_product_price);
            itemView.setOnClickListener(new View.OnClickListener( ) {
                @Override
                public void onClick(View view) {
                    Intent productDetailsIntent=new Intent(itemView.getContext(),ProductDetailsActivity.class);
                    itemView.getContext().startActivity(productDetailsIntent);
                }
            });

        }
        private void setProductImage(String resource)
        {
//          imageView.setImageResource(Integer.parseInt(resource));
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions())
                    .placeholder(R.drawable.mobile_icons).into(imageView);
        }
        private void setProductTitle( String title)
        {
            titleTextView.setText(title);
        }
        private void setProductDiscription( String discription)
        {
            discriptionTextView.setText(discription);
        }
        private void setProductPrice( String price)
        {
            priceTextview.setText("Rs."+price+"-/");
        }

    }
}
