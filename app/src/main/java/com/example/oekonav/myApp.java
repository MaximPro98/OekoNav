package com.example.oekonav;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

public class myApp extends Application {
    public myApp(){

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("dq0ikfmgyQHFQr5IwohD9Kw0eC46w6Jb5NCpVdXH")
                .clientKey("0JmGmpQb9Z4V0njeWQ9Gh2iItGeoEDa7eutfrt76")
                .server("https://parseapi.back4app.com")
                .build()
        );
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.put("GCMSenderId", "731446809473");
        if (ParseUser.getCurrentUser() != null) {
            installation.put("user", ParseUser.getCurrentUser());

        }
        installation.saveInBackground();
    }
}
