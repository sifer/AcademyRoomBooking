package com.example.domain;

/**
 * Created by Administrator on 2017-03-08.
 */
public class User {
    private int id;
    private String username;
    private String password;
    private int Person_ID;

    public User(int id, String username, String password, int person_ID) {
        this.id = id;
        this.username = username;
        this.password = password;
        Person_ID = person_ID;
    }

    public int getId() {
        return id;
    }

    public int getPerson_ID() {
        return Person_ID;
    }
}
