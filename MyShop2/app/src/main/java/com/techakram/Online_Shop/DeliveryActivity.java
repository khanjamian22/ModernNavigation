package com.techakram.Online_Shop;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
public class DeliveryActivity extends AppCompatActivity {
    RecyclerView deliveryRecyclerView;
    private Button changeBtn;
    public static final int SELECT_ADDRESS=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Delivery");
        deliveryRecyclerView=findViewById(R.id.delivery_recyclerView);
        changeBtn=findViewById(R.id.changeOrAddBtn);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        deliveryRecyclerView.setLayoutManager(linearLayoutManager);
        List<CartItemModel> cartItemModelList=new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0,R.drawable.mobile_icons,"Google Pixel 2",2,"5999/-","69999/-",6,7,8));
        cartItemModelList.add(new CartItemModel(0,R.drawable.mobile_icons,"Google Pixel 2",2,"5999/-","69999/-",6,7,8));
        cartItemModelList.add(new CartItemModel(0,R.drawable.mobile_icons,"Google Pixel 2",2,"5999/-","69999/-",6,7,8));
        cartItemModelList.add(new CartItemModel(0,R.drawable.mobile_icons,"Google Pixel 2",2,"4999/-","89999/-",6,7,8));

        /////////////total amount wala.................
        cartItemModelList.add(new CartItemModel(1,"Price (4 items)","Rs.30000/-","free","Rs.28994-/","Rs.3400"));

        CartItemAdapter cartItemAdapter=new CartItemAdapter(cartItemModelList);
        deliveryRecyclerView.setAdapter(cartItemAdapter);
        cartItemAdapter.notifyDataSetChanged();
        changeBtn.setVisibility(View.VISIBLE);
        changeBtn.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                Intent myaddressesIntent=new Intent(DeliveryActivity.this,MyAddressesActivity.class);
                myaddressesIntent.putExtra("MODE",SELECT_ADDRESS);
                startActivity(myaddressesIntent);
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==android.R.id.home)
        {
          finish();
          return true;
        }
        return super.onOptionsItemSelected(item);
    }

}