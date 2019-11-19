package com.example.oekonav;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Application app = getApplication();
        MyApp myapp = (MyApp) app;

        //myapp.initialisieren();
        initListener(myapp);
    }

    private void initListener(MyApp ma) {
        final TextInputEditText textInputUsername = findViewById(R.id.textInputUsername);
        final TextInputEditText textInputPassword = findViewById(R.id.textInputPassword);
        final Button buttonLogin = findViewById(R.id.buttonLogin);
        final TextView textViewRegister = findViewById(R.id.textViewRegister);

        final Context context = this;

        final MyApp myapp = ma;

        buttonLogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        System.out.println(textInputUsername.getText());
                        myapp.createUser(textInputUsername.getText().toString());
                        System.out.println(myapp.getUser().getName());
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
}
