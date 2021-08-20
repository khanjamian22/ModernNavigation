package com.techakram.Online_Shop;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordFragment extends Fragment {

    private ImageView back_btn;
    private EditText et_email;
    Button bt_resetPassword;
    FrameLayout parentFramelayout;
    private ViewGroup emailICONcontainer;
     private ImageView emailIcon;
    TextView emailIconText;
    private ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    public ResetPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_reset_password, container, false);
        firebaseAuth=FirebaseAuth.getInstance();
        back_btn=view.findViewById(R.id.forget_password_back_btn);
       parentFramelayout = getActivity( ).findViewById(R.id.register_framelayout);
        et_email=view.findViewById(R.id.etEmailId);
        emailICONcontainer=view.findViewById(R.id.forgotPassEmailIconContainer);
        emailIcon=view.findViewById(R.id.EmailIcon);
        emailIconText=view.findViewById(R.id.emailRecoveryTextview);
        progressBar=view.findViewById(R.id.forgotprogressbar);

        bt_resetPassword=view.findViewById(R.id.forget_password_next_btn);
        back_btn.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                setFragment(new SignInFragment( ));
            }
        });
        checkInput();
        bt_resetPassword.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                //TransitionManager.beginDelayedTransition(emailICONcontainer);
                //emailIconText.setVisibility(View.GONE);
                TransitionManager.beginDelayedTransition(emailICONcontainer);
                emailIcon.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
               firebaseAuth.sendPasswordResetEmail(et_email.getText().toString())
               .addOnCompleteListener(new OnCompleteListener<Void>( ) {
                   @Override
                   public void onComplete(@NonNull Task<Void> task) {
                      if(task.isSuccessful())
                      {
                          if(checkInput()==2)
                          {
                              TransitionManager.beginDelayedTransition(emailICONcontainer);
                              progressBar.setVisibility(View.GONE);
                              emailIcon.setVisibility(View.VISIBLE);
                              emailIconText.setVisibility(View.VISIBLE);
                              emailIconText.setTextColor(getResources().getColor(R.color.green));

                              //Toast.makeText(getActivity(), "email sent successfully", Toast.LENGTH_SHORT).show( );
                          }
                      }
                      else
                      {
                          String error=task.getException().getMessage();
                           emailIconText.setText(error);
                           emailIconText.setTextColor(getResources().getColor(R.color.colorPrimarydark));
                           TransitionManager.beginDelayedTransition(emailICONcontainer);
                           emailIconText.setVisibility(View.VISIBLE);
                           // Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show( );
                      }
                       progressBar.setVisibility(View.GONE);
                   }
               });
            }
        });
        return view;
    }

    private int checkInput()
    {
        if(TextUtils.isEmpty(et_email.getText().toString())) {
            et_email.setError("email can't empty");
            return 1;
        }
        else {
            return 2;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        back_btn.setOnClickListener(new View.OnClickListener( ) {
//            @Override
//            public void onClick(View view) {
//                setFragment(new SignInFragment( ));
//            }
//        });
    }
    private void setFragment (Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity( ).getSupportFragmentManager( ).beginTransaction( );
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_left, R.anim.slide_out_from_right);
        fragmentTransaction.replace(parentFramelayout.getId( ), fragment);
        //fragmentTransaction.replace(R.id.main, fragment);
        fragmentTransaction.addToBackStack("null");
        fragmentTransaction.commit( );

    }


}