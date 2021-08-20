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

public class CatagoryAdapter extends RecyclerView.Adapter<CatagoryAdapter.ViewHolder> {
    private List<CatagoryModel> catagoryModelList;

    public CatagoryAdapter(List<CatagoryModel> catagoryModelList) {
        this.catagoryModelList = catagoryModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.catagory,viewGroup,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String icon=catagoryModelList.get(position).getCatagoryIconLink();
        String name=catagoryModelList.get(position).getCatagoryName();
        holder.setcatagoryIcon(icon);
        holder.setCatagory(name);
    }

    @Override
    public int getItemCount() {
        return catagoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView catagoryIcon;
        private TextView catagoryName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            catagoryIcon=itemView.findViewById(R.id.catagory_icon);
            catagoryName=itemView.findViewById(R.id.catagory_name);
        }
        private void setcatagoryIcon(String iconUrl) {
            if (!iconUrl.equals("null"))
            {
                Glide.with(itemView.getContext( )).load(iconUrl).apply(new RequestOptions( ).placeholder(R.drawable.mobile_icons)).into(catagoryIcon);
            }
        }
        private void setCatagory(String name)
        {
            catagoryName.setText(name);
            itemView.setOnClickListener(new View.OnClickListener( ) {
                @Override
                public void onClick(View view) {
                    Intent catagoryIntent=new Intent(itemView.getContext(),CatagoryActivity.class);
                    catagoryIntent.putExtra("catagoryName",name);
                    itemView.getContext().startActivity(catagoryIntent);
                }
            });
        }
    }

}
