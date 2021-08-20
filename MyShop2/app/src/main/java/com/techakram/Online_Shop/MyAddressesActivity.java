package com.techakram.Online_Shop;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import java.util.ArrayList;
import java.util.List;

import static com.techakram.Online_Shop.DeliveryActivity.SELECT_ADDRESS;

public class MyAddressesActivity extends AppCompatActivity {
     private Button deliverHereBtn;
    private RecyclerView addressesRecyclerView;
     public static AddressesAdapter addressesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_addresses);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar( ).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar( ).setDisplayShowTitleEnabled(true);
        getSupportActionBar( ).setTitle("My Addresse");
        addressesRecyclerView=findViewById(R.id.my_addresse_recyclerview);
        deliverHereBtn=findViewById(R.id.deliver_here_button);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        addressesRecyclerView.setLayoutManager(linearLayoutManager);
        List<AddresssModel> addresssModelList=new ArrayList<>();
        addresssModelList.add(new AddresssModel("Akram khan","kakraka budauun","243637",true));
        addresssModelList.add(new AddresssModel("Aman khan","budauun","247637",false));
        addresssModelList.add(new AddresssModel("Murshid khan","alapur","243937",false));
        addresssModelList.add(new AddresssModel("Arshad khan","Nawada budauun","240637",false));
        addresssModelList.add(new AddresssModel("Nazeem khan","shekhupur budauun","244637",false));
         int mode=getIntent().getIntExtra("MODE",-1);
         if(mode==SELECT_ADDRESS)
         {
           deliverHereBtn.setVisibility(View.VISIBLE);
         }
         else
         {
             deliverHereBtn.setVisibility(View.INVISIBLE);
         }
        addressesAdapter=new AddressesAdapter(addresssModelList,mode);
        addressesRecyclerView.setAdapter(addressesAdapter);
        addressesAdapter.notifyDataSetChanged();
        ((SimpleItemAnimator)addressesRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
    }
    //for items refereshes.........
    public static void refreshItem(int deselect,int select)
    {
      addressesAdapter.notifyItemChanged(deselect);
      addressesAdapter.notifyItemChanged(select);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId( );
        if (id == android.R.id.home) {
            finish( );

        }
        return true;
    }
}