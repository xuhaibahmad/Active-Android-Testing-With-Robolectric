/*
 * Copyright (C) 2017 Zuhaib Ahmad
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.zuhaibahmad.activeandroidrobolectrictutorial;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.zuhaibahmad.activeandroidrobolectrictutorial.models.Item;

/**
 * Created by Zuhaib Ahmad on 1/20/2017.
 * <p>
 * Application subclass to be used in Tests
 */

public class TestApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Create configurations for a temporary mock database
        Configuration.Builder configuration = new Configuration.Builder(this).setDatabaseName(null);
        configuration.addModelClasses(Item.class);
        // Initialize ActiveAndroid DB
        ActiveAndroid.initialize(configuration.create());
    }

    @Override
    public void onTerminate() {
        // Dispose temporary database on termination
        ActiveAndroid.dispose();
        super.onTerminate();
    }
}
