package com.example.rbammi.simplechat;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by rbammi on 10/22/15.
 */

public class ChatApplication extends Application {
    public static final String YOUR_APPLICATION_ID = "eXteCsc0Ds4d8IoGtJ6H3jPHoQ67Dk2xnuq8jaVB";
    public static final String YOUR_CLIENT_KEY = "DieRRUNeHyRrXZiOwAFn4kkUj3SidVk2cLIlVDLO";
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);

        // Register your parse models here
        ParseObject.registerSubclass(Message.class);
        Parse.initialize(this, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);
    }
}
