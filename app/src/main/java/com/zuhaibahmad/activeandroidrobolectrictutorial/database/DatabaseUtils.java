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

package com.zuhaibahmad.activeandroidrobolectrictutorial.database;

import com.activeandroid.query.Select;
import com.zuhaibahmad.activeandroidrobolectrictutorial.models.Item;

import static android.text.TextUtils.isEmpty;

/**
 * Created by Zuhaib Ahmad on 11/28/2016.
 * <p>
 * Utility class to handle database operations
 */
public class DatabaseUtils {

    private static DatabaseUtils instance;

    private DatabaseUtils() {
    }

    /**
     * Gets singleton instance.
     *
     * @return the instance
     */
    public static DatabaseUtils getInstance() {
        if (instance == null) {
            instance = new DatabaseUtils();
        }

        return instance;
    }

    /**
     * Retrieves {@link Item} object from database
     *
     * @param name    The name of contact
     * @param email   The email of contact
     * @param contact The number of contact
     * @return The retrieved item
     */
    public Item retrieveFromDatabase(String name, String email, String contact) {
        // Use first non-null parameter for conditional check
        String whereClause = "";
        String key = "";

        if (!isEmpty(name)) {
            whereClause = name;
            key = Item.KEY_NAME;
        } else if (!isEmpty(email)) {
            whereClause = email;
            key = Item.KEY_EMAIL;
        } else if (!isEmpty(contact)) {
            whereClause = contact;
            key = Item.KEY_CONTACT;
        }

        // Query the database and return the fetched item
        return new Select()
                .from(Item.class)
                .where(key + " =?", whereClause)
                .executeSingle();
    }

    /**
     * Saves {@link Item} to database
     *
     * @param name    The name of contact
     * @param email   The email of contact
     * @param contact The number of contact
     * @return The operation success status
     */
    public boolean saveToDatabase(String name, String email, String contact) {
        // Create database item object out of provided parameters
        Item item = new Item(name, email, contact);

        // Save to database and get the id of saved item
        Long id = item.save();

        // Mark operation as successful if returned id is non-zero
        return id > 0;
    }
}