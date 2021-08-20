package com.techakram.Online_Shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecificationFragment extends Fragment {
    private RecyclerView productspecificationRecyclerview;
    public ProductSpecificationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_product_specification, container, false);
        productspecificationRecyclerview=view.findViewById(R.id.product_specification_recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        productspecificationRecyclerview.setLayoutManager(linearLayoutManager);
        List<ProductSpecificationModel> productSpecificationModelList=new ArrayList<>();
        productSpecificationModelList.add(new ProductSpecificationModel("RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM","4GB"));
        ProductSpecificationAdapter productSpecificationAdapter=new ProductSpecificationAdapter(productSpecificationModelList);
        productspecificationRecyclerview.setAdapter(productSpecificationAdapter);
        productSpecificationAdapter.notifyDataSetChanged();
        return view;
    }
}