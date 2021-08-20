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

public class MyReward_Fragment extends Fragment {
    private RecyclerView rewaedRecyclerView;
    public MyReward_Fragment() {
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
        View view=inflater.inflate(R.layout.fragment_my_reward_, container, false);
        rewaedRecyclerView=view.findViewById(R.id.reward_recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rewaedRecyclerView.setLayoutManager(linearLayoutManager);
        List<RewardModel> rewardModelList=new ArrayList<>();
        rewardModelList.add(new RewardModel("Cashback","till 16th june 2021","Get 40% cashback on Rs.5000 abobe and Rs.1000 below"));
        rewardModelList.add(new RewardModel("Discount","till 26th june 2021","Get 30% cashback on Rs.5000 abobe and Rs.1000 below"));
        rewardModelList.add(new RewardModel("Buy 1 get 1 free","till 06th july 2021","Get 10% cashback on Rs.5000 abobe and Rs.1000 below"));
        rewardModelList.add(new RewardModel("Cashback","till 16th june 2021","Get 40% cashback on Rs.5000 abobe and Rs.1000 below"));
        rewardModelList.add(new RewardModel("Discount","till 26th june 2021","Get 30% cashback on Rs.5000 abobe and Rs.1000 below"));
        rewardModelList.add(new RewardModel("Buy 1 get 1 free","till 06th july 2021","Get 10% cashback on Rs.5000 abobe and Rs.1000 below"));
        rewardModelList.add(new RewardModel("Cashback","till 16th june 2021","Get 40% cashback on Rs.5000 abobe and Rs.1000 below"));
        rewardModelList.add(new RewardModel("Discount","till 26th june 2021","Get 30% cashback on Rs.5000 abobe and Rs.1000 below"));
        rewardModelList.add(new RewardModel("Buy 1 get 1 free","till 06th july 2021","Get 10% cashback on Rs.5000 abobe and Rs.1000 below"));
        RewardAdapter rewardAdapter=new RewardAdapter(rewardModelList,false);
        rewaedRecyclerView.setAdapter(rewardAdapter);
        rewardAdapter.notifyDataSetChanged();
        return view;
    }
}