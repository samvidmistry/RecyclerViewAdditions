package com.example.samvidmistry.recyclerviewadditions;

import android.app.Application;

import com.bumptech.glide.request.target.ViewTarget;

/**
 * Created by samvidmistry on 11/19/16.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ViewTarget.setTagId(R.id.glide_tag);
    }
}
