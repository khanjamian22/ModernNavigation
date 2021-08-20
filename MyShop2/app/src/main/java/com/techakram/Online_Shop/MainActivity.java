package com.techakram.Online_Shop;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import static com.techakram.Online_Shop.RegisterActivity.setSignUpFragment;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener
{
    private  static final int Home_Fragment=0;
    private static final int Cart_Fragment=1;
    private static final int Orders_Fragment=2;
    private static final int WishList_Fragment=3;
    private static final int Reward_Fragment=4;
    private static final int Account_Fragment=5;
    private FrameLayout frameLayout;
    private  int Current_Fragment=-1;
    private  NavigationView navigationView;
    private TextView main_actibar_title;
    private Window window;
    private Toolbar toolbar;
   // private ImageView imageViewConnection;
    public static Boolean showCart=false;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        window=getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        main_actibar_title=findViewById(R.id.action_barTitle);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        frameLayout=findViewById(R.id.main_frame_layout);

            if (showCart) {    // drawer locking in this activity
                drawer.setDrawerLockMode(1);
                getSupportActionBar( ).setDisplayHomeAsUpEnabled(true);
                goToFragment("My cart", new MyCartFragment( ), -2);
            } else {
                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
                drawer.addDrawerListener(toggle);
                toggle.syncState( );
                setFragment(new HomeFragment( ), Home_Fragment);
            }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
             drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            if(Current_Fragment==Home_Fragment)
            {
                Current_Fragment=-1;
                super.onBackPressed( );
            }
            else {
                if (showCart)
                {
                       showCart=false;
                       finish();
                } else {
                    invalidateOptionsMenu( );
                    main_actibar_title.setVisibility(View.VISIBLE);
                    setFragment(new HomeFragment( ), Home_Fragment);
                    navigationView.getMenu( ).getItem(0).setChecked(true);

                }
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       if (Current_Fragment==Home_Fragment) {
           getMenuInflater( ).inflate(R.menu.main, menu);
       }
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
        else if(id==R.id.main_notification)
        {
            return true;
        }
        else if(id==R.id.main_cart)
        {
            Dialog signindialog=new Dialog(MainActivity.this);
            signindialog.setCancelable(true);
            signindialog.setContentView(R.layout.signin_dialog_layout);
            signindialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            Button signInBtn=signindialog.findViewById(R.id.signIndialog_button);
            Button signUpBtn=signindialog.findViewById(R.id.SignUpdialog_button);
            final Intent registerIntent=new Intent(MainActivity.this,RegisterActivity.class);
             signInBtn.setOnClickListener(new View.OnClickListener( ) {
                @Override
                public void onClick(View v) {
                        signindialog.dismiss();
                    setSignUpFragment=false;
                    startActivity(registerIntent);
                }
            });
            signUpBtn.setOnClickListener(new View.OnClickListener( ) {
                @Override
                public void onClick(View v) {
                    signindialog.dismiss();
                    setSignUpFragment=true;
                    startActivity(registerIntent);

                }
            });
            signindialog.show();
            //goToFragment("My Cart",new MyCartFragment(),Cart_Fragment);
            return true;
        }
        else if (id==android.R.id.home)
        {
          if(showCart)
          {
              showCart=false;
              finish();
              return true;
          }
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToFragment(String title,Fragment fragment,int fragmentno) {
        MyCartFragment myCartFragment=new MyCartFragment();

        main_actibar_title.setVisibility(View.INVISIBLE);//its use for remove menu from action bar when click any icon..
        getSupportActionBar().setTitle(title);
        invalidateOptionsMenu();
        setFragment(fragment,fragmentno);
        if(fragmentno==Cart_Fragment) {
            navigationView.getMenu( ).getItem(3).setChecked(true);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
         int id=item.getItemId();
         if(id==R.id.nav_my_mall)
         {
             invalidateOptionsMenu();
             main_actibar_title.setVisibility(View.VISIBLE);
             setFragment(new HomeFragment(),Home_Fragment);

           //handle home......
         }
         else if (id==R.id.nav_order)
         {
           goToFragment("My Orders",new MyOrderFragment(),Orders_Fragment);
         }
         else if (id==R.id.nav_reward)
         {
             goToFragment("My Rewards", new MyReward_Fragment(),Reward_Fragment);
         }
         else if (id==R.id.nav_cart)
         {
             goToFragment("My Cart",new MyCartFragment(),Cart_Fragment);
         }
         else if (id==R.id.nav_wishlist)
         {
             goToFragment("My Wish List",new MyWishList_Fragment(),WishList_Fragment);
         }
         else if (id==R.id.nav_account)
         {
          goToFragment("My account",new MyAccount_Fragment(),Account_Fragment);
         }
         else if (id==R.id.nav_logout)
         {

         }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
         drawer.closeDrawer(GravityCompat.START);
         return true;
    }
    private void setFragment(Fragment fragment, int Fragmentno)
    {
        if(Fragmentno!=Current_Fragment)
        {
            if(Fragmentno==Reward_Fragment)
            {
               window.setStatusBarColor(Color.parseColor("#5B04B1"));
               toolbar.setBackgroundColor(Color.parseColor("#5B04B1"));
            }
            else {
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimarydark));
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimarydark));
            }
        Current_Fragment=Fragmentno;
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.faidin,R.anim.faidout);
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }
 }

}