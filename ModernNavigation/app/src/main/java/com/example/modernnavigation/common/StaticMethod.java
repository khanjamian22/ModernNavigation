package com.example.modernnavigation.common;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.modernnavigation.R;

public class StaticMethod {
    public static void loadFragmentWithStack(FragmentActivity fragmentActivity, Fragment fragment){
     fragmentActivity.getSupportFragmentManager()
             .beginTransaction()
             .replace(R.id.main_container,fragment)
             .addToBackStack(null)
             .commit();

    }
}
