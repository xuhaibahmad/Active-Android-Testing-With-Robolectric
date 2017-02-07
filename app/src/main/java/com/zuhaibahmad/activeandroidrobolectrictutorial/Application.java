package com.zuhaibahmad.activeandroidrobolectrictutorial;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.zuhaibahmad.activeandroidrobolectrictutorial.models.Item;

/**
 * Created by Zuhaib on 1/22/2016.
 * <p>
 * Application class for global management and data access
 */
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Create Active Android configurations
        Configuration.Builder configuration = new Configuration.Builder(this);
        configuration.addModelClasses(Item.class);
        // Initialize ActiveAndroid DB
        ActiveAndroid.initialize(configuration.create());
    }
}
