package com.example.oekonav;

import com.example.oekonav.resources.MailboxAdapter;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class PushController {

    ParseInstallation targetInstall;
    ParseUser targetUser;
    ParseUser current = ParseUser.getCurrentUser();
    public PushController(String username){
        ParseQuery q = ParseUser.getQuery();
        q.whereMatches("username", username);
        q.findInBackground(new FindCallback< ParseObject >() {
            public void done(List<ParseObject> User, ParseException e) {
                if (e == null) {

                    targetUser = (ParseUser) User.get(0);


                } else {


                }
            }
        });
    }
}
