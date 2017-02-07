package com.zuhaibahmad.activeandroidrobolectrictutorial;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;

/**
 * Created by Zuhaib Ahmad on 2/7/2017.
 * <p>
 * Java POJO representing database model
 */
public class Item extends Model {

    public static final String KEY_CONTACT = "contact";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_NAME = "name";

    @Column(name = KEY_NAME)
    private String name;
    @Column(name = KEY_EMAIL)
    private String email;
    @Column(name = KEY_CONTACT)
    private String contact;

    public Item() {
        super();
    }

    public Item(String name, String email, String contact) {
        super();
        this.name = name;
        this.email = email;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
