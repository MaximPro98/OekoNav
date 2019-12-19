package com.example.oekonav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
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

        if(currentUser != null){
            if (currentUser.isAuthenticated()) {
    gotoMap();
            } else {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }else{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }
    public void gotoMap() {
        Intent intent = new Intent(this, Navdrawmenu.class);
        startActivity(intent);
    }
}
