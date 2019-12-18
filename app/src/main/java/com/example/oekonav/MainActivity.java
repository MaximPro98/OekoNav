package com.example.oekonav;
import com.parse.Parse;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.ParseException;
import androidx.appcompat.app.AppCompatActivity;
import com.parse.LogInCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parse.enableLocalDatastore(this);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("dq0ikfmgyQHFQr5IwohD9Kw0eC46w6Jb5NCpVdXH")
                .clientKey("0JmGmpQb9Z4V0njeWQ9Gh2iItGeoEDa7eutfrt76")
                .server("https://parseapi.back4app.com")
                .build()
        );
        ParseInstallation.getCurrentInstallation().saveInBackground();

        setContentView(R.layout.activity_main);


        ParseUser currentUser = ParseUser.getCurrentUser();

        if (currentUser != null) {
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        } else {
            Application app = getApplication();
            MyApp myapp = (MyApp) app;
            //myapp.initialisieren();
            initListener(myapp);
        }


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
                    public void onClick(final View view) {

                        ParseUser.logInInBackground(textInputUsername.getText().toString(), textInputPassword.getText().toString(), new LogInCallback() {
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
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }
}
