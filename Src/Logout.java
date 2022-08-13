package com.example.haidersapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Logout extends AppCompatActivity {
     private Button logout;
     private Button updatepass;
     Context context;
     private  Button updateemail;
     FirebaseAuth auth;
     validateinput validateinput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_logout);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        auth = FirebaseAuth.getInstance();
        validateinput = new validateinput(this);
      auth=FirebaseAuth.getInstance();
      updateemail=findViewById(R.id.updateemail);
      updateemail.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(Logout.this, Updatmail.class));

          }
      });
      updatepass=findViewById(R.id.updatepass);
      updatepass.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
           startActivity(new Intent(Logout.this,updatepassword.class));
          }
      });
      logout=findViewById(R.id.logout);
      logout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
                  AlertDialog.Builder builder=new AlertDialog.Builder(context);
                  builder.setMessage("Do You Want To Logout").setPositiveButton("yes", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                          Toast.makeText(Logout.this, "Logging out", Toast.LENGTH_SHORT).show();
                          auth.signOut();
                          ((Activity)(context)).finish();
                      }
                  }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                          Toast.makeText(Logout.this, "Declined", Toast.LENGTH_SHORT).show();
                      }
                  });

              Toast.makeText(Logout.this, "Signing Out", Toast.LENGTH_SHORT).show();
              startActivity(new Intent(Logout.this,MainActivity.class));
              AlertDialog dialog=builder.create();
              dialog.show();
          }

      });


    }


}