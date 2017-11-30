package com.skylele.myapplication.utils;

import android.app.Application;

/**
 * Created by cxg on 2017/11/30.
 */

public class MyApplication extends Application {
    public static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
