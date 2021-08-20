package com.techakram.Online_Shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class MyAccount_Fragment extends Fragment {
    private Button viewAlladdressBtn;
    public static final int MANAGE_ADDRESS=1;

    public MyAccount_Fragment() {
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
         View view= inflater.inflate(R.layout.fragment_my_account_, container, false);
         viewAlladdressBtn=view.findViewById(R.id.viewAllAddressesButton);
         viewAlladdressBtn.setOnClickListener(new View.OnClickListener( ) {
             @Override
             public void onClick(View v) {
                 Intent myaddressesIntent=new Intent(getContext(),MyAddressesActivity.class);
                 myaddressesIntent.putExtra("MODE",MANAGE_ADDRESS);
                 startActivity(myaddressesIntent);
             }
         });
         return view;
    }
}