package com.example.oekonav;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        final TextInputEditText username = findViewById(R.id.txt_username_signup);
        final TextInputEditText email = findViewById(R.id.txt_email_reg);
        final TextInputEditText emailConf = findViewById(R.id.txt_emailConf_reg);
        final TextInputEditText password = findViewById(R.id.txt_password_reg);
        final TextInputEditText passwordConf = findViewById(R.id.txt_passwordConf_reg);
        final Button btnReg = findViewById(R.id.btn_Reg);

        btnReg.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {

                       if(password.getText() == passwordConf.getText()){
                           password.setTextColor(Color.RED);
                           passwordConf.setTextColor(Color.RED);
                       }else{
                           password.setTextColor(Color.BLACK);
                           passwordConf.setTextColor(Color.BLACK);
                       }
                       String emailTest = email.getText().toString().trim();
                        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                        if((email.getText() == emailConf.getText()) && emailTest.matches(emailPattern) ){
                            email.setTextColor(Color.RED);
                            emailConf.setTextColor(Color.RED);
                        }else{
                            email.setTextColor(Color.BLACK);
                            emailConf.setTextColor(Color.BLACK);
                        }


                    }
                }
        );
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
