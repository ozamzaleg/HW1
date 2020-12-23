package com.example.oz_tal_application_project.utils;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MySP.init(this);
        getLocation.init(this);
    }

}

