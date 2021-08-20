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

public class MyOrderFragment extends Fragment {
     private RecyclerView myOrdersRecyclerView;
    public MyOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_my_order, container, false);
        myOrdersRecyclerView=view.findViewById(R.id.my_order_fragmentRecyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myOrdersRecyclerView.setLayoutManager(linearLayoutManager);
        List<MyOrderModel> myOrderModelList=new ArrayList<>();
        myOrderModelList.add(new MyOrderModel(R.drawable.mobile_icons,2,"Google 28 pixel 3d MX","Delivery on Mon 15th Aug 2021"));
        myOrderModelList.add(new MyOrderModel(R.drawable.mobile_icons,1,"Google 20 pixel 7d MX","Delivery on Mon 16th Aug 2021"));
        myOrderModelList.add(new MyOrderModel(R.drawable.mobile_icons,0,"Google 12 pixel 8d MX","cancelled"));
        myOrderModelList.add(new MyOrderModel(R.drawable.mobile_icons,4,"Google 21 pixel 5d MX","Delivery on Mon 18th Aug 2021"));
        MyOrderAdapter myOrderAdapter=new MyOrderAdapter(myOrderModelList);
        myOrdersRecyclerView.setAdapter(myOrderAdapter);
        myOrderAdapter.notifyDataSetChanged();
        return view;
    }
}