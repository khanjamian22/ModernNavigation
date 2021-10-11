package com.example.mypractice.activity.user_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.mypractice.R;
import com.example.mypractice.activity.service_provider_activity.LocalBusinessManFormActivity;
import com.example.mypractice.callback.BaseCallBack;
import com.example.mypractice.databinding.ActivityChooseRolesBinding;
import com.example.mypractice.model.RoleUpdateResponse;
import com.example.mypractice.networking.Api;
import com.example.mypractice.utils.Constance;
import com.example.mypractice.utils.Utils;

public class ChooseRolesActivity extends AppCompatActivity {

    private static final String TAG = ChooseRolesActivity.class.getSimpleName();
    /*TODO view Binding */
    private ActivityChooseRolesBinding vb;

    private RelativeLayout lastSelectedLayout;
    private int choose_role_id = -1;


    /*TODO start Activity*/
    public static void  startActivity(Activity activity){

        Intent intent = new Intent(activity,ChooseRolesActivity.class);
        activity.startActivity(intent);

//        activity.overridePendingTransition(R.anim.slide_up,R.anim.slide_down);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_roles);
        initOnClickListener();
    }

    private void initOnClickListener() {
        /*TODO for first layout*/
        vb.employeeRlClick.setOnClickListener(v->{
            choose_role_id=1;
            activateContinueButton();
            changeBackground(lastSelectedLayout,vb.employeeRlClick);
        });

        /*TODO for second layout*/
        vb.hireRlClick.setOnClickListener(v->{
            choose_role_id=2;
            activateContinueButton();
            changeBackground(lastSelectedLayout,vb.hireRlClick);
        });

        /*TODO for continue button*/
        vb.continueBtn.setOnClickListener(v->{
            if(choose_role_id!=-1)
            {
                updateRoleApiCall(choose_role_id);
            }
            else {
                Utils.showToast(getString(R.string.errorPleaseSelectrole),ChooseRolesActivity.this);
            }
        });
        vb.closeBtn.setOnClickListener(v->{
            finish();
        });
    }


    /*TODO change layout background */
    private void changeBackground(RelativeLayout lastlayout,RelativeLayout selectedLayout) {
        if(lastlayout!=null)
        {   /*TODO FOR LAST TIME SELECTED LAYOUT*/
            lastlayout.setBackground(null);
        }
        /*TODO for current selected layout*/
        if(selectedLayout !=null)
        {
           selectedLayout.setBackground(getDrawable(R.drawable.dark_main_stroke_color));
        }

        lastSelectedLayout=selectedLayout;
    }

    @Override
    protected void onDestroy() {
        overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
        super.onDestroy();
    }
    /*TODO activate continue button*/
    private void activateContinueButton() {
        vb.continueBtn.setBackgroundDrawable(getDrawable(R.drawable.click_effect));
    }
    /*TODO for update Role*/
    private void updateRoleApiCall(int role_id) {
        String role= Constance.USER_ROLE;
        if(role_id==1)
        {
            role=Constance.SERVICE_PROVIDER;
        }
        else  if(role_id==2)
        {
            role=Constance.USER_ROLE;
        }
        final String finalrole=role;
        new Api(this).roleUpdate(new BaseCallBack() {
            @Override
            public void onResponse(Object response) {
                RoleUpdateResponse roleUpdateResponse=(RoleUpdateResponse) response;
                if(roleUpdateResponse.isError())
                {
                    Utils.showToast(roleUpdateResponse.getMessage(),ChooseRolesActivity.this);
                }
                else {
                    /*TODO save login user role*/
                      Utils.saveRole(true);
                      Utils.saveRoleName(finalrole);
                      Intent intent;
                      if(finalrole.equalsIgnoreCase(Constance.SERVICE_PROVIDER))
                      {
                          intent=new Intent(ChooseRolesActivity.this, LocalBusinessManFormActivity.class);
                      }
                      else {
                          intent=new Intent(ChooseRolesActivity.this,UserHomeActivity.class);
                      }
                      startActivity(intent);
                }
            }

            @Override
            public void onError(String error) {

            }
        },finalrole);
    }
}