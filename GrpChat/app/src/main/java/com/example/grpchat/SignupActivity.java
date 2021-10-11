package com.example.grpchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    EditText e1,e3,e4;
    Button btn_register;
     Model model;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        e1=findViewById(R.id.edit1);
        e3=findViewById(R.id.edit3);
        e4=findViewById(R.id.edit4);
        btn_register=findViewById(R.id.register);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance().getReference().child("Users");

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email=e3.getText().toString().trim();
                String password=e4.getText().toString().trim();

                firebaseAuth.createUserWithEmailAndPassword(Email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                            {
                                setdate();

                                Toast.makeText(SignupActivity.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));


                            } else {

                                Toast.makeText(SignupActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                            }

                            }
                        });
            }

        });
    }
     public void setdate()
     {
         String name=e1.getText().toString();
         model.setMessageUser(name);
         String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
         model.setMessageUserId(id);
         firebaseDatabase.child(id).setValue(model);

     }

}