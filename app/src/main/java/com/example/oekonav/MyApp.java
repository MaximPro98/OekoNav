package com.example.oekonav;

import android.app.Application;

import com.example.oekonav.resources.User;

public class MyApp extends Application {

    private String userID;
    private User user;

    public User getUser() {
        return user;
    }

    //public void initialisieren(){
    public MyApp() {

    }

    public void createUser(String userID) {

        this.userID = userID;

        if (this.userID.equals("1")) {
            user = new User("Hans", "Super Nett", 3000);
            return;
        }
        if (this.userID.equals("2")) {
            user = new User("Peter", "Super Mies", 1000);
            return;
        }

        return;
    }


}
