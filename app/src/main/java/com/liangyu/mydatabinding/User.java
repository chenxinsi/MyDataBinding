package com.liangyu.mydatabinding;

/**
 * Created by liangyu on 17-7-20.
 */

public class User {
    public String firstName;
    public String lastName;
    public User(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }



    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
