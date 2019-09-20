package com.tasdid.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("MfS73T9DUa4o65lqcygjsbp9wu6dBhBAAF1hpeQr")
                // if defined
                .clientKey("TiLhV42U33RwbfGEy6uURl4fPFeVzhue5cyJfmyU")
                .server("https://parseapi.back4app.com/")
                .build()
        );


    }
}
