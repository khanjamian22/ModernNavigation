package com.techakram.Online_Shop;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewAllGridOrHorizontalActivity extends AppCompatActivity {
    private RecyclerView viewAllRecyclerView;
    private GridView gridView;
    public static WishListAdapter wishListAdapter;
    public static GridProductLayoutAdapter gridProductLayoutAdapter;
    public static List<WishListModel> wishListModelList;
    public static List<Horizontal_product_scroll_Model> horizontal_product_scroll_modelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_grid_or_horizontal);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("View All Items");
        viewAllRecyclerView=findViewById(R.id.viewallrecyclerview);
        gridView=findViewById(R.id.viewallgridview);
        int layout_code=getIntent().getIntExtra("layout_code",-1);
        if(layout_code==0) {
            viewAllRecyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            viewAllRecyclerView.setLayoutManager(linearLayoutManager);
            List<WishListModel> wishListModelList = new ArrayList<>( );
//            wishListModelList.add(new WishListModel(R.drawable.mobile_icons, 1, 27, "Google pixel Dx2", "3", "Rs.4999/-", "Rs.5999/-", "cash on delivery"));
//            wishListModelList.add(new WishListModel(R.drawable.mobile_icons, 0, 270, "Google pixel MX2", "4", "Rs.3999/-", "Rs.4999/-", "cash on delivery"));
//            wishListModelList.add(new WishListModel(R.drawable.mobile_icons, 2, 75, "Google pixel RX2", "2", "Rs.2999/-", "Rs.3999/-", "cash on delivery"));
//            wishListModelList.add(new WishListModel(R.drawable.mobile_icons, 4, 627, "Google pixel ZX2", "5", "Rs.5999/-", "Rs.6999/-", "cash on delivery"));
//            wishListModelList.add(new WishListModel(R.drawable.mobile_icons, 1, 7, "Google pixel KX2", "1", "Rs.6999/-", "Rs.8999/-", "cash on delivery"));
//            wishListModelList.add(new WishListModel(R.drawable.mobile_icons, 1, 27, "Google pixel Dx2", "3", "Rs.4999/-", "Rs.5999/-", "cash on delivery"));
//            wishListModelList.add(new WishListModel(R.drawable.mobile_icons, 0, 270, "Google pixel MX2", "4", "Rs.3999/-", "Rs.4999/-", "cash on delivery"));
//            wishListModelList.add(new WishListModel(R.drawable.mobile_icons, 2, 75, "Google pixel RX2", "2", "Rs.2999/-", "Rs.3999/-", "cash on delivery"));
//            wishListModelList.add(new WishListModel(R.drawable.mobile_icons, 4, 627, "Google pixel ZX2", "5", "Rs.5999/-", "Rs.6999/-", "cash on delivery"));
//            wishListModelList.add(new WishListModel(R.drawable.mobile_icons, 1, 7, "Google pixel KX2", "1", "Rs.6999/-", "Rs.8999/-", "cash on delivery"));
//            wishListModelList.add(new WishListModel(R.drawable.mobile_icons, 1, 27, "Google pixel Dx2", "3", "Rs.4999/-", "Rs.5999/-", "cash on delivery"));
//            wishListModelList.add(new WishListModel(R.drawable.mobile_icons, 0, 270, "Google pixel MX2", "4", "Rs.3999/-", "Rs.4999/-", "cash on delivery"));
//            wishListModelList.add(new WishListModel(R.drawable.mobile_icons, 2, 75, "Google pixel RX2", "2", "Rs.2999/-", "Rs.3999/-", "cash on delivery"));
//            wishListModelList.add(new WishListModel(R.drawable.mobile_icons, 4, 627, "Google pixel ZX2", "5", "Rs.5999/-", "Rs.6999/-", "cash on delivery"));
//            wishListModelList.add(new WishListModel(R.drawable.mobile_icons, 1, 7, "Google pixel KX2", "1", "Rs.6999/-", "Rs.8999/-", "cash on delivery"));
            //Log.d("ask", String.valueOf(wishListModelList.get(1)));
            wishListAdapter = new WishListAdapter(wishListModelList, false);
            viewAllRecyclerView.setAdapter(wishListAdapter);
            wishListAdapter.notifyDataSetChanged( );
        }
        /////////////grid view.....................
        else if(layout_code==1) {
            gridView.setVisibility(View.VISIBLE);
            //List<Horizontal_product_scroll_Model> horizontal_product_scroll_modelList = new ArrayList<>( );
//            horizontal_product_scroll_modelList.add(new Horizontal_product_scroll_Model(R.drawable.mobile_icons,
//                    "Redmi 5A", "SD 625 Processor", "Rs.5999/-"));
//            horizontal_product_scroll_modelList.add(new Horizontal_product_scroll_Model(R.drawable.mobile_icons,
//                    "Redmi 6A", "SD 525 Processor", "Rs.6999/-"));
//            horizontal_product_scroll_modelList.add(new Horizontal_product_scroll_Model(R.drawable.mobile_icons,
//                    "Samsung 5A++", "SD 225 Processor", "Rs.7999/-"));
//            horizontal_product_scroll_modelList.add(new Horizontal_product_scroll_Model(R.drawable.mobile_icons,
//                    "Lenovo A", "SD 925 Processor", "Rs.8999/-"));
//            horizontal_product_scroll_modelList.add(new Horizontal_product_scroll_Model(R.drawable.mobile_icons,
//                    "Nokia 5s", "SD 625 Processor", "Rs.9999/-"));
//            horizontal_product_scroll_modelList.add(new Horizontal_product_scroll_Model(R.drawable.mobile_icons,
//                    "Apple 67a", "SD 825 Processor", "Rs.2999/-"));
//            horizontal_product_scroll_modelList.add(new Horizontal_product_scroll_Model(R.drawable.mobile_icons,
//                    "Techno asus", "SD 725 Processor", "Rs.1999/-"));
//            horizontal_product_scroll_modelList.add(new Horizontal_product_scroll_Model(R.drawable.mobile_icons,
//                    "Nokia 5s", "SD 625 Processor", "Rs.9999/-"));
//            horizontal_product_scroll_modelList.add(new Horizontal_product_scroll_Model(R.drawable.mobile_icons,
//                    "Apple 67a", "SD 825 Processor", "Rs.2999/-"));
//            horizontal_product_scroll_modelList.add(new Horizontal_product_scroll_Model(R.drawable.mobile_icons,
//                    "Techno asus", "SD 725 Processor", "Rs.1999/-"));
            gridProductLayoutAdapter = new GridProductLayoutAdapter(horizontal_product_scroll_modelList);
            gridView.setAdapter(gridProductLayoutAdapter);
        }
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