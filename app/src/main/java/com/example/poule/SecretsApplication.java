package com.example.poule;

import android.app.Application;
import com.google.android.libraries.places.api.Places;

public class SecretsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Places.initialize(this, BuildConfig.MAPS_API_KEY);
    }
}
