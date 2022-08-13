package com.example.haidersapplication;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Updatmail extends AppCompatActivity {
    private EditText email;
    private Button updateemail;
    private FirebaseAuth auth;
    private FirebaseUser user;

    validateinput valid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateemail);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        email=findViewById(R.id.email);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        valid=new validateinput(this);
        updateemail=findViewById(R.id.updatetheemail);
        updateemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              update();
            }
        });
    }
    private void update() {
        String updemail=email.getText().toString();
        if (valid.checkemailisvalid(updemail)) {
            user.updateEmail(updemail).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Updatmail.this, "Updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Updatmail.this,MainActivity.class));
                    }
                    else {
                        Toast.makeText(Updatmail.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }


}
