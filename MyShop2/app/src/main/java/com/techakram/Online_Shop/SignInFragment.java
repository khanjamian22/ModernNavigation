package com.techakram.Online_Shop;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInFragment extends Fragment {
    TextView textView,forgetTextview;
    FrameLayout parentFramelayout;
    EditText et_email,et_password;
    Button login_btn;
    ImageButton close_btn;
    FirebaseAuth firebaseAuth;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    public SignInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sign_in, container, false);
         textView=view.findViewById(R.id.donthaveanaccount);
         forgetTextview=view.findViewById(R.id.forgotLink);
         et_email=view.findViewById(R.id.email);
         et_password=view.findViewById(R.id.password);
         login_btn=view.findViewById(R.id.signIn_btn);
         close_btn=view.findViewById(R.id.crossBtn);
         firebaseAuth=FirebaseAuth.getInstance();
         parentFramelayout=getActivity().findViewById(R.id.register_framelayout);
         return  view;
    }
    //ye jab create ho jayga tab
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        close_btn.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                Intent mainIntent=new Intent(getActivity(),MainActivity.class);
                startActivity(mainIntent);
                getActivity().finish();
            }
        });
        forgetTextview.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                //isme if key wala chalega.....
                RegisterActivity.onresetPasswordfragment=true;
               setFragment(new ResetPasswordFragment());
            }
        });
        textView.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {

                setFragment(new SignUpFragment());
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                checkEmailAndPassword();
            }
        });

    }

    private void checkEmailAndPassword()
    {
        if(et_email.getText().toString().matches(emailPattern))
        {
            if(et_password.length()>=8)
            {

//                login_btn.setEnabled(false);
//                login_btn.setTextColor(Color.argb(50,255,255,255));
                firebaseAuth.signInWithEmailAndPassword(et_email.getText().toString()
                        ,et_password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>( ) {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    if(validate()==3)
                                    {
                                        Intent mainIntent=new Intent(getActivity(),MainActivity.class);
                                        startActivity(mainIntent);
                                        getActivity().finish();
                                    }

                                }
                                else
                                {
//                                    login_btn.setEnabled(true);
//                                    login_btn.setTextColor(Color.rgb(255,255,255));
                                    String  error=task.getException().getMessage();
                                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show( );
                                }
                            }
                        });
            }
            else
            {
                Toast.makeText(getActivity(), "incorrect email or password", Toast.LENGTH_SHORT).show( );
            }
        }
        else
        {
            Toast.makeText(getActivity(), "incorrect email or password", Toast.LENGTH_SHORT).show( );
        }
    }

    private int validate()
    {
        if(TextUtils.isEmpty(et_email.getText().toString()))
        {
            et_email.setError("email can't empty");
            return 1;
        }
        else if(TextUtils.isEmpty(et_password.getText().toString()))
        {
            et_password.setError("password can't empty");
            return 2;
        }
        else
        {
            return 3;
        }

    }

    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_right_anim,R.anim.slide_out_from_left);
        fragmentTransaction.replace(parentFramelayout.getId(),fragment);
        fragmentTransaction.commit();
    }
}