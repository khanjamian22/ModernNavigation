package com.techakram.Online_Shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyCartFragment extends Fragment {
private RecyclerView cartItemRecyclerView;
private Button countinueBtn;

    public MyCartFragment() {
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
       View view= inflater.inflate(R.layout.fragment_my_cart, container, false);
       cartItemRecyclerView=view.findViewById(R.id.cart_item_recyclerView);
       countinueBtn=view.findViewById(R.id.cart_continue_btn);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cartItemRecyclerView.setLayoutManager(linearLayoutManager);
        List<CartItemModel> cartItemModelList=new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0,R.drawable.mobile_icons,"Google Pixel 2",2,"5999/-","69999/-",6,7,8));
        cartItemModelList.add(new CartItemModel(0,R.drawable.mobile_icons,"Google Pixel 2",2,"5999/-","69999/-",6,7,8));
        cartItemModelList.add(new CartItemModel(0,R.drawable.mobile_icons,"Google Pixel 2",2,"5999/-","69999/-",6,7,8));
        cartItemModelList.add(new CartItemModel(0,R.drawable.mobile_icons,"Google Pixel 2",2,"4999/-","89999/-",6,7,8));

        /////////////total amount wala.................
        cartItemModelList.add(new CartItemModel(1,"Price (4 items)","Rs.30000/-","free","Rs.28994-/","Rs.3400"));

        CartItemAdapter cartItemAdapter=new CartItemAdapter(cartItemModelList);
        cartItemRecyclerView.setAdapter(cartItemAdapter);
        cartItemAdapter.notifyDataSetChanged();
        countinueBtn.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                Intent deliveryIntent=new Intent(view.getContext(),AddAddressActivity.class);
                view.getContext().startActivity(deliveryIntent);

            }
        });
        return view;
    }
}