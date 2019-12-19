package com.example.oekonav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

public class SpalshScreen extends AppCompatActivity {

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

        setContentView(R.layout.activity_spalsh_screen);


        ParseUser currentUser = ParseUser.getCurrentUser();

        if (currentUser != null) {
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
