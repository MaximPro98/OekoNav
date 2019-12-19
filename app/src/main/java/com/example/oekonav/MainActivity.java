package com.example.oekonav;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.parse.ParseUser;
import com.parse.ParseException;
import androidx.appcompat.app.AppCompatActivity;
import com.parse.LogInCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        initListener();
    }

    private void initListener() {
        final TextInputEditText textInputUsername = findViewById(R.id.textInputUsername);
        final TextInputEditText textInputPassword = findViewById(R.id.textInputPassword);
        final Button buttonLogin = findViewById(R.id.buttonLogin);
        final TextView textViewRegister = findViewById(R.id.textViewRegister);


        buttonLogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {

                        ParseUser.logInInBackground(textInputUsername.getText().toString().toLowerCase(), textInputPassword.getText().toString(), new LogInCallback() {
                            public void done(ParseUser user, ParseException e) {
                                if (user != null) {
                                  goToMap(view);
                                } else {
                                    textInputUsername.setText("");
                                    textInputPassword.setText("");
                                }
                            }
                        });

                    }
                }
        );
        textViewRegister.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    goToRegister(view);
                    }
                }
        );

    }


    // Wechselt bei aufruf zum ausgewählten TrainProg
    public void goToRegister (View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    public void goToMap (View view) {
        Intent intent = new Intent(this, Navdrawmenu.class);
        startActivity(intent);
    }
}
