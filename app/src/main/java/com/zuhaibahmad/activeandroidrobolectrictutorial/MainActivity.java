package com.zuhaibahmad.activeandroidrobolectrictutorial;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.zuhaibahmad.activeandroidrobolectrictutorial.database.DatabaseUtils;

import static android.text.TextUtils.isEmpty;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mContainer;
    private EditText mNameEditText;
    private EditText mEmailEditText;
    private EditText mContactEditText;
    private Button mSaveButton;
    private Button mRetrieveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContainer = (LinearLayout) findViewById(R.id.LLContainer);
        mNameEditText = (EditText) findViewById(R.id.ETName);
        mEmailEditText = (EditText) findViewById(R.id.ETEmail);
        mContactEditText = (EditText) findViewById(R.id.ETContact);
        mSaveButton = (Button) findViewById(R.id.BTSave);
        mRetrieveButton = (Button) findViewById(R.id.BTRetrieve);

        mSaveButton.setOnClickListener(this);
        mRetrieveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        String name = mNameEditText.getText().toString();
        String email = mEmailEditText.getText().toString();
        String contact = mContactEditText.getText().toString();

        if (id == R.id.BTSave) {
            // Validate input
            if (isEmpty(name) || isEmpty(email) || isEmpty(contact)) {
                showSnackBar("Please Fill All Fields Before Saving To Database");
                return;
            }

            // Save to database
            boolean isSuccessful = DatabaseUtils.getInstance()
                    .saveToDatabase(name, email, contact);

            // Notify user regarding operation success
            String message = isSuccessful ? "Saved Successfully" : "Error While Saving Item";
            showSnackBar(message);

        } else if (id == R.id.BTRetrieve) {
            // Validate input
            if (isEmpty(name) && isEmpty(email) && isEmpty(contact)) {
                showSnackBar("Please Fill At Least One Field Before Retrieving From Database");
                return;
            }

            // Retrieve from database
            Item item = DatabaseUtils.getInstance()
                    .retrieveFromDatabase(name, email, contact);

            // Notify user about error in case of failure
            if (item == null) {
                showSnackBar("Error Or No Result While Retrieving Item From Database");
                return;
            }

            // Display item data in relevant fields
            mNameEditText.setText(item.getName());
            mEmailEditText.setText(item.getEmail());
            mContactEditText.setText(item.getContact());

        } else {
            showSnackBar("Invalid Action!");
        }
    }

    private void showSnackBar(String message) {
        Snackbar.make(mContainer, message, Snackbar.LENGTH_SHORT).show();
    }
}
