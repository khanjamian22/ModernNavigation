package com.example.modernnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.modernnavigation.common.StaticMethod;
import com.example.modernnavigation.databinding.ActivityMainBinding;
import com.example.modernnavigation.fragment.HomeFragment;
import com.example.modernnavigation.fragment.NotificationFragment;
import com.example.modernnavigation.fragment.ProfileFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if(savedInstanceState==null){
          StaticMethod.loadFragmentWithStack(MainActivity.this,new HomeFragment());
        }
      actionBarDrawerToggle=new ActionBarDrawerToggle(this,binding.drawerLayout,R.string.drawer_open,R.string.dawer_close);
      binding.drawerLayout. addDrawerListener(actionBarDrawerToggle);
      actionBarDrawerToggle.syncState();
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      //TODO for navigation
        bottomMenu();
        navigation();
    }

    private void navigation() {
        binding.navView.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i){
                    case R.id.nav_home:
                        StaticMethod.loadFragmentWithStack(MainActivity.this,new HomeFragment());
                         binding.drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_notification:
                        StaticMethod.loadFragmentWithStack(MainActivity.this,new NotificationFragment());
                        binding.drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_profile:
                        StaticMethod.loadFragmentWithStack(MainActivity.this,new ProfileFragment());
                        binding.drawerLayout.closeDrawers();
                        break;

                }
            }
        });
    }

    private void bottomMenu() {
        binding.bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i){
                    case R.id.bottom_home:
                        StaticMethod.loadFragmentWithStack(MainActivity.this,new HomeFragment());
                        break;

                    case R.id.bottom_notification:
                        StaticMethod.loadFragmentWithStack(MainActivity.this,new NotificationFragment());
                        break;
                    case R.id.bottom_account:
                        StaticMethod.loadFragmentWithStack(MainActivity.this,new ProfileFragment());
                        break;
                    default:

                       return;
                }
            }
        });
    }

    //Todo click toggle and expand navigation
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}