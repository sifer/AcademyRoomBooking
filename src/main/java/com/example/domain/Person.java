package com.example.domain;

/**
 * Created by Administrator on 2017-03-08.
 */
public class Person {
    private int PersonID;
    private String FirstName;
    private String LastName;

    public Person(int personID, String firstName, String lastName) {
        PersonID = personID;
        FirstName = firstName;
        LastName = lastName;
    }

    public int getPersonID() {
        return PersonID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}
