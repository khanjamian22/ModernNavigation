package com.techakram.Online_Shop;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpFragment extends Fragment {
    private TextView textView;
    private FrameLayout parentframeLayout;
    private EditText et_name, et_email, et_password, et_cnpassword;
    private Button sign_up;
    ImageButton closeBtn;
    private FirebaseAuth firebaseAuth;
    ProgressBar progressBar;
    private FirebaseFirestore db;
    //FirebaseDatabase firebaseDatabase;

    // private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        textView = view.findViewById(R.id.alreadyhaveanaccount);
        parentframeLayout = getActivity( ).findViewById(R.id.register_framelayout);
        closeBtn=view.findViewById(R.id.close_btn);
        et_name = view.findViewById(R.id.fname);
        et_email = view.findViewById(R.id.email);
        et_password = view.findViewById(R.id.password);
        et_cnpassword = view.findViewById(R.id.cn_password);
        sign_up = view.findViewById(R.id.register_btn);
        progressBar=view.findViewById(R.id.progress);
        firebaseAuth = FirebaseAuth.getInstance( );
        db = FirebaseFirestore.getInstance( );
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        closeBtn.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {

            }
        });
        sign_up.setOnClickListener(new View.OnClickListener( ) {

            @Override
            public void onClick(View view) {
                Log.d("akram", "clicked button");
                checkEmailAndPassword();

            }
        });
        textView.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                setFragment(new SignInFragment( ));

            }
        });

    }

    private void checkEmailAndPassword()
    {
        if(et_password.getText().toString().equals(et_cnpassword.getText().toString()))
        {
            progressBar.setVisibility(View.VISIBLE);
              firebaseAuth.createUserWithEmailAndPassword(et_email.getText().toString(),
                      et_password.getText().toString())
                      .addOnCompleteListener(new OnCompleteListener<AuthResult>( ) {
                          @Override
                          public void onComplete(@NonNull Task<AuthResult> task) {
                              if(task.isSuccessful()) {
                                  if(signUpvalidation()==6) {

                                      String name=et_name.getText().toString();
                                      Map<String,Object> user=new HashMap<>();
                                      user.put("fName",name);
                                      db.collection("user")
                                              .add(user).addOnCompleteListener(new OnCompleteListener<DocumentReference>( ) {
                                          @Override
                                          public void onComplete(@NonNull Task<DocumentReference> task) {
                                              Intent intent = new Intent(getActivity( ), MainActivity.class);
                                              startActivity(intent);
                                          }
                                      }).addOnFailureListener(new OnFailureListener( ) {
                                          @Override
                                          public void onFailure(@NonNull Exception e) {
                                              Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show( );
                                          }
                                      });



                                  }
                              }
                              else
                              {
                                  String error=task.getException().getMessage();
                                  Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show( );
                              }
                          }
                      });
            progressBar.setVisibility(View.GONE);
        }
        else
        {
            Toast.makeText(getActivity(), "failed signup", Toast.LENGTH_SHORT).show( );
        }
    }

    private int signUpvalidation() {
        if(TextUtils.isEmpty(et_name.getText().toString()))
        {
          et_name.setError("name can't epmty");
          return 1;
        }
        else if(TextUtils.isEmpty(et_email.getText().toString()))
        {
            et_name.setError("email can't epmty");
            return 2;
        }
        else if(TextUtils.isEmpty(et_password.getText().toString()))
        {
            et_name.setError("password can't epmty");
            return 3;
        }
        else if(TextUtils.isEmpty(et_cnpassword.getText().toString()))
        {
            et_name.setError("cnpassword can't epmty");
            return 4;
        }

        else
        {
          return 6;
        }


    }

    private void setFragment (Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity( ).getSupportFragmentManager( ).beginTransaction( );
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_left, R.anim.slide_out_from_right);
        fragmentTransaction.replace(parentframeLayout.getId( ), fragment);
        fragmentTransaction.commit( );
    }

}
