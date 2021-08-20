package com.techakram.Online_Shop;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolder> {
    private List<WishListModel> wishListModelList;
    private Boolean wishlist;
    public WishListAdapter(List<WishListModel> wishListModelList,Boolean wishlist) {
        this.wishListModelList = wishListModelList;
        this.wishlist=wishlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       String resource=wishListModelList.get(position).getProduct_image();
       long free_coupen=wishListModelList.get(position).getFree_coupen();
       long total_rating=wishListModelList.get(position).getTotal_rating();
       String product_title=wishListModelList.get(position).getProduct_title();
       String product_price=wishListModelList.get(position).getProduct_price();
       String cutPrice=wishListModelList.get(position).getCutted_price();
       String rating=wishListModelList.get(position).getRating();
       boolean paymethod=wishListModelList.get(position).isCOD();
       holder.setData(resource,free_coupen,total_rating,product_title,rating,product_price,cutPrice,paymethod);
    }

    @Override
    public int getItemCount() {
        return wishListModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage,coupenIcon;
        private ImageView deleteBtn;
        private View priceCut;
        private TextView productTitle,freecoupen,rating,totalRating,productPrice,cuttedPrice,paymentMethod;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.product_imageView);
            coupenIcon=itemView.findViewById(R.id.coupen_imageView);
            deleteBtn=itemView.findViewById(R.id.deleteBtn_imageView);
            priceCut=itemView.findViewById(R.id.divider);
            productTitle=itemView.findViewById(R.id.title_textView);
            freecoupen=itemView.findViewById(R.id.coupen_textView);
            rating=itemView.findViewById(R.id.tv_product_rating_minview);
            totalRating=itemView.findViewById(R.id.total_rating_textView);
            productPrice=itemView.findViewById(R.id.textViewPrice);
            cuttedPrice=itemView.findViewById(R.id.textViewPriceCut);
            paymentMethod=itemView.findViewById(R.id.payment_method_textView);
        }
        private void setData(String resource, long freeCoupenNo, long totalRatingNo, String title, String averageRate, String price, String cutprice, boolean COD)
        {
             // productImage.setImageResource(resource);
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.mobile_icons))
                    .into(productImage);
              productTitle.setText(title);

            if(freeCoupenNo!=0) {
                coupenIcon.setVisibility(View.VISIBLE);

                if (freeCoupenNo == 1)
                {
                    freecoupen.setText("free" + freeCoupenNo + "coupen");
                } else
                    {
                    freecoupen.setText("free" + freeCoupenNo + "coupens");
                    }
            }
            else
                {
                    coupenIcon.setVisibility(View.INVISIBLE);
                    freecoupen.setVisibility(View.INVISIBLE);
                }
            rating.setText(averageRate);
            totalRating.setText(totalRatingNo+"(ratings)");
            productPrice.setText(price);
            cuttedPrice.setText(cutprice);
            //paymentMethod.setText((CharSequence) paymentMethod);
            if(COD)
            {
             paymentMethod.setVisibility(View.VISIBLE);
            }
            else
            {
                paymentMethod.setVisibility(View.INVISIBLE);
            }
            //for checking button kis activity pe jayga...
            if(wishlist)
            {
              deleteBtn.setVisibility(View.VISIBLE);
            }
            else {
                deleteBtn.setVisibility(View.INVISIBLE);
            }
            deleteBtn.setOnClickListener(new View.OnClickListener( ) {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "deleted", Toast.LENGTH_SHORT).show( );
                }
            });
              itemView.setOnClickListener(new View.OnClickListener( ) {
                  @Override
                  public void onClick(View v) {
                      Intent productDetailsIntent=new Intent(itemView.getContext(),ProductDetailsActivity.class);
                      itemView.getContext().startActivity(productDetailsIntent);

                  }
              });
            }
        }

}
