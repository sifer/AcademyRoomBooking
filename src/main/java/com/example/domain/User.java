package com.example.domain;

/**
 * Created by Administrator on 2017-03-08.
 */
public class User {
    private int id;
    private int Person_ID;

    public User(int id, int person_ID) {
        this.id = id;
        Person_ID = person_ID;
    }

    public int getId() {
        return id;
    }

    public int getPerson_ID() {
        return Person_ID;
    }
}
