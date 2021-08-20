package com.techakram.Online_Shop;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.techakram.Online_Shop.DBQueries.catagoryModelList;
import static com.techakram.Online_Shop.DBQueries.lists;
import static com.techakram.Online_Shop.DBQueries.loadCategoriesName;
import static com.techakram.Online_Shop.DBQueries.loadCategory;
import static com.techakram.Online_Shop.DBQueries.loadFragmentData;

public class HomeFragment extends Fragment {
    private RecyclerView catagoryRecyclerview;
    private RecyclerView homePageRecyclerView;
    private CatagoryAdapter catagoryAdapter;
    private HomePageAdapter adapter;
    private ImageView imageViewConnection;
//    private List<HomePageModel> homePageModelList;
    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        imageViewConnection=view.findViewById(R.id.no_internet_connection);
        ConnectivityManager connectivityManager=(ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected()==true) {
            imageViewConnection.setVisibility(View.GONE);
            catagoryRecyclerview=view.findViewById(R.id.catagoryHome_recycler_view);
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            catagoryRecyclerview.setLayoutManager(linearLayoutManager);
            catagoryAdapter=new CatagoryAdapter(catagoryModelList);
            catagoryRecyclerview.setAdapter(catagoryAdapter);
            if(catagoryModelList.size()==0)
            {
                loadCategory(catagoryAdapter,getContext());
            }
            else
            {
                catagoryAdapter.notifyDataSetChanged();
            }
            ///////////////////////////////////////home page recycler
            homePageRecyclerView =view.findViewById(R.id.HomePageRecyclerView);
            LinearLayoutManager testingLayoutManager=new LinearLayoutManager(getContext());
            testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            homePageRecyclerView.setLayoutManager(testingLayoutManager);
            //homePageModelList=new ArrayList<HomePageModel>();

            if (lists.size()==0)
            {
                loadCategoriesName.add("HOME");
                lists.add(new ArrayList<HomePageModel>());
                adapter=new HomePageAdapter(lists.get(0));
                loadFragmentData(adapter,getContext(),0,"HOME");
            }
            else
            {
                adapter=new HomePageAdapter(lists.get(0));
                adapter.notifyDataSetChanged();
            }

            homePageRecyclerView.setAdapter(adapter);
        }
        else
        {
            Glide.with(this).load(R.drawable.no_internet).into(imageViewConnection);
            imageViewConnection.setVisibility(View.VISIBLE);
        }

        return view;
    }
}