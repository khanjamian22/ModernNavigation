package com.techakram.Online_Shop;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import static com.techakram.Online_Shop.MainActivity.showCart;

public class ProductDetailsActivity extends AppCompatActivity {
    private TextView productTitle;
   private ViewPager productImageViewPager;
   private TabLayout viewPagerIndicator;
   private Button buyNowBtn;
   private Button coupenRedemBtn;
   ///coupendialog.....................
    public static TextView coupenTitle;
    public static TextView coupenExpiryDate;
    public static TextView coupenBody;
    private static RecyclerView coupenrecyclerview;
    private static LinearLayout selectedCoupen;
   ///coupendialog.....................
   //for product discription layout
     private TabLayout productdiscriptionTablayout;
     private ViewPager productDiscriptionViewpager;
    private FloatingActionButton addTowishListBtn;
   private  static boolean AlreadyAddToWishList=false;
   /////////////////////ratinglayout.............
    private LinearLayout ratNow_containerLayout;
    /////////////////////ratinglayout.............
    private FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        productTitle=findViewById(R.id.titleTextView);
        addTowishListBtn=findViewById(R.id.add_wishListBtn);
        coupenRedemBtn=findViewById(R.id.coupen_redemptionBtn);
        buyNowBtn=findViewById(R.id.buyNowBtn);
        productImageViewPager=findViewById(R.id.product_images_viewpager);
        viewPagerIndicator=findViewById(R.id.viewPagerIndicator);
        ////////////////
        productdiscriptionTablayout=findViewById(R.id.product_details_tabLayout);
        productDiscriptionViewpager=findViewById(R.id.product_details_viewpager);
        //String s="vG9rWirnVix9jDXleQJU";
         firebaseFirestore=FirebaseFirestore.getInstance();
         List<String> productImages=new ArrayList<>();
         firebaseFirestore.collection("PRODUCTS").document("vG9rWirnVix9jDXleQJU")
                 .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>( ) {
             @Override
             public void onComplete(@NonNull Task<DocumentSnapshot> task) {
               if(task.isSuccessful())
               {
                 DocumentSnapshot documentSnapshot=task.getResult();
                 for (long x=1;x< (long)documentSnapshot.get("no_of_product_images")+1;x++)
                 {
                   productImages.add(documentSnapshot.get("product_image_"+x).toString());
                 }
                   ProductImageAdapter productImageAdapter=new ProductImageAdapter(productImages);
                   productImageViewPager.setAdapter(productImageAdapter);
                   productTitle.setText(documentSnapshot.get("product_title").toString());
               }
               else {
                   String error=task.getException().getMessage();
                   Toast.makeText(ProductDetailsActivity.this, error, Toast.LENGTH_SHORT).show( );
               }
             }
         });

//        List<String> productImages=new ArrayList<>();
//        productImages.add(R.drawable.mobile_icons);
//        productImages.add(R.drawable.logo);
//        productImages.add(R.drawable.email_icon);
//        productImages.add(R.drawable.error_icon);

        viewPagerIndicator.setupWithViewPager(productImageViewPager);
        buyNowBtn.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                Intent deliveryIntent=new Intent(ProductDetailsActivity.this,DeliveryActivity.class);
               startActivity(deliveryIntent);

            }
        });
        addTowishListBtn.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                if(AlreadyAddToWishList)
                {
                    AlreadyAddToWishList=false;
                 addTowishListBtn.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#D6D1D1")));
                }
                else {
                    AlreadyAddToWishList=true;
                    addTowishListBtn.setSupportImageTintList(getResources().getColorStateList(R.color.colorPrimarydark  ));
                }
            }
        });
        ///////////coupenDialog
        Dialog coupenDialog=new Dialog(ProductDetailsActivity.this);
        coupenDialog.setContentView(R.layout.coupen_redem_dialog);
        coupenDialog.setCancelable(true);
        coupenDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        ImageView togglerecyclerview=coupenDialog.findViewById(R.id.toggle_recyclerView);
        coupenrecyclerview=coupenDialog.findViewById(R.id.coupens_recyclerView);
        selectedCoupen=coupenDialog.findViewById(R.id.selected_coupen);
        coupenTitle=coupenDialog.findViewById(R.id.title_textView);
        coupenExpiryDate=coupenDialog.findViewById(R.id.expiry_datae_textView);
        coupenBody=coupenDialog.findViewById(R.id.body_textView);
        TextView originalPrice=coupenDialog.findViewById(R.id.original_price);
        TextView redemptionPrice=coupenDialog.findViewById(R.id.redempt_price);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(ProductDetailsActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        coupenrecyclerview.setLayoutManager(linearLayoutManager);
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
        RewardAdapter rewardAdapter=new RewardAdapter(rewardModelList,true);
        coupenrecyclerview.setAdapter(rewardAdapter);
        rewardAdapter.notifyDataSetChanged();
        togglerecyclerview.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                settoggleRecyclerView();
            }
        });
        coupenRedemBtn.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {

                coupenDialog.show();
            }
        });
        ////////////////coupenDialog.............
        productDiscriptionViewpager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(),productdiscriptionTablayout.getTabCount()));
        productDiscriptionViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productdiscriptionTablayout));
        productdiscriptionTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener( ) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDiscriptionViewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        /////////////////////ratinglayout.............
          ratNow_containerLayout=findViewById(R.id.rate_now_linearLayout);
          for (int x=0; x<ratNow_containerLayout.getChildCount(); x++)
          {
             final int starPosition=x;
             ratNow_containerLayout.getChildAt(x).setOnClickListener(new View.OnClickListener( ) {
                 @Override
                 public void onClick(View view) {
                     setRating(starPosition);
                 }
             });
          }
        /////////////////////ratinglayout.............
    }
     public  static void settoggleRecyclerView()
     {
         if(coupenrecyclerview.getVisibility()==View.GONE)
         {
             coupenrecyclerview.setVisibility(View.VISIBLE);
             selectedCoupen.setVisibility(View.GONE);
         }
         else {
             coupenrecyclerview.setVisibility(View.GONE);
             selectedCoupen.setVisibility(View.VISIBLE);
         }
     }
    private void setRating(int starPosition) {
        for (int x=0; x<ratNow_containerLayout.getChildCount(); x++) {
            ImageView starImageBtn = (ImageView) ratNow_containerLayout.getChildAt(x);
            starImageBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));

            if (x <= starPosition)
            {
              starImageBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater( ).inflate(R.menu.search_and_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==android.R.id.home)
        {
            //to do somethings..
            finish();
            return true;
        }
        if(id==R.id.main_search)
        {
            //to do somethings...
            return true;
        }

        else if(id==R.id.main_cart)
        {
            Intent cartIntent=new Intent(ProductDetailsActivity.this,MainActivity.class);
            showCart=true;
            startActivity(cartIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}