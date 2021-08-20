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

public class MyWishList_Fragment extends Fragment {
    private RecyclerView wishlistRecyclerView;
    public MyWishList_Fragment() {
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
        View view= inflater.inflate(R.layout.fragment_my_wish_list_, container, false);
        wishlistRecyclerView=view.findViewById(R.id.wishlist_recyclerview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        wishlistRecyclerView.setLayoutManager(linearLayoutManager);
        List<WishListModel> wishListModelList=new ArrayList<>();
//        wishListModelList.add(new WishListModel(R.drawable.mobile_icons,1,27,"Google pixel Dx2","3","Rs.4999/-","Rs.5999/-","cash on delivery"));
//        wishListModelList.add(new WishListModel(R.drawable.mobile_icons,0,270,"Google pixel MX2","4","Rs.3999/-","Rs.4999/-","cash on delivery"));
//        wishListModelList.add(new WishListModel(R.drawable.mobile_icons,2,75,"Google pixel RX2","2","Rs.2999/-","Rs.3999/-","cash on delivery"));
//        wishListModelList.add(new WishListModel(R.drawable.mobile_icons,4,627,"Google pixel ZX2","5","Rs.5999/-","Rs.6999/-","cash on delivery"));
//        wishListModelList.add(new WishListModel(R.drawable.mobile_icons,1,7,"Google pixel KX2","1","Rs.6999/-","Rs.8999/-","cash on delivery"));
        WishListAdapter wishListAdapter=new WishListAdapter(wishListModelList,true);
        wishlistRecyclerView.setAdapter(wishListAdapter);
        wishListAdapter.notifyDataSetChanged();
        return view;
    }
}