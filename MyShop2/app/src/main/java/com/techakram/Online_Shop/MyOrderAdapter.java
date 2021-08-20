package com.techakram.Online_Shop;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {
     LinearLayout ratNow_containerLayout;
     List<MyOrderModel> myOrderModelList;

    public MyOrderAdapter(List<MyOrderModel> myOrderModelList) {
        this.myOrderModelList = myOrderModelList;
    }

    @NonNull
    @Override
    public MyOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_orders_item_layout,parent,false);
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderAdapter.ViewHolder holder, int position) {
       int resource=myOrderModelList.get(position).getProductImage();
       int rating=myOrderModelList.get(position).getRating();
       String title=myOrderModelList.get(position).getProductTitle();
       String status=myOrderModelList.get(position).getProductStatus();
       holder.setOrderdetails(resource,title,status,rating);
    }

    @Override
    public int getItemCount() {
        return myOrderModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView orderImage,deliveryIndicator;
        TextView orderTitle,orderStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ratNow_containerLayout=itemView.findViewById(R.id.rate_now_linearLayout);
            orderImage=itemView.findViewById(R.id.order_imageView);
            deliveryIndicator=itemView.findViewById(R.id.order_indicator);
            orderTitle=itemView.findViewById(R.id.textView_order_title);
            orderStatus=itemView.findViewById(R.id.textView_order_deliverd_date);
            itemView.setOnClickListener(new View.OnClickListener( ) {
                @Override
                public void onClick(View v) {
                    Intent orderdetailIntent=new Intent(itemView.getContext(),Order_Detail_Activity.class);
                    itemView.getContext().startActivity(orderdetailIntent);
                }
            });
        }
        public void setOrderdetails(int resource,String title,String deliveryDate,int rating)
        {
            orderImage.setImageResource(resource);
            orderTitle.setText(title);
            if(deliveryDate.equals("cancelled"))
            {
               deliveryIndicator.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.colorPrimarydark)));
            }
            else
                {
                    deliveryIndicator.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.green)));

                }
            orderStatus.setText(deliveryDate);
            /////////////////////ratinglayout.............
                   setRating(rating);
            for (int x=0; x<ratNow_containerLayout.getChildCount(); x++)
            {
                final int starPosition=x;
                ratNow_containerLayout.getChildAt(x).setOnClickListener(new View.OnClickListener( ) {
                    @Override
                    public void onClick(View view) {
                        setRating(starPosition);
                    }
                });
            }
            /////////////////////ratinglayout.............
        }
        private void setRating(int starPosition) {
            for (int x=0; x<ratNow_containerLayout.getChildCount(); x++) {
                ImageView starImageBtn = (ImageView) ratNow_containerLayout.getChildAt(x);
                starImageBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));

                if (x <= starPosition)
                {
                    starImageBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
                }
            }
        }


    }
}
