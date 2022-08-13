package com.example.haidersapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
     private Button signup;
     private EditText named;
     private EditText username;
     private  EditText password;
     validateinput valid;
     private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        username=findViewById(R.id.signusername);
        password=findViewById(R.id.signpassword);
        named=findViewById(R.id.name);
        auth=FirebaseAuth.getInstance();
        valid=new validateinput(this);
        signup=findViewById(R.id.Ssignup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user1=username.getText().toString();
                String pass=password.getText().toString();
                String names=named.getText().toString();
                if (!TextUtils.isEmpty(names)) {
                    if(valid.checkemailisvalid(user1)&&valid.checkpasswordisvalid(pass)){
                        auth.createUserWithEmailAndPassword(user1,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isComplete()){
                                    FirebaseDatabase.getInstance().getReference().child("Users").child("Names").push().setValue(names);
                                    Toast.makeText(Signup.this, "Signed up", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Signup.this,MainActivity.class));
                                }
                                else {
                                    Toast.makeText(Signup.this, "Error Occured"+task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                }
                else {
                    Toast.makeText(Signup.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}