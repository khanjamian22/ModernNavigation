package com.techakram.Online_Shop;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.techakram.Online_Shop.DBQueries.lists;
import static com.techakram.Online_Shop.DBQueries.loadCategoriesName;
import static com.techakram.Online_Shop.DBQueries.loadFragmentData;

public class CatagoryActivity extends AppCompatActivity {
      RecyclerView catagory_recyclerView;
      private HomePageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagory);
        Toolbar toolbar = findViewById(R.id.toolbar);
        catagory_recyclerView=findViewById(R.id.catagoryActivity_recyclerView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String title=getIntent().getStringExtra("catagoryName");
        getSupportActionBar().setTitle(title);


        //banner slider
      //  List<SliderModel> sliderModelList=new ArrayList<SliderModel>();
//        sliderModelList.add(new SliderModel(R.mipmap.drawer_reward,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.drawer_logout,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.drawer_cart,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.drawer_user,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.drawer_order,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.drawer_reward,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.drawer_logout,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.drawer_cart,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.drawer_user,"#077AE4"));
        //////////////////banner slider
//
//        List<Horizontal_product_scroll_Model> horizontal_product_scroll_modelList=new ArrayList<>();
//        horizontal_product_scroll_modelList.add(new Horizontal_product_scroll_Model(R.drawable.mobile_icons,
//                "Redmi 5A","SD 625 Processor","Rs.5999/-"));
//        horizontal_product_scroll_modelList.add(new Horizontal_product_scroll_Model(R.drawable.mobile_icons,
//                "Redmi 6A","SD 525 Processor","Rs.6999/-"));
//        horizontal_product_scroll_modelList.add(new Horizontal_product_scroll_Model(R.drawable.mobile_icons,
//                "Samsung 5A++","SD 225 Processor","Rs.7999/-"));
//        horizontal_product_scroll_modelList.add(new Horizontal_product_scroll_Model(R.drawable.mobile_icons,
//                "Lenovo A","SD 925 Processor","Rs.8999/-"));
//        horizontal_product_scroll_modelList.add(new Horizontal_product_scroll_Model(R.drawable.mobile_icons,
//                "Nokia 5s","SD 625 Processor","Rs.9999/-"));
//        horizontal_product_scroll_modelList.add(new Horizontal_product_scroll_Model(R.drawable.mobile_icons,
//                "Apple 67a","SD 825 Processor","Rs.2999/-"));
//        horizontal_product_scroll_modelList.add(new Horizontal_product_scroll_Model(R.drawable.mobile_icons,
//                "Techno asus","SD 725 Processor","Rs.1999/-"));
//
//        ////////////////////testing

        LinearLayoutManager testingLayoutManager=new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        catagory_recyclerView.setLayoutManager(testingLayoutManager);
        //List<HomePageModel> homePageModelList=new ArrayList<>();
//        homePageModelList.add(new HomePageModel(0,sliderModelList));
//        homePageModelList.add(new HomePageModel(1,R.drawable.mobile_icons,"#000000"));
//        homePageModelList.add(new HomePageModel(2,"DEALS OF THE DAY",horizontal_product_scroll_modelList));
//        homePageModelList.add(new HomePageModel(3,"DEALS OF THE DAY",horizontal_product_scroll_modelList));
//        homePageModelList.add(new HomePageModel(1,R.drawable.mobile_icons,"#000000"));
//        homePageModelList.add(new HomePageModel(3,"DEALS OF THE DAY",horizontal_product_scroll_modelList));
//        homePageModelList.add(new HomePageModel(2,"DEALS OF THE DAY",horizontal_product_scroll_modelList));
//        homePageModelList.add(new HomePageModel(1,R.drawable.mobile_icons,"#000000"));
        int listPosition=0;
        for(int x=0;x< loadCategoriesName.size();x++)
        {
          if(loadCategoriesName.get(x).equals(title.toUpperCase()))
          {
             listPosition=x;
          }
        }
        if(listPosition==0)
        {
            loadCategoriesName.add(title.toUpperCase());
            lists.add(new ArrayList<HomePageModel>());
            adapter=new HomePageAdapter(lists.get(loadCategoriesName.size()-1));
            loadFragmentData(adapter,this,loadCategoriesName.size()-1,title);
        }
        else
        {
            adapter=new HomePageAdapter(lists.get(listPosition));
        }
        //HomePageAdapter adapter=new HomePageAdapter(homePageModelList);
        catagory_recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater( ).inflate(R.menu.search_icon, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.main_search)
        {
            //to do somethings...
            return true;
        }
        if(id==android.R.id.home)
        {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}