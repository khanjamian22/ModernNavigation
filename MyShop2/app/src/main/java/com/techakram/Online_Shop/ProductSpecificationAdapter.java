package com.techakram.Online_Shop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductSpecificationAdapter extends RecyclerView.Adapter<ProductSpecificationAdapter.ViewHolder> {
    private List<ProductSpecificationModel> productSpecificationModelList;

    public ProductSpecificationAdapter(List<ProductSpecificationModel> productSpecificationModelList) {
        this.productSpecificationModelList = productSpecificationModelList;
    }

    @NonNull
    @Override
    public ProductSpecificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_specification_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductSpecificationAdapter.ViewHolder holder, int position) {
      String fNmae=productSpecificationModelList.get(position).getFeature_name();
      String fValue=productSpecificationModelList.get(position).getFeature_value();
      holder.setFeature(fNmae,fValue);
    }

    @Override
    public int getItemCount() {
        return productSpecificationModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView feature_name,feature_value;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            feature_name=itemView.findViewById(R.id.tv_feature_name);
            feature_value=itemView.findViewById(R.id.tv_feature_value);
        }
        public void setFeature(String f_name,String f_value)
        {
            feature_name.setText(f_name);
            feature_value.setText(f_value);
        }
    }
}
