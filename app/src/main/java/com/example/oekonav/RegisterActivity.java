package com.example.oekonav;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.parse.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        final TextView errorMsg = findViewById(R.id.txt_error_reg);
        errorMsg.setText("");

        final Button btnReg = findViewById(R.id.btn_Reg);
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(username.getText().length() < 6){
                        username.setTextColor(Color.RED);
                    }else{
                        username.setTextColor(Color.GREEN);
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(password.getText().length() < 8){
                    password.setTextColor(Color.RED);
                }else{
                    password.setTextColor(Color.GREEN);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        passwordConf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(password.getText().equals(passwordConf.getText())){
                        passwordConf.setTextColor(Color.RED);
                    }else{
                        passwordConf.setTextColor(Color.GREEN);

                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String emailTest = email.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if(emailTest.matches((emailPattern))){
                    email.setTextColor(Color.GREEN);
                }else{
                    email.setTextColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        emailConf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(emailConf.getText().equals(email.getText())){
                        emailConf.setTextColor(Color.RED);
                    }else{
                        emailConf.setTextColor(Color.GREEN);

                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnReg.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {

                      if(username.getCurrentTextColor() == Color.GREEN &&
                              email.getCurrentTextColor() == Color.GREEN
                      && emailConf.getCurrentTextColor() == Color.GREEN &&
                              password.getCurrentTextColor() == Color.GREEN && passwordConf.getCurrentTextColor() == Color.GREEN){

                          ParseACL acl = new ParseACL();
                          acl.setPublicWriteAccess(true);
                          acl.setPublicReadAccess(true);
                          ParseUser user = new ParseUser();
                          user.setEmail(email.getText().toString());
                          user.setPassword(password.getText().toString());
                          user.setUsername(username.getText().toString().toLowerCase());
                          user.put("Score", 0);
                          user.put("Tagline", "Hey I'm New");
                          user.setACL(acl);
                          user.signUpInBackground(new SignUpCallback() {
                              public void done(ParseException e) {
                                  if (e == null) {
                                      errorMsg.setText("");
                                      goToMap(view);
                                  } else {
                                     errorMsg.setText("Error occured!");
                                     errorMsg.setTextColor(Color.RED);
                                     System.out.println(e.getMessage());
                                  }
                              }
                          });
                      }

                    }
                }
        );
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void goToMap (View view) {
        Intent intent = new Intent(this, Navdrawmenu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }

}
