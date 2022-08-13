package com.example.haidersapplication;

import android.content.Context;
import android.util.Patterns;
import android.widget.Toast;

public class validateinput {
    Context context;
    validateinput(Context context){
        this.context=context;
    }


    Boolean checkemailisvalid(String email) {

        if (email.length() < 1) {
            Toast.makeText(context, "Email is Short", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(context, "Something is Missing", Toast.LENGTH_SHORT).show();

            return false;
        }
        else {
            return true;
        }
    }

    Boolean checkpasswordisvalid(String password) {

        if (password.length() < 6) {
            Toast.makeText(context, "Password Is Short", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }

    }
}
