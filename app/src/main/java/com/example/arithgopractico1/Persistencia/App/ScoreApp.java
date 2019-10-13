package com.example.arithgopractico1.Persistencia.App;

import android.app.Application;
import android.content.Context;

public class ScoreApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        ScoreApp.context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

}
