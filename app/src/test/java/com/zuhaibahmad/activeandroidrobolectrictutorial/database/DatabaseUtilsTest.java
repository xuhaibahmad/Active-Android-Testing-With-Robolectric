package com.zuhaibahmad.activeandroidrobolectrictutorial.database;

import com.zuhaibahmad.activeandroidrobolectrictutorial.BuildConfig;
import com.zuhaibahmad.activeandroidrobolectrictutorial.TestApplication;
import com.zuhaibahmad.activeandroidrobolectrictutorial.models.Item;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Zuhaib Ahmad on 2/7/2017.
 * <p>
 * Tests fro database utils
 */
@RunWith(RobolectricTestRunner.class)
@Config(
        constants = BuildConfig.class,
        application = TestApplication.class,    // Run with TestApplication instead of actual
        sdk = 21                                // Test against Lollipop
)
public class DatabaseUtilsTest {

    private DatabaseUtils mInstance;

    @Before
    public void setUp() throws Exception {
        ShadowLog.stream = System.out;

        mInstance = DatabaseUtils.getInstance();
    }

    @Test
    public void retrieveFromDatabase() throws Exception {
        String name = "Test Retrieve Item";
        String email = "Retrieve@abc.com";
        String contact = "12345";
        Item testItem = new Item(name, email, contact);
        testItem.save();

        // Fetch with name
        Item item1 = mInstance.retrieveFromDatabase(name, null, null);
        assertNotNull(item1);
        assertEquals(testItem, item1);

        // Fetch with email
        Item item2 = mInstance.retrieveFromDatabase(null, email, null);
        assertNotNull(item2);
        assertEquals(testItem, item2);

        // Fetch with contact
        Item item3 = mInstance.retrieveFromDatabase(null, null, contact);
        assertNotNull(item3);
        assertEquals(testItem, item3);

        testItem.delete();
    }

    @Test
    public void saveToDatabase() throws Exception {
        String name = "Test Save Item";
        String email = "Save@abc.com";
        String contact = "67890";

        // Create test object from test parameters
        Item testItem = new Item(name, email, contact);

        // Save test parameters
        boolean isSuccessful = mInstance.saveToDatabase(name, email, contact);

        // Check if successfully saved
        assertTrue(isSuccessful);

        // Check if saved item is equal to test object
        Item savedItem = mInstance.retrieveFromDatabase(name, null, null);
        assertEquals(testItem.getName(), savedItem.getName());
        assertEquals(testItem.getEmail(), savedItem.getEmail());
        assertEquals(testItem.getContact(), savedItem.getContact());
    }

}