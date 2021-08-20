package com.techakram.Online_Shop;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class RegisterActivity extends AppCompatActivity {
     public  static Boolean setSignUpFragment=false;
     private FrameLayout frameLayout;
     public static boolean onresetPasswordfragment=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        frameLayout=findViewById(R.id.register_framelayout);
        if(setSignUpFragment)
        {
           setSignUpFragment=false;
           setFragment(new SignUpFragment());
        }
        else {
            setDefaultFragment(new SignInFragment( ));
        }
        // signin set ker diye..
    }
    //for back button click...........
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if(onresetPasswordfragment)
            {
                // for exit activity....
                onresetPasswordfragment=false;
                setFragment(new SignInFragment());
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setDefaultFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }
    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }
}